package com.example.storeitemmanager;

import com.example.storeitemmanager.item.ItemDto;
import com.example.storeitemmanager.item.ItemService;
import com.example.storeitemmanager.item.models.Availability;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser(username = "user", roles = {"USER"})
@SpringBootTest
@AutoConfigureMockMvc
class UserItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;


    @Test
    void shouldReturnListOfItems() throws Exception {
        List<ItemDto> items = List.of(new ItemDto("Item1", "Description", 10.0, Availability.AVAILABLE));
        Mockito.when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/user/items/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Item1"));
    }

    @Test
    void shouldReturnItemsByAvailability() throws Exception {
        List<ItemDto> availableItems = List.of(new ItemDto("Item1", "Description", 10.0, Availability.AVAILABLE));
        Mockito.when(itemService.getItemsByAvailability(Availability.AVAILABLE)).thenReturn(availableItems);

        mockMvc.perform(get("/user/items/availability/AVAILABLE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Item1"));
    }

    @Test
    void shouldReturnNotFoundWhenNoItemsFoundByAvailability() throws Exception {
        Mockito.when(itemService.getItemsByAvailability(Availability.UNAVAILABLE)).thenReturn(List.of());

        mockMvc.perform(get("/user/items/availability/UNAVAILABLE"))
                .andExpect(status().isNotFound());
    }
}
