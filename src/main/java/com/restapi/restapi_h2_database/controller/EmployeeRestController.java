package com.restapi.restapi_h2_database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.restapi_h2_database.model.Employee;
import com.restapi.restapi_h2_database.repository.EmployeeRepository;

@RestController
@RequestMapping("/app")
@ResponseBody
public class EmployeeRestController {
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeRepository.save(employee), HttpStatus.CREATED);
		
	}
    @GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();

	}
    @GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getCustomer(@PathVariable Integer id)
	{
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if (employee.isPresent()) 
		{
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    @DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer id) 
    {
		
		try 
		{
			Optional<Employee> employee = employeeRepository.findById(id);
			
			if (employee.isPresent()) 
			{
				employeeRepository.delete(employee.get());
			}
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
    @PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		
		try 
		{
			return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	



    
}
