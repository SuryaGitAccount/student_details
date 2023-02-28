package com.student.service;

import com.student.entities.StudentInfo;
import com.student.exception.ResourceNotFoundException;
import com.student.payload.StudentInfoDTO;
import com.student.repository.StudentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentInfoService {

    @Autowired
    private final StudentInfoRepository studentInfoRepository;

    public StudentInfoService(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    public StudentInfoDTO createStudentInfo(StudentInfoDTO studentInfoDTO){
        StudentInfo studentInfo = mapToEntity(studentInfoDTO);
        StudentInfo newStudentInfo = studentInfoRepository.save(studentInfo);

        StudentInfoDTO studentInfoResponnce= mapToDTO(newStudentInfo);
        return studentInfoResponnce;
    }

    public  StudentInfoDTO getStudentInfoById(long id){
        StudentInfo studentInfo=studentInfoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("studentInfo","id",id));
        return mapToDTO(studentInfo);
    }

    public StudentInfoDTO updateStudentInfo(StudentInfoDTO studentInfoDTO,long id){
        StudentInfo studentInfo=studentInfoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("studentInfo","id",id));

        studentInfo.setName(studentInfoDTO.getName());
        studentInfo.setAddress(studentInfoDTO.getAddress());
        studentInfo.setEmail(studentInfoDTO.getEmail());
        studentInfo.setPhoneNo(studentInfoDTO.getPhoneNo());
        studentInfo.setSection(studentInfoDTO.getSection());
        studentInfo.setGrade(studentInfoDTO.getGrade());
        StudentInfo updateStudentInfo= studentInfoRepository.save(studentInfo);
        return mapToDTO(updateStudentInfo);

    }
    public void deleteStudentInfoById(long id){
        StudentInfo studentInfo = studentInfoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("studentInfo","id",id));
        studentInfoRepository.delete(studentInfo);
    }
    private StudentInfoDTO mapToDTO(StudentInfo studentInfo){
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        studentInfoDTO.setId(studentInfo.getId());
        studentInfoDTO.setName(studentInfo.getName());
        studentInfoDTO.setAddress(studentInfo.getAddress());
        studentInfoDTO.setEmail(studentInfo.getEmail());
        studentInfoDTO.setPhoneNo(studentInfo.getPhoneNo());
        studentInfoDTO.setSection(studentInfo.getSection());
        studentInfoDTO.setGrade(studentInfo.getGrade());
        return studentInfoDTO;
    }

    private StudentInfo mapToEntity(StudentInfoDTO studentInfoDTO){

        StudentInfo studentInfo= new StudentInfo();
        studentInfo.setId(studentInfoDTO.getId());
        studentInfo.setName(studentInfoDTO.getName());
        studentInfo.setAddress(studentInfoDTO.getAddress());
        studentInfo.setEmail(studentInfoDTO.getEmail());
        studentInfo.setPhoneNo(studentInfoDTO.getPhoneNo());
        studentInfo.setSection(studentInfoDTO.getSection());
        studentInfo.setGrade(studentInfoDTO.getGrade());
        return studentInfo;

    }


}

