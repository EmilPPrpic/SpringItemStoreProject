package com.example.storeitemmanager.item;

import com.example.storeitemmanager.item.models.Availability;
import com.example.storeitemmanager.item.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findByName(String name);
    List<Item> findByAvailability(Availability availability);
}
