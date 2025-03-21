package org.kps.schoolmanagement.service;

import org.kps.schoolmanagement.model.dto.request.InstructorRequest;
import org.kps.schoolmanagement.model.entity.Instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructor(Integer offset, Integer limit);

    Instructor getInstructorById(Integer id);

    Instructor createInstructor(InstructorRequest instructorRequest);

    Instructor updateInstructor(Integer id, InstructorRequest instructorRequest);

    Instructor removeInstructor(Integer id);
}
