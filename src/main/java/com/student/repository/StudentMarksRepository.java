package com.student.repository;

import com.student.entities.StudentInfo;
import com.student.entities.StudentMarks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentMarksRepository extends JpaRepository<StudentMarks, Long> {

    List<StudentMarks> findByStudentInfoId(long studentInfoId);
}
