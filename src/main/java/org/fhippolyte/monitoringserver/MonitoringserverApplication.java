package org.fhippolyte.monitoringserver;

import org.fhippolyte.monitoringserver.service.MetricsPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonitoringserverApplication {

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
