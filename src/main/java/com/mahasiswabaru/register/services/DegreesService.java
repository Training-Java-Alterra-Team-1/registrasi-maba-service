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
import java.util.List;

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

        Degrees theDegree = new Degrees();
        theDegree.setName(degreeeRequest.getDegreeName());
        LocalDateTime todayDateTime = LocalDateTime.now();
        theDegree.setCreatedAt(todayDateTime);

        degreesRepository.save(theDegree);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(theDegree);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all degrees")
    public ResponseEntity<Object> getAllDegrees(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Degrees> respon = degreesRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(respon);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get degrees by Id")
    public ResponseEntity<Object> getDegreeById(Integer degreeId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Degrees degrees = degreesRepository.findDegreesByIdAndDeletedAtIsNull(degreeId);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(degrees);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get degrees by name")
    public ResponseEntity<Object> getDegreeById(String degreeName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Degrees degrees = degreesRepository.findDegreesByNameAndDeletedAtIsNull(degreeName);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(degrees);
    }
}
