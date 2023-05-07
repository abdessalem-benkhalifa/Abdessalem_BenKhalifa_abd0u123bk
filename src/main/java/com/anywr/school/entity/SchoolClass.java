package com.anywr.school.entity;

import javax.persistence.*;

@javax.persistence.Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    // getters and setters

	public SchoolClass(String name, Teacher teacher) {
		super();
		this.name = name;
		this.teacher = teacher;
	}
	
	public SchoolClass() {
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
    
    
}