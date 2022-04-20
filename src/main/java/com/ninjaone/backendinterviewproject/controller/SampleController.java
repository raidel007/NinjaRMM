package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Services;
import com.ninjaone.backendinterviewproject.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class SampleController {
    private final ProductsService productsService;

    public SampleController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * Endpoint to add a new service to the list of available services for our devices.
     * @param services: The new Services object to add.
     * @return: The content of the newly added Service in JSon format.
     */
    @PostMapping(path = "/addServices", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    private Services addServices(@RequestBody Services services){
        return productsService.saveServices(services);
    }
    
    /**
     * Endpoint to delete an existing service from the list of available services.
     * @param serviceId: The ID for the service to delete.
     */
    @DeleteMapping("/deleteServices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteServices(@PathVariable String id){
        productsService.deleteServices(id);
    }
    
    /**
     * Endpoint to add a new Device for a customer.
     * @param device: The new Device object to add.
     * @return: The content of the newly added Device in JSon format.
     */
    @PostMapping(path = "/addDevice", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    private Device addDevice(@RequestBody Device device){
        return productsService.saveDevice(device);
    }
    
    /**
     * Endpoint to get the details from a Device by ID.
     * @param device: The details for the Device we are searching for.
     * @return: The content of the Device we are looking for in JSon format.
     */
    @GetMapping("getDeviceById/{id}")
    private Device getDevice(@PathVariable String id){
        return productsService.getDeviceById(id)
                .orElseThrow();
    }
    
    /**
     * Endpoint to update an existing Device.
     * @param device: The details for the Device we want to update.
     * @return: The content of the newly updated Device in JSon format.
     */
    @PutMapping(path = "/updateDevice", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    private Device updateDevice(@RequestBody Device device){
        return productsService.updateDevice(device);
    }

    /**
     * Endpoint to delete an existing Device by its Id.
     * @param serviceId: The Id for the Device to delete.
     */
    @DeleteMapping("/deleteDevice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDevice(@PathVariable String id){
    	productsService.deleteDevice(id);
    }
    
    /**
     * Endpoint to calculate the total monthly cost of all services.
     * @param
     * @return: Total monthly cost of all services.
     */
    @GetMapping(path = "/getTotalCost", produces = "application/json")
    private String getTotalCost() {
    	return productsService.calculateTotalCost();
    }
}
