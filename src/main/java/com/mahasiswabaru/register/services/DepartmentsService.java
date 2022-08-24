package com.mahasiswabaru.register.services;

import com.mahasiswabaru.register.dto.DepartmentDto;
import com.mahasiswabaru.register.models.Departments;
import com.mahasiswabaru.register.repositories.DepartmentsRepository;
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

@Service
@Transactional(rollbackOn = Exception.class)
public class DepartmentsService {
    @Autowired
    private DepartmentsRepository departmentsRepository;

    @SneakyThrows(Exception.class)
    @ApiOperation("Add new department")
    public ResponseEntity<Object> addDepartment(DepartmentDto departmentRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Departments department = new Departments();
        department.setName(departmentRequest.getDepartmentName());
        LocalDateTime todayDateTime = LocalDateTime.now();
        department.setCreatedAt(todayDateTime);

        departmentsRepository.save(department);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", department);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @SneakyThrows(Exception.class)
    @ApiOperation("Get all departments")
    public ResponseEntity<Object> getAllDepartments(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Departments> departments = departmentsRepository.findAll();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "success");
        response.put("data", departments);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
