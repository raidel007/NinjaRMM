package com.ninjaone.backendinterviewproject.model;

import java.util.Set;

public class CostBreakdown {

    private int totalDeviceCost;
    private int totalAntivirusCost;
    private int totalBackupCost;
    private int totalPSACost;
    private int totalScreenShareCost;
    private Iterable<Device> allDevices;

    public CostBreakdown(){}
    
    public CostBreakdown(Iterable<Device> allDevices){
    	this.allDevices = allDevices;
    }

	public int getTotalDeviceCost() {
		return totalDeviceCost;
	}

	public void setTotalDeviceCost(int totalDeviceCost) {
		this.totalDeviceCost = totalDeviceCost;
	}

	public int getTotalAntivirusCost() {
		return totalAntivirusCost;
	}

	public void setTotalAntivirusCost(int totalAntivirusCost) {
		this.totalAntivirusCost = totalAntivirusCost;
	}

	public int getTotalBackupCost() {
		return totalBackupCost;
	}

	public void setTotalBackupCost(int totalBackupCost) {
		this.totalBackupCost = totalBackupCost;
	}

	public int getTotalPSACost() {
		return totalPSACost;
	}

	public void setTotalPSACost(int totalPSACost) {
		this.totalPSACost = totalPSACost;
	}

	public int getTotalScreenShareCost() {
		return totalScreenShareCost;
	}

	public void setTotalScreenShareCost(int totalScreenShareCost) {
		this.totalScreenShareCost = totalScreenShareCost;
	}
	
	public Iterable<Device> getAllDevices() {
		return allDevices;
	}

	public void setAllDevices(Iterable<Device> allDevices) {
		this.allDevices = allDevices;
	}

	/**
	 * Overwritten toString method to print the breakdown cost of all devices and services.
	 */
	@Override
	public String toString() {
		
		int numberOfDevices = 0;
		int numberOfServices = 0;
		int totalCost = 0;
		
		for(Device dev: getAllDevices()) {
			
			numberOfDevices ++;
			Set<Services> services = dev.getServices();
			numberOfServices += services.size();
			
			for(Services serv: services) {
				if(serv.getServiceName().equalsIgnoreCase("Device Service")){
					totalDeviceCost += serv.getCost();
				}
				else if(serv.getServiceName().contains("Antivirus")){
					totalAntivirusCost += serv.getCost();
				}
				else if(serv.getServiceName().equalsIgnoreCase("Backup")){
					totalBackupCost += serv.getCost();
				}
				else if(serv.getServiceName().equalsIgnoreCase("PSA")){
					totalPSACost += serv.getCost();
				}
				if(serv.getServiceName().equalsIgnoreCase("Screen Share")){
					totalScreenShareCost += serv.getCost();
				}
			}
		}
		
		totalCost = totalDeviceCost + totalAntivirusCost + totalBackupCost + totalPSACost + totalScreenShareCost;
		
		String explanationOfCosts = "Explanation of Cost \n\n" + "\nNumber of Devices: " + numberOfDevices 
		+ "\nNumber of Services: " + numberOfServices	+ "\nTotal Cost: $" + totalCost +"\n\n" + "Explanation:"
		+ "\n Device cost: $" + totalDeviceCost + "\n Antivirus cost: $" + totalAntivirusCost + "\n Backup cost: $"
		+ totalBackupCost + "\n PSA Cost: $" + totalPSACost + "\n Screen Share: $" + totalScreenShareCost;
		
		return explanationOfCosts;
	}

}
