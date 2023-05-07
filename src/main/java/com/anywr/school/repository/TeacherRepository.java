package com.anywr.school.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.school.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}