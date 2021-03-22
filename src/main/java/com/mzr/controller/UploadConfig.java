package com.mzr.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Component
@Configuration
public class UploadConfig {
	@Bean("multipartResolver")
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
}
