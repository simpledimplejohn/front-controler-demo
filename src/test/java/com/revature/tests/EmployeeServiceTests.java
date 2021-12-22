package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {
	private EmployeeDAO mockdao;
	private EmployeeService eserv;

	@Before
	public void setup() {
		mockdao = mock(EmployeeDAO.class);
		eserv = new EmployeeService(mockdao); // constructor injection
	}

	@After
	public void teardown() {
		mockdao = null;
		eserv = null;
	}

	// happy pass scenario
	@Test
	public void testConfirmLogin_sucess() {
		// create fake database of Employee objects
		Employee e1 = new Employee(3, "gfk", "gkl", "fghd", "ghfdjk");
		Employee e2 = new Employee(5, "gf", "ged", "fghd", "ggfjk");

		// add to list
		List<Employee> emps = new ArrayList();
		emps.add(e1);
		emps.add(e2);
		// when mockdao's findAll() method is called it returns dummy list

		when(mockdao.findAll()).thenReturn(emps);
		// use assert equals on the eserv.confirmLogin method returns correct user
		// when we call confirm loggin, we return the Employee
		assertEquals(e2, eserv.confirmLogin("fghd", "ggfjk"));
	}

	@Test
	public void testConfirmLoginFail() {
		// create fake database of Employee objects
		Employee e1 = new Employee(3, "gfk", "gkl", "fghd", "ghfdjk");
		Employee e2 = new Employee(5, "gf", "ged", "fghd", "ggfjk");

		// add to list
		List<Employee> emps = new ArrayList();
		emps.add(e1);
		emps.add(e2);
		// when mockdao's findAll() method is called it returns dummy list

		when(mockdao.findAll()).thenReturn(emps);
		// use assert equals on the eserv.confirmLogin method returns correct user
		// when we call confirm loggin, we return the Employee
		assertNull(eserv.confirmLogin("fghd", "ggf****jk"));
	}

}
