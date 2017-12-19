package com.dong.eureka.hystrix;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class EurekaClient1HystrixCommand extends HystrixCommand<String>	{

	//Pass RestTemplate to the constructor argument
	private final RestTemplate restTemplate;
	private final String url;

	private static final String TEST2="Test2";

	
	public EurekaClient1HystrixCommand(String commandKey, String url, RestTemplate restTemplate) {

		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"))
				.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey)));
		
		this.url = url;
		this.restTemplate = restTemplate;
	}

	@Override
	protected String run() throws Exception {

		String name;
		System.out.println("\n Run method\n" + new java.util.Date());
		System.out.println(this.commandKey + " is Allow  " + this.circuitBreaker.allowRequest());
		System.out.println(this.commandKey + " is Open   " + this.circuitBreaker.isOpen());

		
		name = restTemplate.getForObject(url, String.class);
					
	
		return name;
	}

	@Override
	protected String getFallback() {
		
		System.out.println("\nIt is fallback method   " + new java.util.Date());
		System.out.println(this.commandKey + " is Allow  " + this.circuitBreaker.allowRequest());
		System.out.println(this.commandKey + " is Open   " + this.circuitBreaker.isOpen());
		
		return "XXXXXXXXXXXXXXX   It is in Fallback method";
	}	
	
}
