package com.example.storeitemmanager.item.controller;

import com.example.storeitemmanager.item.ItemDto;
import com.example.storeitemmanager.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/items")
@PreAuthorize("hasRole('ADMIN')")
public class AdminItemController {

    private final ItemService itemService;

    @Autowired
    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.createItem(itemDto));
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable String name, @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.updateItem(name, itemDto));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteItem(@PathVariable String name) {
        itemService.deleteItem(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<ItemDto> getItem(@PathVariable String name) {
        ItemDto itemDto = itemService.getItem(name);
        if (itemDto != null)
            return ResponseEntity.ok(itemDto);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

}