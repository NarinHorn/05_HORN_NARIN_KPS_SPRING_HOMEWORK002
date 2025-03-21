package org.kps.schoolmanagement.controller;

import org.kps.schoolmanagement.model.dto.request.CourseRequest;
import org.kps.schoolmanagement.model.dto.request.StudentRequest;
import org.kps.schoolmanagement.model.dto.response.ApiResponse;
import org.kps.schoolmanagement.model.entity.Course;
import org.kps.schoolmanagement.model.entity.Student;
import org.kps.schoolmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllCourse(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {

        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .httpStatus(HttpStatus.OK)
                .message("All students have been successfully fetched.")
                .timeStamp(LocalDateTime.now())
                .payload(studentService.getAllStudent(offset, limit))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Integer id) {

        Student student = studentService.getStudentById(id);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully founded.")
                .payload(student)
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest) {

        Student student = studentService.createStudent(studentRequest);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully added.")
                .payload(student)
                .httpStatus(HttpStatus.CREATED)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {

        Student student = studentService.updateStudent(id, studentRequest);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully updated.")
                .payload(student)
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> removeStudent(@PathVariable Integer id) {

        Student student = studentService.removeStudent(id);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .httpStatus(HttpStatus.OK)
                .message("The course has been successfully removed.")
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
