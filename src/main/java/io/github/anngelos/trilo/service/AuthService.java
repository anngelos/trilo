package io.github.anngelos.trilo.service;

import io.github.anngelos.trilo.dto.LoginRequestDTO;
import io.github.anngelos.trilo.dto.LoginResponseDTO;
import io.github.anngelos.trilo.exception.UserNotFoundException;
import io.github.anngelos.trilo.repository.UserRepository;
import io.github.anngelos.trilo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public LoginResponseDTO login(LoginRequestDTO dto) {
    var user = userRepository.findByUsername(dto.username()).orElseThrow(() -> new UserNotFoundException("Usuário ou senha incorreto."));
    if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
      throw new IllegalArgumentException("Usuário ou senha incorreto.");
    }
    String token = jwtUtil.generateToken(user);
    return new LoginResponseDTO(token);
  }

  // logout = finaliza a sessao do User
}
