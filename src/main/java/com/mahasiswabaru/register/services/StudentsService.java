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
import java.util.List;

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

        Students students = new Students();
        students.setAddress(studentReq.getStudentAddress());
        students.setDob(studentReq.getStudentDob());
        students.setName(studentReq.getStudentName());
        students.setGender(studentReq.getStudentGender());

        Majors theMajor = new Majors();
        theMajor = majorsRepository.findMajorsById(studentReq.getMajorId());

        students.setMajors(theMajor);

        studentsRepository.save(students);

        return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(studentReq);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all students")
    public ResponseEntity<Object> getStudents(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Students> resp = studentsRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(resp);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get student by Id")
    public ResponseEntity<Object> getStudentById(Integer studentId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Students students = studentsRepository.findStudentsById(studentId);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(students);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get student by name")
    public ResponseEntity<Object> getStudentByName(String studentName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Students students = studentsRepository.findStudentsContainingName(studentName);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(students);
    }
}
