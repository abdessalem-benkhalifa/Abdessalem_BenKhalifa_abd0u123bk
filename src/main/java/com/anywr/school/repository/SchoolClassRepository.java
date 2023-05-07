package com.anywr.school.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.school.entity.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Optional<SchoolClass> findByName(String name);
}
