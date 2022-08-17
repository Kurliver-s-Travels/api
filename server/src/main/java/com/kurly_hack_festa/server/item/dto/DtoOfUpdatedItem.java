package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <h1>DtoOfUpdatedItem</h1>
 * <p>
 *     Response Dto of get updated Item Entity Dto
 * </p>
 * <p>
 *     특정 아이템 엔티티가 수정된 후의 결과 dto
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemController
 * @see com.kurly_hack_festa.server.item.ItemService
 *
 * @author younghoCha
 */
@Builder
@Getter
public class DtoOfUpdatedItem {

    /**
     * 수정 후의 상품 이름
     */
    private String name;


    /**
     * 수정 후의 상품 위치
     */
    private Location location;

    /**
     * 수정 후의 상품 배송 수량
     */
    private int count;

    /**
     * 상품 id(pk)
     */
    private Long id;

    /**
     * 수정 후의 상품 배송 날짜
     */
    private LocalDate deliveryTime;
}
