package com.anywr.school.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import com.anywr.school.dto.StudentDTO;
import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.StudentRepository;
import com.anywr.school.repository.TeacherRepository;

import java.util.Collections;

import org.slf4j.Logger;

import com.anywr.school.entity.Teacher;
import com.anywr.school.logging.LogbackConfig;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherRepository teacherRepository;
    
    private final Logger logger = LogbackConfig.logger();
    

    public StudentService(StudentRepository studentRepository, SchoolClassRepository schoolClassRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.teacherRepository = teacherRepository;
    }

    public StudentDTO getStudentById(Long id) {
    	StudentDTO studentDTO = null;
    	Optional<Student> student = studentRepository.findById(id);
    	if (student.isPresent()) return convertToDTO(student.get(), null, null);
    	return studentDTO;
    }
    
    public Page<StudentDTO> getStudents(String className, String teacherFullName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName", "lastName"));
        Page<StudentDTO> studentDTOPage = new PageImpl<>(Collections.emptyList());;
        if (className == null && teacherFullName == null) {
        	return studentRepository.findAll(pageable).map(student -> convertToDTO(student, null, null));
        }
        
        if (className != null && teacherFullName != null) {
            Optional<SchoolClass> schoolClass = schoolClassRepository.findByName(className);
            Optional<Teacher> teacher = findTeacherByName(teacherFullName);
            if (schoolClass.isPresent() && teacher.isPresent()) {
            	List<Student> students = studentRepository.findBySchoolClass_NameAndSchoolClass_Teacher_FirstNameAndSchoolClass_Teacher_LastName(
                        className, teacher.get().getFirstName(), teacher.get().getLastName(), pageable);
            	if (students.size() != 0) {
            		List<StudentDTO> studentDTOs = new ArrayList<>();
            		for(Student student : students) {
                	    StudentDTO studentDTO = convertToDTO(student, className, teacherFullName);
                	    studentDTOs.add(studentDTO);
                	}
                	studentDTOPage = new PageImpl<>(studentDTOs, pageable, studentDTOs.size()); 
            	}
            	
            }
            return studentDTOPage;
        } else if (className != null) {
            Optional<SchoolClass> schoolClass = schoolClassRepository.findByName(className);
            if (schoolClass.isPresent()) {
            	List<Student> students = studentRepository.findBySchoolClass_Name(className, pageable);
            	
            	if (students.size() != 0) {
            		List<StudentDTO> studentDTOs = new ArrayList<>();
            		for(Student student : students) {
                	    StudentDTO studentDTO = convertToDTO(student, className, teacherFullName);
                	    studentDTOs.add(studentDTO);
                	}
                	studentDTOPage = new PageImpl<>(studentDTOs, pageable, studentDTOs.size()); 
            	}
            	            	          	
                /*return studentRepository.findBySchoolClass_Name(className, pageable)
                        .map(student -> convertToDTO(student, className, schoolClass.get().getTeacher().getFullName()));
                        */
            }
            return studentDTOPage;
        } else if (teacherFullName != null) {
        	Optional<Teacher> teacher = findTeacherByName(teacherFullName);
            if (teacher.isPresent()) {
            	List<Student> students = studentRepository.findBySchoolClass_Teacher_Id(teacher.get().getId(), pageable);
            	if (students.size() != 0) {
            		List<StudentDTO> studentDTOs = new ArrayList<>();
            		for(Student student : students) {
                	    StudentDTO studentDTO = convertToDTO(student, className, teacherFullName);
                	    studentDTOs.add(studentDTO);
                	}
                	studentDTOPage = new PageImpl<>(studentDTOs, pageable, studentDTOs.size()); 
            	}
            }
        }
        return studentDTOPage;
    }

    private Optional<Teacher> findTeacherByName(String teacherFullName) {
        String[] names = teacherFullName.split("\\s+");
        if (names.length == 2) {
            return teacherRepository.findByFirstNameAndLastName(names[0], names[1]);
        }
        return Optional.empty();
    }

    private StudentDTO convertToDTO(Student student, String className, String teacherFullName) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setClassName(className != null ? className : student.getSchoolClass().getName());
        studentDTO.setTeacherFullName(teacherFullName != null ? teacherFullName : student.getSchoolClass().getTeacher().getFullName());
        return studentDTO;
    }

	public List<Student> saveAllStudents(List<Student> studentList) {
		// TODO Auto-generated method stub
		return studentRepository.saveAll(studentList);
	}

}

