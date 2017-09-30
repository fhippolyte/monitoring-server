package org.fhippolyte.monitoringserver.service;

import java.util.Hashtable;
import java.util.Map;

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
	
	public Metrics getMetrics(String type){
		return this.metricsMap.get(type);
	}
	
	public void addMetrics(String type, Metrics metrics){
		this.metricsMap.put(type, metrics);
	}
	
	public void setSecurityKey(String key) {
		this.securityKey = key;
	}
	
	public boolean isSecurityKeyValid(String key) {
		return this.securityKey.equals(key);
	}
	
}
