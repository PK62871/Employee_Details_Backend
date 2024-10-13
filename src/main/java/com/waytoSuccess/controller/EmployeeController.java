package com.waytoSuccess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.waytoSuccess.dto.EmployeeDto;
import com.waytoSuccess.entity.Employee;
import com.waytoSuccess.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto dto){
		EmployeeDto employeeDto = employeeService.createEmployee(dto);
		
		return new ResponseEntity<EmployeeDto>(employeeDto,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> fetchAllEmployeerecord(){
		List<EmployeeDto> allEmployee = employeeService.fetchAllEmployee();
		return new ResponseEntity<>(allEmployee,HttpStatus.OK);
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id){
		EmployeeDto dto = employeeService.findEmployeeById(id);
		
		return new ResponseEntity<>(dto,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Integer id,@RequestBody EmployeeDto dto){
		
		EmployeeDto employeeDto = employeeService.updateEmployeeRecord(id,dto);
		
		return new ResponseEntity<EmployeeDto>(employeeDto,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee Deleted Successfully",HttpStatus.OK);
	}
	
}
