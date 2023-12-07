package com.example.demo.service;

import com.example.demo.model.Container;
import com.example.demo.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    @Autowired
    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    public Container getContainerById(Long id) {
        return containerRepository.findById(id).orElse(null);
    }

    public void createContainer(Container container) {
        containerRepository.save(container);
    }

    public void updateContainer(Long id, Container updatedContainer) {
        Container existingContainer = containerRepository.findById(id).orElse(null);
        if (existingContainer != null) {
            // Update properties of the existing container
            // Example: existingContainer.setName(updatedContainer.getName());
            containerRepository.save(existingContainer);
        }
    }

    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }

    // Custom method to get containers by ship name
    public List<Container> getContainersByShipName(String shipName) {
        return containerRepository.findByShip_Name(shipName);
    }

    // Custom method to get containers by port name
    public List<Container> getContainersByPortName(String portName) {
        return containerRepository.findByPort_Name(portName);
    }

    // Custom method to get containers by item name
    public List<Container> getContainersByItemName(String itemName) {
        return containerRepository.findByItems_Name(itemName);
    }

    // Add more custom methods as needed

}
