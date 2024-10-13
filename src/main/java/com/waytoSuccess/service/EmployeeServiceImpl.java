package com.waytoSuccess.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.waytoSuccess.dto.EmployeeDto;
import com.waytoSuccess.entity.Employee;
import com.waytoSuccess.exception.ResourceNotFoundException;
import com.waytoSuccess.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public List<EmployeeDto> fetchAllEmployee() {
		
		List<Employee> findAll = empRepo.findAll();
		
		List<EmployeeDto> listOfDto = findAll.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
		
		return listOfDto;
	}

	
	
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = mapToEntity(employeeDto);
		Employee save = empRepo.save(employee);
		
		EmployeeDto dto = mapToDto(save);
		return dto;
	}

	//Find Employee ById.......
	@Override
	public EmployeeDto findEmployeeById(Integer id) {
		Employee employee = empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Does Not Exist by this id :"+ id));
		EmployeeDto employeeDto = mapToDto(employee);
		return employeeDto;
	}
	
	
	@Override
	public EmployeeDto updateEmployeeRecord(Integer id, EmployeeDto dto) {
	    // Find the existing employee by ID
	    Employee employee = empRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User Does Not Exist by this id: " + id));
	    
	   
	    Employee updatedEmployee = mapToEntity(dto);
	    
	    // Optionally, you can set the ID from the existing employee to maintain the same entity
	    updatedEmployee.setId(employee.getId());
	    
	    Employee savedEmployee = empRepo.save(updatedEmployee);
	    return mapToDto(savedEmployee);
	}
	
	
	//deleteEmployee...................................
	@Override
	public void deleteEmployee(Integer id) {
		Employee employee = empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Does Not Exist By This Id : " + id));
		empRepo.delete(employee);
		
	}

	// Mapping methods remain unchanged
	Employee mapToEntity(EmployeeDto dto) {
	    return modelMapper.map(dto, Employee.class);
	}

	EmployeeDto mapToDto(Employee employee) {
	    return modelMapper.map(employee, EmployeeDto.class);
	}




}
