package com.example.storeitemmanager.item;

import com.example.storeitemmanager.item.models.Availability;
import com.example.storeitemmanager.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item(itemDto);
        itemRepository.save(item);
        return itemDto;
    }

    public ItemDto updateItem(String name, ItemDto itemDto) {
        return itemRepository.findByName(name)
                .map(item -> {
                    item.setDescription(itemDto.getDescription());
                    item.setPrice(itemDto.getPrice());
                    item.setAvailability(itemDto.getAvailability());
                    itemRepository.save(item);
                    return itemDto;
                })
                .orElse(null);
    }

    public ItemDto getItem(String name) {
        return itemRepository.findByName(name)
                .map(item -> new ItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAvailability()))
                .orElse(null);
    }

    public void deleteItem(String name) {
        itemRepository.findByName(name)
                .ifPresent(itemRepository::delete);
    }

    public List<ItemDto> getAllItems() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items.stream()
                .map(item -> new ItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAvailability()))
                .toList();
    }

    public List<ItemDto> getItemsByAvailability(Availability availability) {
        List<Item> items = itemRepository.findByAvailability(availability);
        return items.stream()
                .map(item -> new ItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAvailability()))
                .toList();
    }

}
