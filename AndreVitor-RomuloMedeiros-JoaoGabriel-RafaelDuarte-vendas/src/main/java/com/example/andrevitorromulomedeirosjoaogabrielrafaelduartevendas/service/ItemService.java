package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.repository.ItemRepository;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id);
    }
}
