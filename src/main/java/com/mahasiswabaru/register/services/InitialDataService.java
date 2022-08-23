package com.mahasiswabaru.register.services;

import com.mahasiswabaru.register.dto.DegreesDto;
import com.mahasiswabaru.register.dto.DepartmentDto;
import com.mahasiswabaru.register.dto.MajorsDto;
import com.mahasiswabaru.register.models.Degrees;
import com.mahasiswabaru.register.models.Departments;
import com.mahasiswabaru.register.models.Majors;
import com.mahasiswabaru.register.repositories.DegreesRepository;
import com.mahasiswabaru.register.repositories.DepartmentsRepository;
import com.mahasiswabaru.register.repositories.MajorsRepository;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional(rollbackOn = Exception.class)
public class InitialDataService {
    @Autowired
    private DegreesRepository degreesRepository;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private MajorsRepository majorsRepository;

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new major")
    public ResponseEntity<Object> addMajor(MajorsDto majorRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Majors existData = majorsRepository.findMajorsByNameAndDepartmentsIdAndDegreesId(majorRequest.getMajorName(), majorRequest.getDepartmentId(), majorRequest.getDegreeId());
        Majors theMajor = new Majors();
        if(existData == null) {
            theMajor.setName(majorRequest.getMajorName());
            Degrees theDegree = new Degrees();
            theDegree.setId(majorRequest.getDegreeId());
            theMajor.setDegrees(theDegree);
            Departments theDepartment = new Departments();
            theDepartment.setId(majorRequest.getDepartmentId());
            theMajor.setDepartments(theDepartment);
            LocalDateTime todayDateTime = LocalDateTime.now();
            theMajor.setCreatedAt(todayDateTime);

            majorsRepository.save(theMajor);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new department")
    public ResponseEntity<Object> addDepartment(DepartmentDto departmentRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Departments foundData = departmentsRepository.findDepartmentsByName(departmentRequest.getDepartmentName());
        if(foundData == null) {
            Departments theDepartment = new Departments();
            theDepartment.setName(departmentRequest.getDepartmentName());
            LocalDateTime todayDateTime = LocalDateTime.now();
            theDepartment.setCreatedAt(todayDateTime);

            departmentsRepository.save(theDepartment);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new degree")
    public ResponseEntity<Object> addDegree(DegreesDto degreeeRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Degrees foundData = degreesRepository.findDegreesByName(degreeeRequest.getDegreeName());
        if(foundData == null){
            Degrees theDegree = new Degrees();
            theDegree.setName(degreeeRequest.getDegreeName());
            LocalDateTime todayDateTime = LocalDateTime.now();
            theDegree.setCreatedAt(todayDateTime);

            degreesRepository.save(theDegree);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }
}
