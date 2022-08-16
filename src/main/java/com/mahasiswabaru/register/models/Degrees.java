package com.mahasiswabaru.register.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Degrees")
@Table(name="degrees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Degrees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, insertable = true, length = 50)
    private String name;

    @Column(name = "created_at", nullable = false, insertable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, insertable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
