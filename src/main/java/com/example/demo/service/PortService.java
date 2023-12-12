package com.example.demo.service;

import com.example.demo.model.Port;
import com.example.demo.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortService {

    private final PortRepository portRepository;

    @Autowired
    public PortService(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Transactional
    public void createPort(Port port) {
        portRepository.save(port);
    }

    @Transactional
    public void updatePort(Port port) {
        portRepository.save(port);
    }

    @Transactional
    public void deletePort(Long portId) {
        portRepository.deleteById(portId);
    }

    public Port getPortById(Long portId) {
        return portRepository.findById(portId).orElse(null);
    }

    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }
}
