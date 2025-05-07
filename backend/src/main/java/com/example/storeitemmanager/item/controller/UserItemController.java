package com.example.storeitemmanager.item.controller;

import com.example.storeitemmanager.item.models.Availability;
import com.example.storeitemmanager.item.ItemDto;
import com.example.storeitemmanager.item.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/items")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserItemController {

    private final ItemService itemService;

    public UserItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("availability/{availability}")
    public ResponseEntity<List<ItemDto>> getItemsByAvailability(@PathVariable Availability availability) {
        List<ItemDto> items = itemService.getItemsByAvailability(availability);
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }


}
