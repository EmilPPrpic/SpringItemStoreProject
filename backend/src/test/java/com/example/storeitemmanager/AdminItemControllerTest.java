package com.example.storeitemmanager;

import com.example.storeitemmanager.item.ItemDto;
import com.example.storeitemmanager.item.ItemService;
import com.example.storeitemmanager.item.models.Availability;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser(username = "admin", roles = {"ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
class AdminItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnListOfItems() throws Exception {
        List<ItemDto> items = List.of(new ItemDto("Item1", "Description", 10.0, Availability.AVAILABLE));
        Mockito.when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/admin/items/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Item1"));
    }

    @Test
    void shouldReturnItemIfExists() throws Exception {
        ItemDto item = new ItemDto("Item1", "Description", 10.0, Availability.AVAILABLE);
        Mockito.when(itemService.getItem("Item1")).thenReturn(item);

        mockMvc.perform(get("/admin/items/get/Item1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Item1"));
    }

    @Test
    void shouldReturnNotFoundIfItemDoesNotExist() throws Exception {
        Mockito.when(itemService.getItem("Unknown")).thenReturn(null);

        mockMvc.perform(get("/admin/items/get/Unknown"))
                .andExpect(status().isNotFound());
    }
    @Test
    void shouldCreateItemAndReturnDto() throws Exception {
        ItemDto input = new ItemDto("Item1", "Description", 10.0, Availability.AVAILABLE);
        Mockito.when(itemService.createItem(Mockito.any())).thenReturn(input);

        mockMvc.perform(post("/admin/items/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Item1"));
    }
    @Test
    void shouldUpdateItemAndReturnDto() throws Exception {
        ItemDto updated = new ItemDto("Item1", "Updated Desc", 20.0, Availability.UNAVAILABLE);
        Mockito.when(itemService.updateItem(Mockito.eq("Item1"), Mockito.any())).thenReturn(updated);

        mockMvc.perform(put("/admin/items/update/Item1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Desc"))
                .andExpect(jsonPath("$.price").value(20.0));
    }
    @Test
    void shouldDeleteItemAndReturnNoContent() throws Exception {
        mockMvc.perform(delete("/admin/items/delete/Item1"))
                .andExpect(status().isNoContent());
    }
}
