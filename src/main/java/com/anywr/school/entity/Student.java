package com.anywr.school.entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;
    // getters and setters

    public Student(String firstName, String lastName, SchoolClass schoolClass) {
    	super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.schoolClass = schoolClass;
	}
	
	public Student() {
		
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
    
    
}
