package com.example.demo.controller;

import com.example.demo.model.Container;
import com.example.demo.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/container")
public class ContainerController {

    private final ContainerService containerService;

    @Autowired
    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createContainer(@RequestBody Container container) {
        containerService.createContainer(container);
        return ResponseEntity.ok("Container created successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateContainer(@RequestBody Container container) {
        containerService.updateContainer(container);
        return ResponseEntity.ok("Container updated successfully");
    }

    @DeleteMapping("/delete/{containerId}")
    public ResponseEntity<String> deleteContainer(@PathVariable Long containerId) {
        containerService.deleteContainer(containerId);
        return ResponseEntity.ok("Container deleted successfully");
    }

    @GetMapping("/get/{containerId}")
    public ResponseEntity<Container> getContainerById(@PathVariable Long containerId) {
        Container container = containerService.getContainerById(containerId);
        if (container != null) {
            return ResponseEntity.ok(container);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Container>> getAllContainers() {
        List<Container> containers = containerService.getAllContainers();
        return ResponseEntity.ok(containers);
    }
}
