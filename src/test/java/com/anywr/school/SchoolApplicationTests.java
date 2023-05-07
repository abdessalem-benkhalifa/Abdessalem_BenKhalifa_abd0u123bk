package com.anywr.school;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.entity.Teacher;
import com.anywr.school.service.StudentService;

//@ContextConfiguration(classes = {})
@SpringBootTest
@AutoConfigureMockMvc
class SchoolApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentService studentService;

	private List<Student> studentList;
	
	@Before
	public void setUp() {
        // Initialize test data
        //studentList = new List<Student>();
    	SchoolClass class1 = new SchoolClass("Class A", new Teacher("Teacher1_firtName", "Teacher1_lastName"));
    	SchoolClass class2 = new SchoolClass("Class B", new Teacher("Teacher2_firtName", "Teacher2_lastName"));
    	SchoolClass class3 = new SchoolClass("Class C", new Teacher("Teacher3_firtName", "Teacher3_lastName"));
    	studentList.add(new Student("John", "Doe", class1) );
    	studentList.add(new Student("Jane", "Doe", class1) );
    	studentList.add(new Student("Bob", "Smith", class2));
        studentList.add(new Student("Alice", "Johnson", class3));
        studentList = studentService.saveAllStudents(studentList);
    }

    @Test
    public void testGetAllStudents() throws Exception {
    	mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.content", hasSize(4)))
                .andExpect(jsonPath("$.numberOfElements", is(4))) //totalElements
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                //.andExpect(jsonPath("$.first", is(true)))
                //.andExpect(jsonPath("$.last", is(true)))
                ;
    }

    @Test
    public void testGetStudentsByClassName() throws Exception {
    	mockMvc.perform(get("/api/students?className=Class A"))
                .andExpect(status().isOk())
               // .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.numberOfElements", is(2)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                ;
    }

    @Test
    public void testGetStudentsByTeacherFullName() throws Exception {
    	mockMvc.perform(get("/api/students?teacherFullName=Teacher1_firtName Teacher1_lastName"))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.numberOfElements", is(2)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                ;
    }

    @Test
    public void testGetStudentsByClassNameAndTeacherFullName() throws Exception {
    	mockMvc.perform(get("/api/students?className=Class A&teacherFullName=John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfElements", is(0)))
                ;
    }

    @Test
    public void testGetStudentsWithPagination() throws Exception {
    	mockMvc.perform(get("/api/students?page=0&size=2"))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.numberOfElements", is(2)))
                //.andExpect(jsonPath("$.content[0].id", is(2)))
                //.andExpect(jsonPath("$.content[0].firstName", is("Jane")))
                //.andExpect(jsonPath("$.content[1].id", is(1)))
                //.andExpect(jsonPath("$.content[1].name", is("John")))
                ;
    }

}