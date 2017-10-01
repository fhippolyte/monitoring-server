package org.fhippolyte.monitoringserver.web;

import java.net.URI;
import java.util.List;

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

	@RequestMapping(value="/metrics/{id}", method=RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<?> addMetrics(@PathVariable String id, @RequestBody Metrics metrics) {
		MetricsPersistence.getInstance().addMetrics(id, metrics);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(id).toUri();
		
		return ResponseEntity.created(location).build();
    }
	
	@RequestMapping(value="/metrics/{id}", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Metrics> getMetrics(@PathVariable String id) {
		
		Metrics metrics = MetricsPersistence.getInstance().getMetrics(id);
		if (metrics==null){
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(metrics);
		}
    }
	
	@RequestMapping(value="/metrics", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<Metrics>> getAllMetrics() {
		
		List<Metrics> listMetrics = MetricsPersistence.getInstance().getAllMetrics();
		if (listMetrics==null){
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(listMetrics);
		}
    }
}
