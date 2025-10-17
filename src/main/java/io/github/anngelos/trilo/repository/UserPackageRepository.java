package io.github.anngelos.trilo.repository;

import io.github.anngelos.trilo.model.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPackageRepository extends JpaRepository<UserPackage, Long> {
}
