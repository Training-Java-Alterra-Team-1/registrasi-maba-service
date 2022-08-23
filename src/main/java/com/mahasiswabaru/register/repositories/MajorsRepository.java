package com.mahasiswabaru.register.repositories;

import com.mahasiswabaru.register.models.Majors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorsRepository extends JpaRepository<Majors, Long> {
    Majors findMajorsById(Integer majorId);
    Majors findMajorsByName(String majorName);
    Majors findMajorsByNameAndDepartmentsIdAndDegreesId(String majorName, Integer depId, Integer degId);
}
