package org.kps.schoolmanagement.service;

import org.kps.schoolmanagement.model.dto.request.CourseRequest;
import org.kps.schoolmanagement.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse(Integer offset, Integer limit);

    Course getCourseById(Integer id);

    Course createCourse(CourseRequest courseRequest);

    Course updateCourse(Integer id, CourseRequest courseRequest);

    Course removeCourse(Integer id);
}
