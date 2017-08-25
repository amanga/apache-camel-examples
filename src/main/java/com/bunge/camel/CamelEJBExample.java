package com.bunge.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bunge.bce.counterparty.client.CounterpartyDTO;

public class CamelEJBExample {

	public static void main(String[] args) {
		
		CounterpartyDTO _inCounterpartyDTO = new CounterpartyDTO();
		String source = "BNA";
		String legalId = "0050001897";
		
		_inCounterpartyDTO.setSource(source);
		_inCounterpartyDTO.setLegalId(legalId);
		_inCounterpartyDTO.setActive(true);
		
		 ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		 try {
			CamelContext camelContext = SpringCamelContext.springCamelContext(appContext,false);
			
			 camelContext.addRoutes(new RouteBuilder() {
			
				@Override
				public void configure() throws Exception {
					from("timer:foo?fixedRate=true&period=5000")
					.to("ejb:ejb/bce/Counterparty/CounterpartyReadServiceFacadeHome!ejb3.CounterpartyReadServiceFacadeRemote?method=getLegalAddress(_inCounterpartyDTO)")
				    .to("stream:out");
				}
			});
			System.out.println("sss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}

}
