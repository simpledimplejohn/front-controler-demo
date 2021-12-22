package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAO {

	public int insert(Employee e) {
		// grab sesssion
		Session ses = HibernateUtil.getSession();
		// begin tx
		Transaction tx = ses.beginTransaction();
		// capture the pk
		int pk = (int) ses.save(e);
		// commit
		tx.commit();
		// return pk
		return pk;
	}
	public List<Employee> findAll() {
		// grab sesssion
		Session ses = HibernateUtil.getSession();
		// make statment to return all records
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		// return list
		return emps;
		
	}
	public boolean update(Employee e) {
		return false;
	}
	public boolean delete(Employee e) {
		return false;
	}
}
























