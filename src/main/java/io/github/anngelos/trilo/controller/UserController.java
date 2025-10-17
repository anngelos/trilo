package io.github.anngelos.trilo.controller;

import io.github.anngelos.trilo.dto.UserRequestDTO;
import io.github.anngelos.trilo.model.User;
import io.github.anngelos.trilo.repository.UserRepository;
import io.github.anngelos.trilo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserRequestDTO dto) {
    User createdUser = userService.createUser(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }
}
