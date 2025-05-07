package com.example.storeitemmanager.item;

import com.example.storeitemmanager.item.models.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private String name;

    private String description;

    private double price;

    private Availability availability;
}
