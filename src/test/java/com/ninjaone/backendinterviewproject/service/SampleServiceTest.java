package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServicesRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SampleServiceTest {
    public static final String ID = "12345";

    @Mock
    private DeviceRepository deviceRepository;
    
    @Mock
    private ServicesRepository servicesRepository;

    @InjectMocks
    private ProductsService productsService;

    private Device deviceEntity;
    
    private Services servicesEntity;

    @BeforeEach
    void setup(){
    	deviceEntity = new Device(ID, "system name", "type");
    	servicesEntity = new Services(ID, "Device Service", 4);
    	servicesEntity.setDevices(deviceEntity);
    }
    
    @Test
    void saveServices() {
        when(servicesRepository.save(servicesEntity)).thenReturn(servicesEntity);
        assertEquals(servicesEntity, productsService.saveServices(servicesEntity));
    }
    
    @Test
    void deleteServices(){
        when(servicesRepository.existsById(ID)).thenReturn(true);
        doNothing().when(servicesRepository).deleteById(ID);
        productsService.deleteServices(ID);
        Mockito.verify(servicesRepository, times(1)).deleteById(ID);
    }
    
    @Test
    void saveDevice() {
        when(deviceRepository.save(deviceEntity)).thenReturn(deviceEntity);
        assertEquals(deviceEntity, productsService.saveDevice(deviceEntity));
    }

    @Test
    void getDeviceById() {
        when(deviceRepository.findById(ID)).thenReturn(Optional.of(deviceEntity));
        Optional<Device> sampleEntityOptional = productsService.getDeviceById(ID);
        Device actualEntity = sampleEntityOptional.orElse(null);
        assert actualEntity != null;
        assertEquals(deviceEntity.getType(), actualEntity.getType());
    }

    @Test
    void deleteDevice(){
        when(deviceRepository.existsById(ID)).thenReturn(true);
        doNothing().when(deviceRepository).deleteById(ID);
        productsService.deleteDevice(ID);
        Mockito.verify(deviceRepository, times(1)).deleteById(ID);
    }
}
