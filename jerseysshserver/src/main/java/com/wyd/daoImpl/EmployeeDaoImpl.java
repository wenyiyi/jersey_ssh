package com.wyd.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wyd.dao.EmployeeDao;
import com.wyd.domain.Employee;

@Repository("employeeDao")
@Transactional 
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Employee load(int id) {
		return (Employee) this.getCurrentSession().load(Employee.class, id);
	}

	public Employee get(int id) {
		return (Employee) this.getCurrentSession().get(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		return (List<Employee>)this.getCurrentSession().createQuery("from Employee").setCacheable(true).list();
	}

	public void persist(Employee entity) {
		this.getCurrentSession().persist(entity);
	}

	public String save(Employee entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Employee entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		this.getCurrentSession().delete(this.load(id));
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

}
