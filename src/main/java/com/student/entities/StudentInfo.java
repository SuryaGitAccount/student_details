package com.student.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_info")
public class StudentInfo {

    @Id

    private Long id;

    private String name;

    private String address;

    private String email;

    private String phoneNo;

    private String section;

    private String grade;

    @OneToOne(mappedBy = "studentInfo",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private StudentMarks studentMarks;
}