package com.waytoSuccess.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.waytoSuccess.dto.EmployeeDto;
import com.waytoSuccess.entity.Employee;

@Component
public interface EmployeeService {

	public List<EmployeeDto> fetchAllEmployee();

	public EmployeeDto createEmployee(EmployeeDto employeeDto);

	public EmployeeDto findEmployeeById(Integer id);

	public EmployeeDto updateEmployeeRecord(Integer id, EmployeeDto dto);

	public void deleteEmployee(Integer id);
	
	 
}
