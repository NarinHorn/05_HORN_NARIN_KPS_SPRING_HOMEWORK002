package org.kps.schoolmanagement.repository;

import org.apache.ibatis.annotations.*;
import org.kps.schoolmanagement.model.dto.request.StudentRequest;
import org.kps.schoolmanagement.model.entity.Course;
import org.kps.schoolmanagement.model.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentRepository {

    // findAllStudent
    @Select("""
        select * from students
        offset #{limit} * (#{offset} - 1)
        limit #{limit}
    """)
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "getAllCourseByStudentId")
            )
    })
    List<Student> findAllStudent(Integer offset, Integer limit);

    // getAllCourseByStudentId
    @Select("""
        select * from courses c
        inner join student_course sc on c.course_id = sc.course_id
        where student_id = #{StudentId}
    """)
    @Results(id = "course", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "org.kps.schoolmanagement.repository.InstructorRepository.findInstructorById")
            )
    })
    List<Course> getAllCourseByStudentId(Integer StudentId);

    // findStudentById
    @Select("""
        select * from students where student_id = #{id}
    """)
    @ResultMap("studentMapper")
    Student findStudentById(Integer id);

    // insertStudent
    @Select("""
        insert into students(student_name, email, phone_number)
        values (#{student.studentName}, #{student.email}, #{student.phoneNumber})
        returning student_id;
    """)
    Integer insertStudent(@Param("student") StudentRequest studentRequest);

    @Select("""
        insert into student_course(student_id, course_id)
        values(#{studentId}, #{courseId})
    """)
    void insertStudentCourse(Integer studentId, Integer courseId);

    @Select("""
        delete from student_course where student_id = #{id}
    """)
    void removeStudentCourse(Integer id);

    @Select("""
        delete from students where student_id = #{studentId}
        returning *;
    """)
    @ResultMap("studentMapper")
    Student removeStudent(Integer studentId);

    @Select("""
        update students
        set student_name = #{student.studentName},
        email = #{student.email},
        phone_number = #{student.phoneNumber}
        where student_id = #{id}
        returning *
    """)
    @ResultMap("studentMapper")
    Student updateStudent(Integer id, @Param("student") StudentRequest studentRequest);

    @Select("""
        delete from student_course where student_id = #{id}
    """)
    void removeStudentCourseByStudentId(Integer id);
}
