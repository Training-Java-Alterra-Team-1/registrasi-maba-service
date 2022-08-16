package com.mahasiswabaru.register.repositories;

import com.mahasiswabaru.register.models.Degrees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreesRepository extends JpaRepository<Degrees, Long> {
    Degrees findDegreesByIdAndDeletedAtIsNull(Integer degreeId);
    Degrees findDegreesByNameAndDeletedAtIsNull(String degreeName);
}
