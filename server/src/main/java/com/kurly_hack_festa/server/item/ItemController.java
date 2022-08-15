package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.item.dto.DtoOfCreateItem;
import com.kurly_hack_festa.server.item.dto.DtoOfCreatedItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/item")
    public ResponseEntity createItem(@RequestBody DtoOfCreateItem dtoOfCreateItem){
        DtoOfCreatedItem dtoOfCreatedItem = itemService.createItem(dtoOfCreateItem);

        return ResponseEntity.ok(dtoOfCreatedItem);
    }

}
