package com.ninjaone.backendinterviewproject.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Device {

	@Id
	private String id;
	/*
	 * Can be any name picked by the owner.
	 */
	private String systemName;
	/*
	 * Can be Windows or Mac
	 */
	private String type;
	
	private String status;

//	@OneToMany(
//			mappedBy = "devices",
//			cascade = {CascadeType.ALL}
//			)
	//WORKING
//	@OneToMany(
//			targetEntity = Services.class,
//			cascade = CascadeType.ALL
//			)
//	@JoinColumn(name = "ds_fk", referencedColumnName = "id")
	
	@OneToMany(mappedBy = "devices", cascade = CascadeType.ALL)
	private Set<Services> services = new HashSet<>();
	
	public Device(){}
	public Device(String status) {this.status = status;}
	public Device(String id, String systemName, String type) {
		this.id = id;
		this.systemName = systemName;
		this.type = type;
		this.status = "ACTIVE";
		this.services = new HashSet<Services>();
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Set<Services> getServices() {
		return services;
	}
	
	/**
	 * Adds all provided Services to the existing Device while avoiding duplicates.
	 * @param services: List of services to be added to this Device
	 */
	public void setServices(Set<Services> services) {

		Set<Services> currentServices = getServices();

		if(!currentServices.isEmpty()) {
			
			Iterator<Services> iterator = currentServices.iterator();			
		
			while(iterator.hasNext()) {
				
				Services existing = iterator.next();
				
				for(Services added: services) {
					//If we find that the Services been added are already existing, we remove the existing versions to allow the new one to be added.
					if(existing.getServiceName().equalsIgnoreCase(added.getServiceName())) {
						existing.setDevices(null);//Disassociate existing Service from this Device.
						iterator.remove();
						break;
					}
				}
			}
		}
		//Merge existing and new Services
		currentServices.addAll(services);
		
		this.services = currentServices;

		//Associate all new Services with this Device
		for(Services x: services) {
			x.setDevices(this);
		}
	}
	
}
