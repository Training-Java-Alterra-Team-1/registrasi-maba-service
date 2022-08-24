package com.mahasiswabaru.register.services;

import com.mahasiswabaru.register.dto.StudentsDto;
import com.mahasiswabaru.register.models.Majors;
import com.mahasiswabaru.register.models.Students;
import com.mahasiswabaru.register.repositories.MajorsRepository;
import com.mahasiswabaru.register.repositories.StudentsRepository;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private MajorsRepository majorsRepository;

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new student")
    public ResponseEntity<Object> addStudent(StudentsDto studentReq){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Students student = new Students();
        student.setAddress(studentReq.getStudentAddress());
        student.setDob(studentReq.getStudentDob());
        student.setName(studentReq.getStudentName());
        student.setGender(studentReq.getStudentGender());

        Majors major = new Majors();
        major = majorsRepository.findMajorsById(studentReq.getMajorId());

        student.setMajors(major);

        studentsRepository.save(student);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", student);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all students")
    public ResponseEntity<Object> getStudents(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Students> students = studentsRepository.findAll();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", students);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get student by Id")
    public ResponseEntity<Object> getStudentById(Integer studentId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Students student = studentsRepository.findStudentsById(studentId);
        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(student).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        response.put("message", "success");
        response.put("data", student);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get student by name")
    public ResponseEntity<Object> getStudentByName(String studentName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Students student = studentsRepository.findStudentsByName(studentName);
        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(student).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        response.put("message", "success");
        response.put("data", student);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Update data student")
    public ResponseEntity<Object> updateStudentById(Integer studentId, StudentsDto studentReq){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Students student = studentsRepository.findStudentsById(studentId);
        Map<String, Object> response = new HashMap<String, Object>();
        if(!Optional.ofNullable(student).isPresent()) {
            response.put("message", "failed");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        if(studentReq.getStudentDob() != null)
            student.setDob(studentReq.getStudentDob());
        if(studentReq.getStudentAddress() != null && studentReq.getStudentAddress() != "")
            student.setAddress(studentReq.getStudentAddress());
        if(studentReq.getStudentName() != null && studentReq.getStudentName() != "")
            student.setName(studentReq.getStudentName());
        if(studentReq.getStudentGender() != null && studentReq.getStudentGender() != "")
            student.setGender(studentReq.getStudentGender());
        if(studentReq.getMajorId() != null){
            Majors major = new Majors();
            major.setId(studentReq.getMajorId());
            student.setMajors(major);
        }

        studentsRepository.save(student);
        response.put("message", "success");
        response.put("data", student);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
