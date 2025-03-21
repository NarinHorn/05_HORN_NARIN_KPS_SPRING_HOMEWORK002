package org.kps.schoolmanagement.service.impl;

import org.kps.schoolmanagement.exeception.NotFoundExceptionHandler;
import org.kps.schoolmanagement.model.dto.request.StudentRequest;
import org.kps.schoolmanagement.model.entity.Course;
import org.kps.schoolmanagement.model.entity.Student;
import org.kps.schoolmanagement.repository.CourseRepository;
import org.kps.schoolmanagement.repository.StudentRepository;
import org.kps.schoolmanagement.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> getAllStudent(Integer offset, Integer limit) {

        return studentRepository.findAllStudent(offset, limit);
    }

    @Override
    public Student getStudentById(Integer id) {

        Student student = studentRepository.findStudentById(id);

        if(student == null) {
            throw new NotFoundExceptionHandler("Student ID " + id + " not found");
        }

        return student;
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {

        // check course id that want to create with student exist or not found
        for (Integer courseId : studentRequest.getCourseId()) {
            Course course = courseRepository.findCourseById(courseId);

            // if one of course id is not found, throw exception
            if(course == null) {
                throw new NotFoundExceptionHandler("Course with ID " + courseId + " not found.");
            }
        }

        // all course id is found, so call insert student
        Integer studentId = studentRepository.insertStudent(studentRequest);

        // insert student_course
        for (Integer courseId: studentRequest.getCourseId()) {
            studentRepository.insertStudentCourse(studentId, courseId);
        }

        return studentRepository.findStudentById(studentId);
    }

    @Override
    public Student removeStudent(Integer id) {

        // check student id exist or not found
        Student student = studentRepository.findStudentById(id);

        if(student == null) {
            throw new NotFoundExceptionHandler("Student ID " + id + " not found");
        }

        // remove from table student_course
        studentRepository.removeStudentCourse(id);

        // remove from table students
        return studentRepository.removeStudent(id);
    }

    @Override
    public Student updateStudent(Integer id, StudentRequest studentRequest) {

        // check student id exist or not found
        Student student = studentRepository.findStudentById(id);

        if(student == null) {
            throw new NotFoundExceptionHandler("Student ID " + id + " not found");
        }

        // check course id that want to update exist or not found
        for (Integer courseId : studentRequest.getCourseId()) {
            Course course = courseRepository.findCourseById(courseId);

            // if one of course id is not found, throw exception
            if(course == null) {
                throw new NotFoundExceptionHandler("Course with ID " + courseId + " not found.");
            }
        }

        // delete existing records of student_course with above student id
        studentRepository.removeStudentCourseByStudentId(id);

        // update student_course by insert new record of student_course by that above student id
        for (Integer courseId: studentRequest.getCourseId()) {
            studentRepository.insertStudentCourse(id, courseId);
        }

        // update student by insert new record by that id
        return studentRepository.updateStudent(id, studentRequest);
    }
}
