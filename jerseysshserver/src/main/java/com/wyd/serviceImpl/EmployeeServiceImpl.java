package com.wyd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyd.dao.EmployeeDao;
import com.wyd.domain.Employee;
import com.wyd.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> findAll() {
		return employeeDao.findAll();
	}
	
}
