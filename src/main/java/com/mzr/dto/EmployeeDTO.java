package com.mzr.dto;

import java.util.List;

import org.bson.types.Binary;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Long id;
	private String name;
	private String gender;
	private String address;
	private String email;
	private Long phone;
	private String password;
	private List<String> qualification;
	private List<String> planguage;
	private Integer projCount;
	private List<ProjectDTO> project;
	private Binary image;
	private String imageStr;
	private String qrcode;

}
