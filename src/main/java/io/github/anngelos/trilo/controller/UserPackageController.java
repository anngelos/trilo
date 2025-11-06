package io.github.anngelos.trilo.controller;

import io.github.anngelos.trilo.dto.CreatePackageDTO;
import io.github.anngelos.trilo.model.UserPackage;
import io.github.anngelos.trilo.service.UserPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
public class UserPackageController {

  private final UserPackageService userPackageService;

  @PostMapping
  public ResponseEntity<UserPackage> createPackage(@PathVariable Long userId, @RequestBody CreatePackageDTO dto) {
    UserPackage createdPackage = userPackageService.createPackage(userId, dto);
    return ResponseEntity.ok(createdPackage);
  }
}
