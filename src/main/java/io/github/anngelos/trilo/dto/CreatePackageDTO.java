package io.github.anngelos.trilo.dto;

import io.github.anngelos.trilo.enums.PackageStatus;

public record CreatePackageDTO(String trackCode, String description, String sender, String receiver, PackageStatus status) {
}
