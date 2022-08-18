package com.mahasiswabaru.register.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(name="Students")
@Table(name="students")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Students extends Audit<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, insertable = true, length = 50)
    private String name;

    @Column(name = "dob", nullable = false, insertable = true)
    private Date dob;

    @Column(name = "gender", nullable = false, insertable = true, length = 1)
    private String gender;

    @Lob
    @Column(name = "address", nullable = true, insertable = true, length=512)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id", nullable = false, insertable = true, referencedColumnName = "id")
    private Majors majors;
}
