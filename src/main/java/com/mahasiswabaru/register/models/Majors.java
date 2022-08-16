package com.mahasiswabaru.register.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Majors")
@Table(name="majors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Majors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, insertable = true, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false, insertable = true, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Departments departments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_id", nullable = false, insertable = true, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Degrees degrees;

    @Column(name = "created_at", nullable = false, insertable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, insertable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
