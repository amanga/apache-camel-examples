package com.bunge.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelJMSListener {

	public static void main(String args[]){
//		CamelContext camelContext =new  DefaultCamelContext();
		try{
			 ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			 
			 CamelContext camelContext = SpringCamelContext.springCamelContext(appContext,false);

			 /*camelContext.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					from("timer://myTimer?period=2000")
					.setBody()
				    .simple("Hello World Camel fired at ${header.firedTime}")
				    .to("stream:out");
				}
			});*/
			 try{
				 ProducerTemplate template = camelContext.createProducerTemplate();
			 
				 while(true){
					 camelContext.start();
//					 template.sendBody("jms:BUNGE.BUS.EMPLOYEE.TO.HRIS-AGGREGATION.QUEUE", "Hello World");
				 }
			 }finally{
				 camelContext.stop();
			 }
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
