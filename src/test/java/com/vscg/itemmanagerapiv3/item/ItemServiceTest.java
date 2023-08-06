package com.vscg.itemmanagerapiv3.item;

import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @InjectMocks
    private ItemService itemService = new ItemService();
    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getItem() {
        when(itemRepository.findById(2L))
                .thenReturn(Optional.of(
                        new Item(2L, "VivoBook", "Asus Laptop", "$1599")));
        Optional<Item> item = itemService.getItem(2L);
        assertEquals("VivoBook", item.get().getTitle());
    }

    @Test
    void getNullItem() {
        when(itemRepository.findById(2L))
                .thenReturn(Optional.empty());
        Optional<Item> item = itemService.getItem(2L);
        assertFalse(item.isPresent());
    }

    @Test
    void getAllItems() {
        when(itemRepository.findAll()).thenReturn(Arrays.asList(
                new Item(1L, "VivoBook", "Asus Laptop", "$1599"),
                new Item(2L,"Satelite Laptop", "Powerful computer", "$1655")));
        Iterable<Item> items = itemService.getAllItems();
        assertEquals(StreamSupport.stream(
                items.spliterator(),false).collect(Collectors.toList()).size(), 2);
    }

    @Test
    void deleteItem() {
        doNothing().when(itemRepository).deleteById(1L);
        itemService.deleteItem(1L);
        verify(itemRepository, times(1)).deleteById(1L);
    }


    @Test
    void saveItem(){
        when(itemRepository.save(any(Item.class)))
                .thenReturn(
                        new Item
                                (1L, "Laptop Sony",
                                        "Good performance Laptop", "$1455")
                );
        Item savedItem = itemService.saveItem(new Item
                (1L, "Laptop Sony", "Good performance Laptop", "$1455"));
        assertEquals(1L, savedItem.getId());
        assertEquals("Laptop Sony", savedItem.getTitle());
        assertEquals("Good performance Laptop", savedItem.getFullDescription());
        assertEquals("$1455", savedItem.getPrice());

    }

    @Test
    void updateItem() throws ItemNotFoundException {
        //Item item = new Item(
        //        1L, "Laptop HP", "Good performance Laptop", "$1455");
        //Item UpdatedItem = itemService.updateItem();

        Item anyItem = new Item(1L, "MacBook",
                "Apple computer", "$2144");

        when(itemRepository.findById(1L)).thenReturn(Optional.of(anyItem));
        when(itemRepository.save(anyItem)).thenReturn(anyItem);

        Item updatedItem = itemService.updateItem(1L, anyItem );


        assertThat(updatedItem, instanceOf(Item.class));

    }

    @Test
    void updateItemExceptionCase() {
        //Item item = new Item(
        //        1L, "Laptop HP", "Good performance Laptop", "$1455");
        //Item UpdatedItem = itemService.updateItem();

        Item anyItem = new Item(2L, "MacBook",
                "Apple computer", "$2144");

        when(itemRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            Item updatedItem = itemService.updateItem(2L, anyItem );
        });


        assertTrue(exception.getMessage().contains("No item were found for id"));

    }

}