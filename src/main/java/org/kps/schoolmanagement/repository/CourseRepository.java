package org.kps.schoolmanagement.repository;

import org.apache.ibatis.annotations.*;
import org.kps.schoolmanagement.model.dto.request.CourseRequest;
import org.kps.schoolmanagement.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseRepository {

    // findAllCourse
    @Select("""
        select * from courses
        offset #{limit} * (#{offset} - 1)
        limit #{limit}
    """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "org.kps.schoolmanagement.repository.InstructorRepository.findInstructorById")
            )
    })
    List<Course> findAllCourse(Integer offset, Integer limit);

    // findCourseById
    @Select("""
        select * from courses where course_id = #{id}
    """)
    @ResultMap("courseMapper")
    Course findCourseById(Integer id);

    // insertCourse
    @Select("""
        insert into courses(course_name, description, instructor_id)
        values (#{course.courseName}, #{course.description}, #{course.instructorId})
        returning *
    """)
    @ResultMap("courseMapper")
    Course insertCourse(@Param("course") CourseRequest courseRequest);

    // updateCourse
    @Select("""
        update courses
        set course_name = #{course.courseName}, description = #{course.description}, instructor_id = #{course.instructorId}
        where course_id = #{id}
        returning *
    """)
    @ResultMap("courseMapper")
    Course updateCourse(Integer id, @Param("course") CourseRequest courseRequest);

    // removeCourse
    @Select("""
        delete from courses where course_id = #{id}
    """)
    @ResultMap("courseMapper")
    Course removeCourse(Integer id);
}
