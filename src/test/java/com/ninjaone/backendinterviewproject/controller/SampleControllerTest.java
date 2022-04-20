package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.NinjaRmmApplication;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Services;
import com.ninjaone.backendinterviewproject.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NinjaRmmApplication.class})
@WebMvcTest(SampleController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class SampleControllerTest {
    public static final String ID = "12345";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductsService productsService;

    private Device deviceEntity;
    
    private Services servicesEntity;

    @BeforeEach
    void setup(){
    	deviceEntity = new Device(ID, "MacBook Air", "Mac");
    	servicesEntity = new Services(ID, "Device Service", 4);
    	servicesEntity.setDevices(deviceEntity);
    }
    
    @Test
    void addServices() throws Exception {
        when(productsService.saveServices(any())).thenReturn(servicesEntity);

        String sampleEntityString = objectMapper.writeValueAsString(servicesEntity);
        mockMvc.perform(post("/product/addServices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sampleEntityString))
                .andExpect(status().isCreated())
                .andExpect(content().string(sampleEntityString));
    }
    
    @Test
    void deleteServices() throws Exception {
        doNothing().when(productsService).deleteServices(ID);

        mockMvc.perform(delete("/product/deleteServices/" + ID))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void addDevice() throws Exception {
        when(productsService.saveDevice(any())).thenReturn(deviceEntity);

        String sampleEntityString = objectMapper.writeValueAsString(deviceEntity);
        mockMvc.perform(post("/product/addDevice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sampleEntityString))
                .andExpect(status().isCreated())
                .andExpect(content().string(sampleEntityString));
    }
    
    @Test
    void updateDevice() throws Exception {
        when(productsService.updateDevice(any())).thenReturn(deviceEntity);

        String sampleEntityString = objectMapper.writeValueAsString(deviceEntity);
        mockMvc.perform(put("/product/updateDevice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sampleEntityString))
                .andExpect(status().isCreated())
                .andExpect(content().string(sampleEntityString));
    }
    

    @Test
    void getDeviceById() throws Exception {
        when(productsService.getDeviceById(ID)).thenReturn(Optional.of(deviceEntity));

        mockMvc.perform(get("/product/getDeviceById/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(deviceEntity)));
    }

    @Test
    void deleteDevice() throws Exception {
        doNothing().when(productsService).deleteDevice(ID);

        mockMvc.perform(delete("/product/deleteDevice/" + ID))
                .andExpect(status().isNoContent());
    }
}
