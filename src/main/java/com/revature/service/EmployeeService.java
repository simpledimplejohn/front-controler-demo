package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;

// this handles buisness or application logic
// dao handles persistance logic (session and hibernate's session logic)

public class EmployeeService {
	
	private EmployeeDAO edao;
	// constructor injection
	// a type of dependency
	public EmployeeService(EmployeeDAO edao) {
		super();
		this.edao = edao;
	}
	
	// confirm login returns logged in employee that matches username and password
	
	public Employee confirmLogin(String username, String password) {
		// call findAll()
		Optional<Employee> possibleEmp = edao.findAll()
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
				return (possibleEmp.isPresent() ? possibleEmp.get() : null);
		// stream through records and find the matches

	}
	
	
	
	
	
	
	
	public List<Employee> findAll() {
		return edao.findAll();
	}
	
	
	
	public int insert(Employee e) {
		return edao.insert(e);
	}
	
	public boolean update(Employee e) {
		return edao.update(e);
	}
	public boolean delete(Employee e) {
		return edao.delete(e);
	}
	
	
}
