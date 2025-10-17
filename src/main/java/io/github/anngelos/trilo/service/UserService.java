package io.github.anngelos.trilo.service;

import io.github.anngelos.trilo.dto.UserRequestDTO;
import io.github.anngelos.trilo.model.User;
import io.github.anngelos.trilo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User createUser(UserRequestDTO dto) {
    User user = User.builder()
            .username(dto.username())
            .password(passwordEncoder.encode(dto.password()))
            .build();

    return userRepository.save(user);
  }

  // updateUser = atualiza o user com base no id

  // getUserById = obter usuario pelo id dele
  
  // deleteUser = deleta o usuario com base no id ou no proprio user mesmo n sei ainda
}
