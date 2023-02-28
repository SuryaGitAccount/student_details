package com.student.controller;

import com.student.payload.StudentInfoDTO;
import com.student.service.StudentInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/details")
public class StudentInfoController {

    private StudentInfoService studentInfoService;

    public StudentInfoController(StudentInfoService studentInfoService){
        this.studentInfoService = studentInfoService;
    }

    //http://localhost:8080/student/details
    @PostMapping
    public ResponseEntity<StudentInfoDTO> saveEntity (@RequestBody StudentInfoDTO studentInfoDTO){
        StudentInfoDTO savedDTO = studentInfoService.createStudentInfo(studentInfoDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    //http://localhost:8080/student/details/1
    @GetMapping("/{id}")
    public ResponseEntity<StudentInfoDTO> getStudentInfoById(@PathVariable("id") long id){
        StudentInfoDTO dto = studentInfoService.getStudentInfoById(id);
        return ResponseEntity.ok(dto);
    }

    //http://localhost:8080/student/details/1
    @PutMapping("/{id}")
    public ResponseEntity<StudentInfoDTO> updateStudentInfo(@RequestBody StudentInfoDTO studentInfoDTO, @PathVariable("id") long id) {
        StudentInfoDTO dto = studentInfoService.updateStudentInfo(studentInfoDTO, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/student/details/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentInfo(@PathVariable(name = "id") long id){
        studentInfoService.deleteStudentInfoById(id);
        return new ResponseEntity<>("Student Info entity deleted successfully.", HttpStatus.OK);
    }
}
