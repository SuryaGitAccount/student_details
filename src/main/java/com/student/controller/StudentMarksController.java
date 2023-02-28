package com.student.controller;

import com.student.payload.StudentMarksDTO;
import com.student.service.StudentInfoService;
import com.student.service.StudentMarksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/")
public class StudentMarksController {

    private StudentMarksService studentMarksService;

    public StudentMarksController(StudentMarksService studentMarksService) {
        this.studentMarksService = studentMarksService;

    }

    //http://localhost:8080/students/marks/123455/details
    @PostMapping("/marks/{studentInfoId}/details")
    public ResponseEntity<StudentMarksDTO> createStudentMarks
    (@PathVariable("studentInfoId") long studentInfoId, @RequestBody StudentMarksDTO studentMarksDTO){

        StudentMarksDTO dto = studentMarksService.createStudentMarks(studentInfoId, studentMarksDTO);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/student/marks/detail/1
    @GetMapping("/student/marks/detail/{studentInfoId}")
    public List<StudentMarksDTO> getStudentMarksByStudentInfoId(
            @PathVariable("studentInfoId") long studentInfoId){

        return studentMarksService.getStudentMarksByStudentId(studentInfoId);
    }

    //http://localhost:8080/studentInfo/1/studentMarks/1
    @GetMapping("/studentInfo/{studentInfoId}/studentMarks/{id}")
    public ResponseEntity<StudentMarksDTO> getStudentMarksById(
            @PathVariable("studentInfoId") long studentInfoId,
            @PathVariable("id") long studentMarksId){
        StudentMarksDTO dto = studentMarksService.getStudentMarksById(studentInfoId, studentMarksId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/studentInfo/1/studentMarks/1
    @PutMapping("/studentInfo/{studentInfoId}/studentMarks/{id}")
    public ResponseEntity<StudentMarksDTO> updateStudentMarks(
            @PathVariable("studentInfoId") long studentInfoId,
            @PathVariable("id") long id,
            @RequestBody StudentMarksDTO studentMarksDTO){

        StudentMarksDTO dto = studentMarksService.updateStudentMarks(studentInfoId, id, studentMarksDTO);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/studentInfo/1/studentMarks/1
    @DeleteMapping("/studentInfo/{studentInfoId}/studentMarks/{id}")
    public ResponseEntity<String> deleteStudentMarks(
            @PathVariable("studentInfoId") long studentInfoId,
            @PathVariable("id") long id){

        studentMarksService.deleteStudentMarks(studentInfoId, id);

        return new ResponseEntity<>("StudentMarks are deleted", HttpStatus.OK);
    }
}