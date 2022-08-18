package com.mahasiswabaru.register.repositories;

import com.mahasiswabaru.register.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
    Students findStudentsById(Integer studentId);
    Students findStudentsByName(String studentName);
    Students findStudentsByGender(String studentGender);
}
