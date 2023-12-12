package com.example.demo.service;

import com.example.demo.model.Container;
import com.example.demo.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    @Autowired
    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Transactional
    public void createContainer(Container container) {
        containerRepository.save(container);
    }

    @Transactional
    public void updateContainer(Container container) {
        containerRepository.save(container);
    }

    @Transactional
    public void deleteContainer(Long containerId) {
        containerRepository.deleteById(containerId);
    }

    public Container getContainerById(Long containerId) {
        return containerRepository.findById(containerId).orElse(null);
    }

    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }
}