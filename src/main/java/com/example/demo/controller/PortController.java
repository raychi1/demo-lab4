package com.example.demo.controller;

import com.example.demo.model.Port;
import com.example.demo.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/port")
public class PortController {

    private final PortService portService;

    @Autowired
    public PortController(PortService portService) {
        this.portService = portService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPort(@RequestBody Port port) {
        portService.createPort(port);
        return ResponseEntity.ok("Port created successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePort(@RequestBody Port port) {
        portService.updatePort(port);
        return ResponseEntity.ok("Port updated successfully");
    }

    @DeleteMapping("/delete/{portId}")
    public ResponseEntity<String> deletePort(@PathVariable Long portId) {
        portService.deletePort(portId);
        return ResponseEntity.ok("Port deleted successfully");
    }

    @GetMapping("/get/{portId}")
    public ResponseEntity<Port> getPortById(@PathVariable Long portId) {
        Port port = portService.getPortById(portId);
        if (port != null) {
            return ResponseEntity.ok(port);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Port>> getAllPorts() {
        List<Port> ports = portService.getAllPorts();
        return ResponseEntity.ok(ports);
    }
}
