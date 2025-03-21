package org.kps.schoolmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Course> courses;
}
