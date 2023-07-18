package com.vscg.itemmanagerapiv3.item;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Optional<Item> getItem(final Long id) {
        return itemRepository.findById(id);
    }

    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(final Long id) {
        itemRepository.deleteById(id);
    }

    public Item saveItem(Item item) {
        Item savedItem = itemRepository.save(item);
        return savedItem;
    }

    public Item updateItem(Long id, Item newItem) throws ItemNotFoundException {
        Optional<Item> currentItemOptional = getItem(id);
        if (currentItemOptional.isPresent()) {
            Item currentItem = currentItemOptional.get();
            currentItem.setTitle(newItem.getTitle());
            currentItem.setFullDescription(newItem.getFullDescription());
            currentItem.setPrice(newItem.getPrice());
            saveItem(currentItem);
            return currentItem;
        } else {
            throw new ItemNotFoundException(id);
        }
    }
}