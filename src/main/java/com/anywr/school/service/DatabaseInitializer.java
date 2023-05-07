package com.anywr.school.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.entity.Teacher;
import com.anywr.school.models.ERole;
import com.anywr.school.models.Role;
import com.anywr.school.models.User;
import com.anywr.school.repository.RoleRepository;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.StudentRepository;
import com.anywr.school.repository.TeacherRepository;
import com.anywr.school.repository.UserRepository;


@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseInitializer(StudentRepository studentRepository, TeacherRepository teacherRepository,
            SchoolClassRepository schoolClassRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	//sample dataset for testing rest api from postman
    	//I will comment it to not impact Junit tetss
    	/*
        // Insert some initial data into the teacher table
        Teacher teacher1 = new Teacher("Amine", "Mahfoudh");
        Teacher teacher2 = new Teacher("Chaouki", "Chalghoumi");
        Teacher teacher3 = new Teacher("Nizar", "Karmani");
        Teacher teacher4 = new Teacher("Noureddine", "BenYalaa");
        Teacher teacher5 = new Teacher("ElAmine", "Ouraiba");
        Teacher teacher6 = new Teacher("Zied", "Becheich");
        teacherRepository.saveAll(Arrays.asList(teacher1, teacher2,teacher3, teacher4,teacher5, teacher6));

        // Insert some initial data into the school_class table
        SchoolClass class1 = new SchoolClass("Class Data TAC", teacher1);
        SchoolClass class2 = new SchoolClass("Class Claim TAC", teacher2);
        SchoolClass class3 = new SchoolClass("Class Deputy", teacher3);
        SchoolClass class4 = new SchoolClass("Class Digital", teacher4);
        SchoolClass class5 = new SchoolClass("Class Integration", teacher5);
        SchoolClass class6 = new SchoolClass("Class Claim", teacher6);
        schoolClassRepository.saveAll(Arrays.asList(class1, class2,class3, class4,class5, class6));
        
        // Insert some initial data into the student table
        Student student1 = new Student("Jaweher", "Kadri", class1);
        Student student2 = new Student("Ilyesse", "Bdioui", class1);
        
        Student student3 = new Student("Oussama", "Dhaou", class2);
        Student student4 = new Student("Yosra", "Bouchiba", class2);
        
        Student student5 = new Student("Ammar", "Sassi", class3);
        Student student6 = new Student("Ahmed", "Limam", class3);
        
        Student student7 = new Student("Oussama", "Yahyaoui", class4);
        Student student8 = new Student("Dhia", "Tlili", class4);
        
        Student student9 = new Student("Ferdaws", "Rabah", class5);
        Student student10 = new Student("Wassim", "BenFatma", class5);

        Student student11 = new Student("Eya", "Megdiche", class6);
        Student student12 = new Student("Alya", "Tizzaoui", class6);
        studentRepository.saveAll(Arrays.asList(student1, student2, student3,student4, student5, student6,student7, student8, student9,student10, student11, student12));
    
     // Insert some initial data into the roles table
        Role role_user = new Role(1, ERole.ROLE_USER);
        Role role_moderator = new Role(2, ERole.ROLE_MODERATOR);
        Role role_admin = new Role(3, ERole.ROLE_ADMIN);
        roleRepository.saveAll(Arrays.asList(role_user, role_moderator, role_admin));
        Set<Role> roles_user = new HashSet<Role>();
        roles_user.add(role_user);
        
        Set<Role> roles_mod = new HashSet<Role>();
        roles_mod.add(role_moderator);
        
        Set<Role> roles_adm = new HashSet<Role>();
        roles_adm.add(role_admin);
        
     // Insert some initial data into the users table
        
        User user1 = new User("user1", "user1@gmail.com", "user1", roles_user);
        User user2 = new User("user2", "user2@gmail.com", "user2", roles_user);
        
        User mod1 = new User("moderator1", "moderator1@gmail.com", "moderator1", roles_mod);
        User mod2 = new User("moderator2", "moderator2@gmail.com", "moderator2", roles_mod);
        
        User admin1 = new User("admin1", "admin1@gmail.com", "admin1", roles_adm);
        User admin2 = new User("admin2", "admin2@gmail.com", "admin2", roles_adm);
        userRepository.saveAll(Arrays.asList(user1, user2, mod1, mod2,admin1, admin2));
        */
        //sample dataset mainly for junit testing
        Teacher TeachJunit1 = new Teacher("Teacher1_firtName", "Teacher1_lastName");
        Teacher TeachJunit2 = new Teacher("Teacher2_firtName", "Teacher2_lastName");
        Teacher TeachJunit3 = new Teacher("Teacher3_firtName", "Teacher3_lastName");
        teacherRepository.saveAll(Arrays.asList(TeachJunit1, TeachJunit2,TeachJunit3));

        SchoolClass classJunit1 = new SchoolClass("Class A", TeachJunit1);
    	SchoolClass classJunit2 = new SchoolClass("Class B", TeachJunit2);
    	SchoolClass classJunit3 = new SchoolClass("Class C", TeachJunit3);
    	schoolClassRepository.saveAll(Arrays.asList(classJunit1, classJunit2, classJunit3));
    	
    	Student studentJunit1 = new Student("John", "Doe", classJunit1);
    	Student studentJunit2 = new Student("Jane", "Doe", classJunit1);
    	Student studentJunit3 = new Student("Bob", "Smith", classJunit2);
    	Student studentJunit4 = new Student("Alice", "Johnson", classJunit3);
    	
        studentRepository.saveAll(Arrays.asList(studentJunit1, studentJunit2, studentJunit3, studentJunit4));
    }
}
