package com.anywr.school.dto;

public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String className;
    private String teacherFullName;
    
    public StudentDTO() {
        // default constructor needed for serialization
    }

    public StudentDTO(Long id, String firstName, String lastName, String className, String teacherFullName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.teacherFullName = teacherFullName;
    }
    
    // getters and setters
    
    
	public void setId(Long id) {
		this.id = id;
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

	public String getClassName() {
		return className;
	}

	public String getTeacherFullName() {
		return teacherFullName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setTeacherFullName(String teacherFullName) {
		this.teacherFullName = teacherFullName;
	}
    
    
}
