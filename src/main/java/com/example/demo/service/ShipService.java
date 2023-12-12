package com.example.demo.service;

import com.example.demo.model.Ship;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Transactional
    public void createShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Transactional
    public void updateShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Transactional
    public void deleteShip(Long shipId) {
        shipRepository.deleteById(shipId);
    }

    public Ship getShipById(Long shipId) {
        return shipRepository.findById(shipId).orElse(null);
    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }
}