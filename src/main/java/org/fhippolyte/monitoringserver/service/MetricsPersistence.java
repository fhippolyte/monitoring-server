package org.fhippolyte.monitoringserver.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fhippolyte.monitoringserver.domain.Metrics;

public class MetricsPersistence {

	private Map<String, Metrics> metricsMap = null;
	private String securityKey= null;
	
	private static MetricsPersistence instance = null; 
	
	private MetricsPersistence(){
		this.metricsMap = new Hashtable<String, Metrics>();
	}
	
	
	public static MetricsPersistence getInstance(){
		if (instance==null) {
			synchronized(MetricsPersistence.class){
				if (instance==null) {
					instance = new MetricsPersistence();
				}
			}
		}
		return instance;
	}
	
	public List<Metrics> getAllMetrics(){

		List<Metrics> ret = new ArrayList<Metrics>(); 
		
		Set<String> cles = metricsMap.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
		   String id = it.next();
		   ret.add(this.metricsMap.get(id));
		}
		
		return ret;
	}
	
	public Metrics getMetrics(String id){
		return this.metricsMap.get(id);
	}
	
	public void addMetrics(String id, Metrics metrics){
		this.metricsMap.put(id, metrics);
	}
	
	public void setSecurityKey(String key) {
		this.securityKey = key;
	}
	
	public boolean isSecurityKeyValid(String key) {
		return this.securityKey.equals(key);
	}
	
}
