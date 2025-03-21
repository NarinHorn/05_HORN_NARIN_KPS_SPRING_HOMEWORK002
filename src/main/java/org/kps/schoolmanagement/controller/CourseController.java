package org.kps.schoolmanagement.controller;

import org.kps.schoolmanagement.model.dto.request.CourseRequest;
import org.kps.schoolmanagement.model.dto.response.ApiResponse;
import org.kps.schoolmanagement.model.entity.Course;
import org.kps.schoolmanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourse(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {

        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .httpStatus(HttpStatus.OK)
                .message("All courses have been successfully fetched.")
                .timeStamp(LocalDateTime.now())
                .payload(courseService.getAllCourse(offset, limit))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Integer id) {

        Course course = courseService.getCourseById(id);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully founded.")
                .payload(course)
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseRequest courseRequest) {

        Course course = courseService.createCourse(courseRequest);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully added.")
                .payload(course)
                .httpStatus(HttpStatus.CREATED)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Integer id, @RequestBody CourseRequest courseRequest) {

        // update instructor
        Course course = courseService.updateCourse(id, courseRequest);

        // get response
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully updated.")
                .payload(course)
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        // return response
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> removeCourse(@PathVariable Integer id) {

        // remove instructor
        Course course = courseService.removeCourse(id);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully removed.")
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
