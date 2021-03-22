package com.mzr.model;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class Employee {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private Long id;
	private String name;
	private String gender;
	private String address;
	private String email;
	private Long phone;
	private String password;
	private List<String> qualification;
	private List<String> planguage;
	@Autowired(required = false)
	private Integer projCount;
	private List<Project> project;
	private Binary image;
	@Autowired(required = false)
	private String imageStr;
	@Autowired(required = false)
	private String qrcode;

}
