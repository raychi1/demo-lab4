package com.example.demo.repository;

import com.example.demo.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findByName(String name);

    List<Ship> findByCapacity(int capacity);

    List<Ship> findByContainers_Name(String containerName);
}
