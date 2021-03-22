package com.mzr.service;

import java.util.List;

import com.mzr.dto.EmployeeDTO;
import com.mzr.model.Employee;

public interface EmployeeService {
	
	public String insert(EmployeeDTO dto, String msg);
	public List<Employee> getAll();
	public Employee getById(Integer id);
	public String deleteById(Integer id);
	public long generateSequence(String seqName);
}
