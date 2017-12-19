package com.dong.eureka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dong.eureka.hystrix.EurekaClient1HystrixCommand;

@Service
public class EurekaClient1Service {

	@Autowired
	RestTemplate restTemplate;
	
	public String toRead1()	{
		String bookName = null;
		//call microservice using application name not actual URL
		String url = "http://eureka-client2/recommended/";
		EurekaClient1HystrixCommand ec1HystrixCommand = new EurekaClient1HystrixCommand("T2st1", url, restTemplate);
		
		bookName = ec1HystrixCommand.execute();
		
		return bookName;
	}
	
	public String toRead2()	{
		String bookName = null;
		//call microservice using application name not actual URL
		//this hystrix confituraion fallback is false
		String url = "http://eureka-client2/recommended2/";
		EurekaClient1HystrixCommand ec1HystrixCommand = new EurekaClient1HystrixCommand("Test2", url, restTemplate);
		try	{
			bookName = ec1HystrixCommand.execute();
		}	catch (Exception e)	{
			System.out.println("Hystrix fallback is set to false");
		}
		
		return bookName;
	}
	
	
}
