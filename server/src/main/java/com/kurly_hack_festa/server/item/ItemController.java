package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.common.response.Response;
import com.kurly_hack_festa.server.item.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final String YEAR_MESSAGE = "년 ";
    private final String MONTH_MESSAGE = "월 ";
    private final String DAY_MESSAGE = "일 ";
    private final ItemService itemService;

    /**
     * method what create Item
     * @param dtoOfCreateItem : 추가할 아이템에 대한 Request Body 데이터
     * @return : 추가된 후 결과 값
     */
    @PostMapping("/api/item")
    public ResponseEntity createItem(@RequestBody DtoOfCreateItem dtoOfCreateItem){
        DtoOfCreatedItem dtoOfCreatedItem = itemService.createItem(dtoOfCreateItem);
        Response response = createResponse("상품이 정상적으로 등록되었습니다.", dtoOfCreatedItem);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * get item information by id
     * @param id : item pk(id)
     * @return : 조회된 아이템 1건에 대한 정보
     */
    @GetMapping("/api/item/{id}")
    public ResponseEntity getItemInformation(@PathVariable Long id){
        DtoOfGetItem dtoOfGetItem = itemService.getItemInformation(id);
        Response response = createResponse("상품 정보가 정상적으로 조회되었습니다.", dtoOfGetItem);

        return new ResponseEntity(response, HttpStatus.OK);

    }

    /**
     * get items information by delivery time
     * @param dtoOfRequestGetItemList : 아이템을 조회하기위해서 클라이언트에서 보낸 queryParmeter
     * @return : 조회된 결과 리스트
     */
    @GetMapping("/api/items")
    public ResponseEntity getItemList(DtoOfRequestGetItemList dtoOfRequestGetItemList, Pageable pageable){

        DtoOfGetItemList dtoOfGetItemList = itemService.getItemsInformation(dtoOfRequestGetItemList.getDeliveryTime(), pageable);

        String message = dtoOfRequestGetItemList.getDeliveryTime().getYear() + this.YEAR_MESSAGE
                + dtoOfRequestGetItemList.getDeliveryTime().getMonthValue() + this.MONTH_MESSAGE
                + dtoOfRequestGetItemList.getDeliveryTime().getDayOfMonth() + this.DAY_MESSAGE
                + "배송 상품들이 정상적으로 조회되었습니다.";
        if(dtoOfGetItemList.getItemList().isEmpty()){
            Response response = createResponse(message, dtoOfGetItemList);


            return new ResponseEntity(response, HttpStatus.OK);
        }
        Response response = createResponse(message, dtoOfGetItemList);

        return new ResponseEntity(response, HttpStatus.OK);


    }

    /**
     * response를 생성하기 위한 메소드
     * @param message : 서버 응답 메세지
     * @param content : 서버 응답 데이터
     * @return 생성된 Response 객체
     */
    public Response createResponse(String message, Object content){
        return Response.builder()
                .message(message)
                .content(content)
                .build();
    }

}
