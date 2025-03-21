package org.kps.schoolmanagement.repository;

import org.apache.ibatis.annotations.*;
import org.kps.schoolmanagement.model.dto.request.InstructorRequest;
import org.kps.schoolmanagement.model.entity.Instructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InstructorRepository {

    // findAllInstructor
    @Select("""
        select * from instructors
        offset #{limit} * (#{offset} - 1)
        limit #{limit}
    """)
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })
    List<Instructor> findAllInstructor(Integer offset, Integer limit);

    // findInstructorById
    @Select("""
        select * from Instructors where instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    Instructor findInstructorById(Integer id);

    // insertInstructor
    @Select("""
        insert into instructors(instructor_name, email)
        values (#{instructor.instructorName}, #{instructor.email})
        returning *
    """)
    @ResultMap("instructorMapper")
    Instructor insertInstructor(@Param("instructor") InstructorRequest instructorRequest);

    // updateInstructor
    @Select("""
        update instructors
        set instructor_name = #{instructor.instructorName}, email = #{instructor.email}
        where instructor_id = #{id}
        returning *
    """)
    @ResultMap("instructorMapper")
    Instructor updateInstructor(Integer id,@Param("instructor") InstructorRequest instructorRequest);

    // removeInstructor
    @Select("""
        delete from instructors where instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    Instructor removeInstructor(Integer id);
}
