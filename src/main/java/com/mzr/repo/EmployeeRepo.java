package com.mzr.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mzr.model.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

}
