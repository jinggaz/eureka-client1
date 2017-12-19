package com.dong.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dong.eureka.service.EurekaClient1Service;

@RestController
public class EurekaClient1Controller {

	@Autowired
	EurekaClient1Service	productRemoteService;
	
	@RequestMapping(path = "/to-read")
	public String getToRead()	{
		String productInfo = productRemoteService.toRead1();
		return productInfo;
	}
	
	@RequestMapping(path = "/to-read2")
	public String getToRead2()	{
		String productInfo = productRemoteService.toRead2();
		return productInfo;
	}
}
