package com.mahasiswabaru.register.repositories;

import com.mahasiswabaru.register.models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
    Departments findDepartmentsById(Integer departmentId);
    Departments findDepartmentsByName(String departmentName);
}
