package com.student.service;

import com.student.entities.StudentInfo;
import com.student.entities.StudentMarks;
import com.student.exception.ResourceNotFoundException;
import com.student.exception.StudentApiException;
import com.student.payload.StudentMarksDTO;
import com.student.repository.StudentInfoRepository;
import com.student.repository.StudentMarksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMarksService {
    private StudentInfoRepository studentInfoRepository;
    private StudentMarksRepository studentMarksRepository;

    public StudentMarksService(StudentInfoRepository studentInfoRepository, StudentMarksRepository studentMarksRepository) {
        this.studentInfoRepository = studentInfoRepository;
        this.studentMarksRepository = studentMarksRepository;
    }

    public StudentMarksDTO createStudentMarks(long studentInfoId,StudentMarksDTO studentMarksDTO){
        StudentMarks studentMarks = mapToEntity(studentMarksDTO);
        StudentInfo  studentInfo= studentInfoRepository.findById(studentInfoId).orElseThrow(
                ()-> new ResourceNotFoundException("StudentInfo", "id",studentInfoId));
        studentMarks.setStudentInfo(studentInfo);
        StudentMarks newStudentMark = studentMarksRepository.save(studentMarks);
        StudentMarksDTO dto= mapToDto(newStudentMark);
        return dto;
    }

    public List<StudentMarksDTO> getStudentMarksByStudentId(long studentInfoId){

        StudentInfo studentInfo= studentInfoRepository.findById(studentInfoId).orElseThrow(
                ()-> new ResourceNotFoundException("studentInfo","id",studentInfoId)
        );
        List<StudentMarks> studentMarks = studentMarksRepository.findByStudentInfoId(studentInfoId);
        return studentMarks.stream().map(studentMark->mapToDto(studentMark)).collect(Collectors.toList());
    }
    public StudentMarksDTO getStudentMarksById(long studentInfoId,long studentMarksId){

        StudentInfo studentInfo= studentInfoRepository.findById(studentInfoId).orElseThrow(
                ()-> new ResourceNotFoundException("studentInfo","id",studentInfoId));

        StudentMarks studentMarks = studentMarksRepository.findById(studentMarksId).orElseThrow(
                ()-> new ResourceNotFoundException("studentMarks","id", studentMarksId)
        );

        if(studentMarks.getStudentInfo().getId()!= studentInfo.getId()){
            throw new StudentApiException(HttpStatus.BAD_REQUEST,"Student marks does not belongs to student info");
        }
        return mapToDto(studentMarks);
    }

    public StudentMarksDTO updateStudentMarks(long studentInfoId,long id,StudentMarksDTO studentMarksDTO){

        StudentInfo studentInfo= studentInfoRepository.findById(studentInfoId).orElseThrow(
                ()-> new ResourceNotFoundException("studentInfo","id",studentInfoId));
        StudentMarks studentMarks = studentMarksRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("studentMarks","id", id)
        );
        if(studentMarks.getStudentInfo().getId()!= studentInfo.getId()){
            throw new StudentApiException(HttpStatus.BAD_REQUEST,"student info not matching with comment");
        }
        studentMarks.setId(id);
        studentMarks.setSession(studentMarksDTO.getSession());
        studentMarks.setSemester(studentMarksDTO.getSemester());
        studentMarks.setTotalSubjects(studentMarksDTO.getTotalSubjects());
        studentMarks.setTotalMarks(studentMarksDTO.getTotalMarks());
        StudentMarks newstudentMarks = studentMarksRepository.save(studentMarks);
        return mapToDto(newstudentMarks);
    }

    public void deleteStudentMarks(long studentInfoId,long id){
        StudentInfo studentInfo= studentInfoRepository.findById(studentInfoId).orElseThrow(
                ()-> new ResourceNotFoundException("studentInfo","id",studentInfoId));
        StudentMarks studentMarks = studentMarksRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("studentMarks","id", id));

        if(studentMarks.getStudentInfo().getId()!= studentInfo.getId()){
            throw new StudentApiException(HttpStatus.BAD_REQUEST,"student not matching with student marks");
        }

        studentMarksRepository.deleteById(id);

    }

    private StudentMarksDTO mapToDto(StudentMarks newstudentMarks){
        StudentMarksDTO studentMarksDTO= new StudentMarksDTO();
        studentMarksDTO.setId(newstudentMarks.getId());
        studentMarksDTO.setSession(newstudentMarks.getSession());
        studentMarksDTO.setSemester(newstudentMarks.getSemester());
        studentMarksDTO.setTotalSubjects(newstudentMarks.getTotalSubjects());
        studentMarksDTO.setTotalMarks(newstudentMarks.getTotalMarks());
        return studentMarksDTO;
    }
    private StudentMarks mapToEntity(StudentMarksDTO studentMarksDTO){
        StudentMarks studentMarks= new StudentMarks();
        studentMarks.setSession(studentMarksDTO.getSession());
        studentMarks.setSemester(studentMarksDTO.getSemester());
        studentMarks.setTotalSubjects(studentMarksDTO.getTotalSubjects());
        studentMarks.setTotalMarks(studentMarksDTO.getTotalMarks());
        return studentMarks;


    }
}
