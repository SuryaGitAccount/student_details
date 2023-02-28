package com.student.payload;

import lombok.Data;

@Data
public class StudentMarksDTO {

    private Long id;

    private String session;

    private String semester;

    private int totalSubjects;

    private int totalMarks;

}

