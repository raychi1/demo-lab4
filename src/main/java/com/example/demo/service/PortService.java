package com.example.demo.service;

import com.example.demo.model.Port;
import com.example.demo.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {

    private final PortRepository portRepository;

    @Autowired
    public PortService(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }

    public Port getPortById(Long id) {
        return portRepository.findById(id).orElse(null);
    }

    public void createPort(Port port) {
        portRepository.save(port);
    }

    public void updatePort(Long id, Port updatedPort) {
        Port existingPort = portRepository.findById(id).orElse(null);
        if (existingPort != null) {
            // Update properties of the existing port
            // Example: existingPort.setName(updatedPort.getName());
            portRepository.save(existingPort);
        }
    }

    public void deletePort(Long id) {
        portRepository.deleteById(id);
    }

    // Custom method to get ports by name
    public List<Port> getPortsByName(String name) {
        return portRepository.findByName(name);
    }

    // Custom method to get ports by location
    public List<Port> getPortsByLocation(String location) {
        return portRepository.findByLocation(location);
    }

    // Custom method to get ports by the name of the associated container
    public List<Port> getPortsByContainerName(String containerName) {
        return portRepository.findByContainers_Name(containerName);
    }

    // Add more custom methods as needed

}
