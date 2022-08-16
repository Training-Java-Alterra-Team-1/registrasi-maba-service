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
import java.util.List;

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

        Majors theMajor = new Majors();
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

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(theMajor);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all majors")
    public ResponseEntity<Object> getAllMajors(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Majors> resp = majorsRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(resp);
    }
}
