package org.kps.schoolmanagement.service;

import org.kps.schoolmanagement.model.dto.request.StudentRequest;
import org.kps.schoolmanagement.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent(Integer offset, Integer limit);

    Student getStudentById(Integer id);

    Student createStudent(StudentRequest studentRequest);

    Student removeStudent(Integer id);

    Student updateStudent(Integer id, StudentRequest studentRequest);
}
