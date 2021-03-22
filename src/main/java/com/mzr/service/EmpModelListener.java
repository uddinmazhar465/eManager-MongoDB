package com.mzr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.mzr.model.Employee;

@Component
public class EmpModelListener extends AbstractMongoEventListener<Employee> {
	@Autowired
    private EmployeeService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Employee.SEQUENCE_NAME));
        }
    }
}
