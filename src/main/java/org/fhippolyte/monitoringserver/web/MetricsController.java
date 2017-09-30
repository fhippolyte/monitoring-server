package org.fhippolyte.monitoringserver.web;

import java.net.URI;

import org.fhippolyte.monitoringserver.domain.Metrics;
import org.fhippolyte.monitoringserver.service.MetricsPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MetricsController {

	@RequestMapping(value="/metrics/{type}", method=RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<?> addMetrics(@PathVariable String type, @RequestBody Metrics metrics) {
		MetricsPersistence.getInstance().addMetrics(type, metrics);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(type).toUri();
		
		return ResponseEntity.created(location).build();
    }
	
	@RequestMapping(value="/metrics/{type}", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Metrics> getMetrics(@PathVariable String type) {
		
		Metrics metrics = MetricsPersistence.getInstance().getMetrics(type);
		if (metrics==null){
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(metrics);
		}
    }
}
