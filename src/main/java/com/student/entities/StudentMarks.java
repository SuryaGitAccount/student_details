package com.student.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_marks")
public class StudentMarks {

    @Id

    private long id;
    private String session;
    private String semester;
    private int totalSubjects;
    private int totalMarks;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private StudentInfo studentInfo;
}