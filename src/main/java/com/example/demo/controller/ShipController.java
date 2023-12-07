package com.example.demo.controller;

import com.example.demo.model.Ship;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> ships = shipService.getAllShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable Long id) {
        Ship ship = shipService.getShipById(id);
        if (ship != null) {
            return new ResponseEntity<>(ship, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createShip(@RequestBody Ship ship) {
        shipService.createShip(ship);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShip(@PathVariable Long id, @RequestBody Ship updatedShip) {
        shipService.updateShip(id, updatedShip);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShip(@PathVariable Long id) {
        shipService.deleteShip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<Ship>> getShipsByName(@PathVariable String name) {
        List<Ship> ships = shipService.getShipsByName(name);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/byCapacity/{capacity}")
    public ResponseEntity<List<Ship>> getShipsByCapacity(@PathVariable int capacity) {
        List<Ship> ships = shipService.getShipsByCapacity(capacity);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/byContainerName/{containerName}")
    public ResponseEntity<List<Ship>> getShipsByContainerName(@PathVariable String containerName) {
        List<Ship> ships = shipService.getShipsByContainerName(containerName);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }
}
