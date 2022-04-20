package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Services {

	@Id
	private String id;
	private String serviceName;
	private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Device devices;
	
	public Services() {}
	
	public Services(String id, String serviceName, int cost){
		this.id = id;
		this.serviceName = serviceName;
		this.cost = cost;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Device getDevices() {
		return devices;
	}
	public void setDevices(Device devices) {
		this.devices = devices;
	}

}
