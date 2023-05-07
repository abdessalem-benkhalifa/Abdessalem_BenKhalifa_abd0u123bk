package com.anywr.school.service;


import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import com.anywr.school.dto.SchoolClassDTO;
import com.anywr.school.dto.StudentDTO;
import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.StudentRepository;
import com.anywr.school.repository.TeacherRepository;
import com.anywr.school.entity.Teacher;

@Service
public class SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public Page<SchoolClassDTO> getClasses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return schoolClassRepository.findAll(pageable).map(this::convertToDTO);
    }

    private SchoolClassDTO convertToDTO(SchoolClass schoolClass) {
    	SchoolClassDTO classDTO = new SchoolClassDTO();
        classDTO.setId(schoolClass.getId());
        classDTO.setName(schoolClass.getName());
        classDTO.setTeacherFullName(schoolClass.getTeacher().getFullName());
        return classDTO;
    }
}

