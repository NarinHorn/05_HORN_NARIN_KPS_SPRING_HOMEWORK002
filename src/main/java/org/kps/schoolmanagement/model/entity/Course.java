package org.kps.schoolmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
