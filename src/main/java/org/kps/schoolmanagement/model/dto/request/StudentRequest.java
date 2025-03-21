package org.kps.schoolmanagement.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Integer> courseId;
}
