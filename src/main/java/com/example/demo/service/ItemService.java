package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void createItem(Item item) {
        itemRepository.save(item);
    }

    public void updateItem(Long id, Item updatedItem) {
        Item existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            // Update properties of the existing item
            // Example: existingItem.setName(updatedItem.getName());
            itemRepository.save(existingItem);
        }
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    // Custom method to get items by name
    public List<Item> getItemsByName(String name) {
        return itemRepository.findByName(name);
    }

    // Custom method to get items by type
    public List<Item> getItemsByType(String type) {
        return itemRepository.findByType(type);
    }

    // Custom method to get items by container ID
    public List<Item> getItemsByContainerId(Long containerId) {
        return itemRepository.findByContainer_Id(containerId);
    }

    // Add more custom methods as needed

}
