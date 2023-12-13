package com.example.demo.service;

import com.example.demo.model.Container;
import com.example.demo.model.Port;
import com.example.demo.model.Ship;
import com.example.demo.repository.PortRepository;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final PortRepository portRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository, PortRepository portRepository) {
        this.shipRepository = shipRepository;
        this.portRepository = portRepository;
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


    @Transactional
    public boolean sailTo(Long shipId, Long destinationPortId) {
        Ship ship = shipRepository.findById(shipId).orElse(null);
        Port destinationPort = portRepository.findById(destinationPortId).orElse(null);

        if (ship != null && destinationPort != null) {
            return ship.sailTo(destinationPort);
        }
        return false;
    }

    @Transactional
    public void reFuel(Long shipId, double newFuel) {
        Ship ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null) {
            ship.reFuel(newFuel);
        }
    }

    @Transactional
    public boolean load(Long shipId, Container container) {
        Ship ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null) {
            return ship.load(container);
        }
        return false;
    }

    @Transactional
    public boolean unLoad(Long shipId, Container container) {
        Ship ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null) {
            return ship.unLoad(container);
        }
        return false;
    }
}