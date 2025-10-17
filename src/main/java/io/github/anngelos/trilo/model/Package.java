package io.github.anngelos.trilo.model;

import io.github.anngelos.trilo.enums.PackageStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "packages")
public class Package {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column
  private String trackId;

  @Column
  private String description;

  @Column
  @Enumerated(EnumType.STRING)
  private PackageStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}
