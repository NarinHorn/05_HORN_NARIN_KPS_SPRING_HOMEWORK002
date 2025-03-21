package org.kps.schoolmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    private Integer instructorId;
    private String instructorName;
    private String email;
}
