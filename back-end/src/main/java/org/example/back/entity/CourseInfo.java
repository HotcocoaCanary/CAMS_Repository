package org.example.back.entity;

import lombok.Data;

@Data

public class CourseInfo {
    private String studentId;
    private String courseId;
    private String courseName;
    private Double credits;
    private Double score;
    private String semester;


}
