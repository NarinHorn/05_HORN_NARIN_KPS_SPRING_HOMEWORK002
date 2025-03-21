package org.kps.schoolmanagement.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.kps.schoolmanagement.model.dto.request.InstructorRequest;
import org.kps.schoolmanagement.model.entity.Instructor;
import org.kps.schoolmanagement.model.dto.response.ApiResponse;
import org.kps.schoolmanagement.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Online Learning Platform", version = "1.0", description = "Education is the spark that ignites curiosity and fuels lifelong learning, transcending the mere accumulation of knowledge."))
@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {

        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .httpStatus(HttpStatus.OK)
                .message("All instructors have been successfully fetched.")
                .timeStamp(LocalDateTime.now())
                .payload(instructorService.getAllInstructor(offset, limit))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Integer id) {

        Instructor instructor = instructorService.getInstructorById(id);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully founded.")
                .payload(instructor)
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorRequest instructorRequest) {

        Instructor instructor = instructorService.createInstructor(instructorRequest);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully added.")
                .payload(instructor)
                .httpStatus(HttpStatus.CREATED)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Integer id, @RequestBody InstructorRequest instructorRequest) {

        // update instructor
        Instructor instructor = instructorService.updateInstructor(id, instructorRequest);

        // get response
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully updated.")
                .payload(instructor)
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        // return response
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> removeInstructor(@PathVariable Integer id) {

        // remove instructor
        Instructor instructor = instructorService.removeInstructor(id);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully removed.")
                .httpStatus(HttpStatus.OK)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
