package org.kps.schoolmanagement.service.impl;

import org.kps.schoolmanagement.exeception.NotFoundExceptionHandler;
import org.kps.schoolmanagement.model.dto.request.InstructorRequest;
import org.kps.schoolmanagement.model.entity.Instructor;
import org.kps.schoolmanagement.repository.InstructorRepository;
import org.kps.schoolmanagement.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructor(Integer offset, Integer limit) {
        return instructorRepository.findAllInstructor(offset, limit);
    }

    @Override
    public Instructor getInstructorById(Integer id) {

        Instructor instructor = instructorRepository.findInstructorById(id);

        if(instructor == null) {
            throw new NotFoundExceptionHandler("Instructor ID " + id + " not found");
        }

        return instructor;
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest) {

        return instructorRepository.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor updateInstructor(Integer id, InstructorRequest instructorRequest) {

        // check if id exist or not found
        Instructor instructor = instructorRepository.findInstructorById(id);

        if(instructor == null) {
            throw new NotFoundExceptionHandler("Instructor ID " + id + " not found");
        }

        // update and return
        return instructorRepository.updateInstructor(id, instructorRequest);
    }

    @Override
    public Instructor removeInstructor(Integer id) {
        // check if id exist or not found
        Instructor instructor = instructorRepository.findInstructorById(id);

        if(instructor == null) {
            throw new NotFoundExceptionHandler("Instructor ID " + id + " not found");
        }

        // remove and return
        return instructorRepository.removeInstructor(id);
    }
}
