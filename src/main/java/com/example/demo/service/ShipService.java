package com.example.demo.service;

import com.example.demo.model.Ship;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public Ship getShipById(Long id) {
        return shipRepository.findById(id).orElse(null);
    }

    public void createShip(Ship ship) {
        shipRepository.save(ship);
    }

    public void updateShip(Long id, Ship updatedShip) {
        Ship existingShip = shipRepository.findById(id).orElse(null);
        if (existingShip != null) {
            // Update properties of the existing ship
            // Example: existingShip.setName(updatedShip.getName());
            shipRepository.save(existingShip);
        }
    }

    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }

    // Custom method to get ships by name
    public List<Ship> getShipsByName(String name) {
        return shipRepository.findByName(name);
    }

    // Custom method to get ships by capacity
    public List<Ship> getShipsByCapacity(int capacity) {
        return shipRepository.findByCapacity(capacity);
    }

    // Custom method to get ships by the name of the associated container
    public List<Ship> getShipsByContainerName(String containerName) {
        return shipRepository.findByContainers_Name(containerName);
    }



}
