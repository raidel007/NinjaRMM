package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServicesRepository;
import com.ninjaone.backendinterviewproject.model.CostBreakdown;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Services;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsService {
    
	private final DeviceRepository deviceRepository;
    private final ServicesRepository servicesRepository;
    
    public ProductsService(DeviceRepository sampleRepository, ServicesRepository servicesRepository) {
        this.deviceRepository = sampleRepository;
        this.servicesRepository = servicesRepository;
    }
    
    public Services saveServices(Services services){
        return servicesRepository.save(services);
    }
    
    public void deleteServices(String serviceId){
    	if(servicesRepository.existsById(serviceId)) {
    		servicesRepository.deleteById(serviceId);
    	}
    }

    public Device saveDevice(Device device){
    	if(deviceRepository.existsById(device.getId())) {
    		return new Device("RECORD ALREADY EXISTS");
    	}
        return deviceRepository.save(device);
    }
    
    public Device updateDevice(Device device){
    	
    	if(!deviceRepository.existsById(device.getId())) {
    		return new Device("RECORD DOESN'T EXISTS");
    	}
    	
    	Device existingDevice = deviceRepository.findById(device.getId()).get();
    	existingDevice.setStatus(device.getStatus());
    	existingDevice.setSystemName(device.getSystemName());
    	existingDevice.setType(device.getType());
    	existingDevice.setServices(device.getServices());
    	
        return deviceRepository.save(existingDevice);
    }

    public Optional<Device> getDeviceById(String id){
        return deviceRepository.findById(id);
    }
    
    public void deleteDevice(String id) {
    	if(deviceRepository.existsById(id)) {
    		deviceRepository.deleteById(id);
    	}
    }
    
    public String calculateTotalCost() {
    	Iterable<Device> allDevices = deviceRepository.findAll();
    	if(allDevices != null) {
        	CostBreakdown breakdown = new CostBreakdown(allDevices);
        	return breakdown.toString();
    	}
    	return "0";
    }
    
}
