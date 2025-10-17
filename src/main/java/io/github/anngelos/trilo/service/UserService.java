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

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário de ID: " + id + " não encontrado."));
  }

  // updateUser = atualiza o user com base no id
  
  // deleteUser = deleta o usuario com base no id ou no proprio user mesmo n sei ainda

  // login = faz o login do usuario gerando o jwt
}
