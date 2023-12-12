package com.example.demo.controller;

import com.example.demo.model.Ship;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ship")
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createShip(@RequestBody Ship ship) {
        shipService.createShip(ship);
        return ResponseEntity.ok("Ship created successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateShip(@RequestBody Ship ship) {
        shipService.updateShip(ship);
        return ResponseEntity.ok("Ship updated successfully");
    }

    @DeleteMapping("/delete/{shipId}")
    public ResponseEntity<String> deleteShip(@PathVariable Long shipId) {
        shipService.deleteShip(shipId);
        return ResponseEntity.ok("Ship deleted successfully");
    }

    @GetMapping("/get/{shipId}")
    public ResponseEntity<Ship> getShipById(@PathVariable Long shipId) {
        Ship ship = shipService.getShipById(shipId);
        return ResponseEntity.ok(ship);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> ships = shipService.getAllShips();
        return ResponseEntity.ok(ships);
    }
}