package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void createItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}