package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Size(min=2, message = "Name should have alteast 2 characters")
	private String name;
	
	
	private String companyName;
	
	private float salary;
	
	public User(String name,String companyName, float salary,int id) {
		this.name = name;
		this.companyName =companyName;
		this.salary =salary;
		this.id = id;
	}
	
	

}
