package com.anywr.school.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import com.anywr.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchoolClass_NameAndSchoolClass_Teacher_FirstNameAndSchoolClass_Teacher_LastName(
            String className, String teacherFirstName, String teacherLastName, Pageable pageable);
    
    List<Student> findBySchoolClass_Name(String className, Pageable pageable);
    
    List<Student> findBySchoolClass_Teacher_Id(Long teacherId, Pageable pageable);
    
}
