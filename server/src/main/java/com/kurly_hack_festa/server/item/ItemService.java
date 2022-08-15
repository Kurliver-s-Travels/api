package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.item.dto.DtoOfCreateItem;
import com.kurly_hack_festa.server.item.dto.DtoOfCreatedItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public DtoOfCreatedItem createItem(DtoOfCreateItem dtoOfCreateItem){

        Item newItem = Item.builder()
                .count(dtoOfCreateItem.getCount())
                .location(dtoOfCreateItem.getLocation())
                .name(dtoOfCreateItem.getName())
                .build();

        Item savedItemEntity = itemRepository.save(newItem);

        return DtoOfCreatedItem.builder()
                .id(savedItemEntity.getId())
                .build();
    }
}
