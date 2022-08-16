package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.item.dto.DtoOfCreateItem;
import com.kurly_hack_festa.server.item.dto.DtoOfCreatedItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>ItemController</h1>
 * <p>
 *     Item Controller in MVC
 * </p>
 * <p>
 *     아이템 컨트롤러
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemService
 * @author younghoCha
 */
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    /**
     * method what create Item
     * @param dtoOfCreateItem : 추가할 아이템에 대한 Request Body 데이터
     * @return : 추가된 후 결과 값
     */
    @PostMapping("/api/item")
    public ResponseEntity createItem(@RequestBody DtoOfCreateItem dtoOfCreateItem){
        DtoOfCreatedItem dtoOfCreatedItem = itemService.createItem(dtoOfCreateItem);

        return ResponseEntity.ok(dtoOfCreatedItem);
    }

}
