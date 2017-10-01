package org.fhippolyte.monitoringserver;

import org.fhippolyte.monitoringserver.service.MetricsPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class MonitoringserverApplication {

	
	@RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> healthCheck() {
		return ResponseEntity.ok().build();
    }
	
	
	
	public static void main(String[] args) {
		String securityKey = System.getenv().get("securityKey");
		if (securityKey!=null) {
			MetricsPersistence.getInstance().setSecurityKey(securityKey);
			SpringApplication.run(MonitoringserverApplication.class, args);
		} else {
			System.err.println("No security key provided, exit !");
		}		
	}
}
