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
}
