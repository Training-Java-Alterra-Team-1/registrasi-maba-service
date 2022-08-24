package com.mahasiswabaru.register.controller;

import com.mahasiswabaru.register.dto.StudentsDto;
import com.mahasiswabaru.register.services.StudentsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @SneakyThrows(Exception.class)
    @PostMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerNewStudent(@RequestBody StudentsDto studentRequest){
        log.info("api POST /api/v1/student is hit.");
        return studentsService.addStudent(studentRequest);
    }

    @SneakyThrows(Exception.class)
    @GetMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllStudent(){
        log.info("api GET /api/v1/student is hit.");
        return studentsService.getStudents();
    }

    @SneakyThrows(Exception.class)
    @GetMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStudentById(@PathVariable Integer id){
        log.info("api GET /api/v1/student/{id} is hit.");
        return studentsService.getStudentById(id);
    }

    @SneakyThrows(Exception.class)
    @PutMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudentById(@PathVariable Integer id, @RequestBody StudentsDto studentReq){
        log.info("api PUT /api/v1/student/{id} is hit.");
        return studentsService.updateStudentById(id, studentReq);
    }
}
