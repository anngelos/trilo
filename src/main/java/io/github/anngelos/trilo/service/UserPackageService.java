package io.github.anngelos.trilo.service;

import io.github.anngelos.trilo.dto.CreatePackageDTO;
import io.github.anngelos.trilo.enums.PackageStatus;
import io.github.anngelos.trilo.exception.UserNotFoundException;
import io.github.anngelos.trilo.model.User;
import io.github.anngelos.trilo.model.UserPackage;
import io.github.anngelos.trilo.repository.UserPackageRepository;
import io.github.anngelos.trilo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPackageService {

  private final UserRepository userRepository;
  private final UserPackageRepository userPackageRepository;

  public UserPackage createPackage(Long userId, CreatePackageDTO dto) {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário de ID " + userId + " não encontrado."));
    UserPackage userPackage = UserPackage.builder()
            .trackCode(dto.trackCode())
            .description(dto.description())
            .sender(dto.sender())
            .receiver(dto.receiver())
            .status(dto.status() != null ? dto.status() : PackageStatus.PENDING)
            .user(user)
            .build();

    return userPackageRepository.save(userPackage);
  }

  // updatePackage = edita o package com base no id dele

  // getPackage = pega o pacote com base no id dele

  // getAllUserPackages = pega todos os pacotes do usuario com base no id do usuario msm

  // deletePackage = deleta o pacote (id do pacote) do usuario
}
