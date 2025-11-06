package io.github.anngelos.trilo.service;

import io.github.anngelos.trilo.dto.LoginRequestDTO;
import io.github.anngelos.trilo.dto.LoginResponseDTO;
import io.github.anngelos.trilo.dto.UserRequestDTO;
import io.github.anngelos.trilo.enums.UserRole;
import io.github.anngelos.trilo.exception.UserNotFoundException;
import io.github.anngelos.trilo.model.User;
import io.github.anngelos.trilo.repository.UserRepository;
import io.github.anngelos.trilo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public User createUser(UserRequestDTO dto) {
    User user = User.builder()
            .username(dto.username())
            .password(passwordEncoder.encode(dto.password()))
            .role(dto.role() != null ? dto.role() : UserRole.USER)
            .build();

    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário de ID: " + id + " não encontrado."));
  }

  public User updateUser(Long id, UserRequestDTO dto) {
    return userRepository.findById(id).map(existingUser -> {
      if (dto.username() != null && !dto.username().isEmpty()) {
        existingUser.setUsername(dto.username());
      }

      if (dto.password() != null && !dto.password().isEmpty()) {
        existingUser.setPassword(passwordEncoder.encode(dto.password()));
      }

      return userRepository.save(existingUser);
    }).orElseThrow(() -> new UserNotFoundException("Usuário de ID: " + id + " não encontrado."));
  }
  
  public void deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário de ID: " + id + " não encontrado."));
    userRepository.delete(user);
  }
}
