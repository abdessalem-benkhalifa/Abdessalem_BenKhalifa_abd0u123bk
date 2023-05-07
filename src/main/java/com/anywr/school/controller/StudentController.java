package com.anywr.school.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anywr.school.service.StudentService;
import com.anywr.school.dto.StudentDTO;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping("/students")
    //public ResponseEntity<List<Student>> getStudents(
    public ResponseEntity<Page<StudentDTO>> getStudents(
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String teacherFullName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        //List<Student> students = studentService.getStudents(className, teacherFullName, page, size);
        Page<StudentDTO> students = studentService.getStudents(className, teacherFullName, page, size);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentDTO);
        }
    }
    
    /*
    @PostMapping("/students/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
        student = studentService.createStudent(student);
        return ResponseEntity.ok(student);
    }
    
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student = studentService.updateStudent(id, student);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }
    
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */
}

