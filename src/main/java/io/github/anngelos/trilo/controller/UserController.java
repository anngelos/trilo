package io.github.anngelos.trilo.controller;

import io.github.anngelos.trilo.dto.UserRequestDTO;
import io.github.anngelos.trilo.exception.UserNotFoundException;
import io.github.anngelos.trilo.model.User;
import io.github.anngelos.trilo.repository.UserRepository;
import io.github.anngelos.trilo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    try {
      User user = userService.getUserById(id);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
    try {
      User updatedUser = userService.updateUser(id, dto);
      return ResponseEntity.ok(updatedUser);
    } catch (UserNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
    return userRepository.findById(id).map(user -> {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
