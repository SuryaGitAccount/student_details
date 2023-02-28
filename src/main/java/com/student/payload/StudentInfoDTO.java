package com.student.payload;

import lombok.Data;

@Data
public class StudentInfoDTO {

    private Long id;

    private String name;

    private String address;

    private String email;

    private String phoneNo;

    private String section;

    private String grade;

    private StudentMarksDTO studentMarks;

}


