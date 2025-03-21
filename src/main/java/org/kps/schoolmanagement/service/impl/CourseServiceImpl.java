package org.kps.schoolmanagement.service.impl;

import org.kps.schoolmanagement.exeception.NotFoundExceptionHandler;
import org.kps.schoolmanagement.model.dto.request.CourseRequest;
import org.kps.schoolmanagement.model.entity.Course;
import org.kps.schoolmanagement.model.entity.Instructor;
import org.kps.schoolmanagement.repository.CourseRepository;
import org.kps.schoolmanagement.repository.InstructorRepository;
import org.kps.schoolmanagement.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    // declare dependency
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    // inject dependency
    public CourseServiceImpl(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse(Integer offset, Integer limit) {
        return courseRepository.findAllCourse(offset, limit);
    }

    @Override
    public Course getCourseById(Integer id) {

        Course course = courseRepository.findCourseById(id);

        if (course == null)
            throw new NotFoundExceptionHandler("Course with ID " + id + " not found.");

        return course;
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {

        // check instructor id exist or not found
        Instructor instructor = instructorRepository.findInstructorById(courseRequest.getInstructorId());

        if(instructor == null) {
            throw new NotFoundExceptionHandler("Instructor ID " + courseRequest.getInstructorId() + " not found");
        }

        // create course
        return courseRepository.insertCourse(courseRequest);
    }

    @Override
    public Course updateCourse(Integer id, CourseRequest courseRequest) {

        // check course id exist or not found
        Course course = courseRepository.findCourseById(id);
        if (course == null)
            throw new NotFoundExceptionHandler("Course with ID " + id + " not found.");

        // check instructor id exist or not found
        Instructor instructor = instructorRepository.findInstructorById(courseRequest.getInstructorId());
        if(instructor == null) {
            throw new NotFoundExceptionHandler("Instructor ID " + courseRequest.getInstructorId() + " not found");
        }

        // update course
        return courseRepository.updateCourse(id, courseRequest);
    }

    @Override
    public Course removeCourse(Integer id) {

        // check course id exist or not found
        Course course = courseRepository.findCourseById(id);
        if (course == null)
            throw new NotFoundExceptionHandler("Course with ID " + id + " not found.");

        return courseRepository.removeCourse(id);
    }
}
