package com.mzr.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mzr.dto.EmployeeDTO;
@Component("eValidate")
public class EmpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return EmployeeDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeDTO eDto = null;
		eDto = (EmployeeDTO) target;
		
		if(eDto.getName()==null||eDto.getName().isEmpty()) {
			errors.rejectValue("name","NotEmpty");
		}
		if(eDto.getAddress()==null||eDto.getAddress().isEmpty()) {
			errors.rejectValue("address","NotEmpty");
		}
		if(eDto.getEmail()==null||eDto.getEmail().isEmpty()) {
			errors.rejectValue("email","NotEmpty");
		}
		if(eDto.getPhone()==null) {
			errors.rejectValue("phone","NotEmpty");
		}
		if(eDto.getPassword()==null||eDto.getPassword().isEmpty()) {
			errors.rejectValue("password","NotEmpty");
		}
		if(eDto.getProjCount()==null) {
			errors.rejectValue("projCount","NotEmpty");
		}
	}
	
}
