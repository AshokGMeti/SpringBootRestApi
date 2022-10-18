package com.restapi.restapi_h2_database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.restapi_h2_database.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

   
    
}
