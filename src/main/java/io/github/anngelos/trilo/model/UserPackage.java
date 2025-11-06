package io.github.anngelos.trilo.model;

import io.github.anngelos.trilo.enums.PackageStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "packages")
public class UserPackage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "track_code", nullable = false, unique = true)
  private String trackCode;

  @Column
  private String description;

  @Column
  private String sender;

  @Column
  private String receiver;

  @Column(name = "created_at", updatable = false)
  @org.hibernate.annotations.CreationTimestamp
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PackageStatus status = PackageStatus.PENDING;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
