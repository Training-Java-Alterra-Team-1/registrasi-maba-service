package com.mahasiswabaru.register.services;

import com.mahasiswabaru.register.dto.DegreesDto;
import com.mahasiswabaru.register.models.Degrees;
import com.mahasiswabaru.register.repositories.DegreesRepository;
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
import java.util.*;

@Service
@Transactional(rollbackOn = Exception.class)
public class DegreesService {
    @Autowired
    private DegreesRepository degreesRepository;

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new degree")
    public ResponseEntity<Object> addDegree(DegreesDto degreeeRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Degrees degree = new Degrees();
        degree.setName(degreeeRequest.getDegreeName());
        LocalDateTime todayDateTime = LocalDateTime.now();
        degree.setCreatedAt(todayDateTime);

        degreesRepository.save(degree);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", degree);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all degrees")
    public ResponseEntity<Object> getAllDegrees(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Degrees> degrees = degreesRepository.findAll();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", degrees);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get degrees by Id")
    public ResponseEntity<Object> getDegreeById(Integer degreeId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Degrees degree = degreesRepository.findDegreesById(degreeId);

        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(degree).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        response.put("message", "success");
        response.put("data", degree);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get degrees by name")
    public ResponseEntity<Object> getDegreeByName(String degreeName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Degrees degree = degreesRepository.findDegreesByName(degreeName);
        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(degree).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        response.put("message", "success");
        response.put("data", degree);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
