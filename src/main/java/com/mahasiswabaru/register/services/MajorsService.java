package com.mahasiswabaru.register.services;

import com.mahasiswabaru.register.dto.DegreesDto;
import com.mahasiswabaru.register.dto.MajorsDto;
import com.mahasiswabaru.register.models.Degrees;
import com.mahasiswabaru.register.models.Departments;
import com.mahasiswabaru.register.models.Majors;
import com.mahasiswabaru.register.repositories.DegreesRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class MajorsService {
    @Autowired
    private MajorsRepository majorsRepository;

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new major")
    public ResponseEntity<Object> addMajor(MajorsDto majorRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Majors major = new Majors();
        major.setName(majorRequest.getMajorName());
        Degrees theDegree = new Degrees();
        theDegree.setId(majorRequest.getDegreeId());
        major.setDegrees(theDegree);
        Departments theDepartment = new Departments();
        theDepartment.setId(majorRequest.getDepartmentId());
        major.setDepartments(theDepartment);
        LocalDateTime todayDateTime = LocalDateTime.now();
        major.setCreatedAt(todayDateTime);

        majorsRepository.save(major);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", major);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all majors")
    public ResponseEntity<Object> getAllMajors(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Majors> majors = majorsRepository.findAll();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", majors);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get major by id")
    public ResponseEntity<Object> getMajorById(Integer majorId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Majors major = majorsRepository.findMajorsById(majorId);
        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(major).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        response.put("message", "success");
        response.put("data", major);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get major by name")
    public ResponseEntity<Object> getMajorByName(String majorName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Majors major = majorsRepository.findMajorsByName(majorName);
        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(major).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
