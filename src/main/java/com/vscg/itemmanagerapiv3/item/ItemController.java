package com.vscg.itemmanagerapiv3.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Create - Add a new item
     *
     * @param item An object item
     * @return The item object saved
     */
    @PostMapping("/item")
    public Item createItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    /**
     * Read - Get one item
     *
     * @param id The id of the item
     * @return An Item object full filled
     */
    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable("id") final Long id) throws ItemNotFoundException {
        Optional<Item> item = itemService.getItem(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new ItemNotFoundException(id);
        }
    }


    /**
     * Read - Get all items
     *
     * @return - An Iterable object of Item full filled
     */
    @GetMapping("/items")
    public Iterable<Item> getAllItems() {
        return itemService.getAllItems();
    }

    /**
     * Update - Update an existing item
     *
     * @param id      - The id of the newItem to update
     * @param newItem - The newItem object updated
     * @return - The item updated
     */
    @PutMapping("/item/{id}")
    public Item updateItem(@PathVariable("id") final Long id, @RequestBody Item newItem) throws ItemNotFoundException {
        return itemService.updateItem(id, newItem);
    }



    /**
     * Delete - Delete an item
     *
     * @param id - The id of the item to delete
     */
    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable("id") final Long id) {
        itemService.deleteItem(id);
    }
}