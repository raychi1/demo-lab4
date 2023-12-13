package com.example.demo.controller;

import com.example.demo.model.Container;
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
    @PostMapping("/sailTo/{shipId}/{destinationPortId}")
    public ResponseEntity<String> sailTo(@PathVariable Long shipId, @PathVariable Long destinationPortId) {
        boolean success = shipService.sailTo(shipId, destinationPortId);
        if (success) {
            return ResponseEntity.ok("Ship sailed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to sail the ship");
        }
    }

    @PostMapping("/reFuel/{shipId}/{newFuel}")
    public ResponseEntity<String> reFuel(@PathVariable Long shipId, @PathVariable double newFuel) {
        shipService.reFuel(shipId, newFuel);
        return ResponseEntity.ok("Ship refueled successfully");
    }

    @PostMapping("/load/{shipId}")
    public ResponseEntity<String> load(@PathVariable Long shipId, @RequestBody Container container) {
        boolean success = shipService.load(shipId, container);
        if (success) {
            return ResponseEntity.ok("Container loaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to load the container");
        }
    }

    @PostMapping("/unLoad/{shipId}")
    public ResponseEntity<String> unLoad(@PathVariable Long shipId, @RequestBody Container container) {
        boolean success = shipService.unLoad(shipId, container);
        if (success) {
            return ResponseEntity.ok("Container unloaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to unload the container");
        }
    }
}