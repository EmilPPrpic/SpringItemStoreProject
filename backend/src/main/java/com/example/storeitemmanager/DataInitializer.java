package com.example.storeitemmanager;

import com.example.storeitemmanager.item.ItemDto;
import com.example.storeitemmanager.item.ItemService;
import com.example.storeitemmanager.item.models.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    ItemService itemService;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        createFruitItems();
    }

    private void createFruitItems() {
        String[] fruitNames = {
                "Apple", "Banana", "Orange", "Strawberry", "Mango",
                "Pineapple", "Blueberry", "Watermelon", "Peach", "Grapes",
                "Pear", "Plum", "Cherry", "Kiwi", "Papaya",
                "Avocado", "Lemon", "Coconut", "Raspberry", "Blackberry"
        };

        String[] fruitDescriptions = {
                "A crisp and refreshing fruit with a sweet flavor.",
                "A sweet, soft fruit that is easy to peel.",
                "A juicy, tangy citrus fruit with vitamin C.",
                "A red, sweet fruit that’s great in desserts.",
                "A tropical fruit with a juicy, tangy taste.",
                "A large tropical fruit with sweet, tart flavor.",
                "Small, round berries that are deliciously sweet and tart.",
                "A large melon with juicy, sweet flesh.",
                "A soft, sweet fruit with a juicy, fragrant taste.",
                "Small, round, and often sweet, perfect for snacking.",
                "A juicy, sweet fruit with a tender texture.",
                "A smooth-skinned fruit with a tart-sweet flavor.",
                "Small, bright red fruits with a sweet, tangy taste.",
                "A small, brown, fuzzy fruit with green flesh and black seeds.",
                "A tropical fruit with a sweet, aromatic flavor.",
                "A creamy fruit with a unique flavor and texture.",
                "A sour, tart fruit often used in cooking or drinks.",
                "A large, brown fruit with a fibrous husk, known for its sweet water.",
                "A tiny berry with a tart and slightly sweet flavor.",
                "A small, dark-colored fruit with a slightly sweet taste."
        };

        double[] fruitPrices = {
                1.2, 0.5, 1.0, 2.0, 1.5,
                3.0, 2.5, 0.8, 1.8, 2.0,
                1.3, 1.4, 2.2, 2.5, 3.5,
                1.6, 0.9, 2.8, 1.7, 1.9
        };

        for (int i = 0; i < fruitNames.length; i++) {
            ItemDto itemDto = new ItemDto(
                    fruitNames[i],
                    fruitDescriptions[i],
                    fruitPrices[i],
                    (i % 3 == 0) ? Availability.AVAILABLE : Availability.ON_DEMAND
            );

            itemService.createItem(itemDto);
        }
    }
}
