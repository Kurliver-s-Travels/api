package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <h1>DtoOfUpdateItem</h1>
 * <p>
 *     Request Dto of update itemEntity
 * </p>
 * <p>
 *     특정 아이템의 내용을 수정하기 위한 request dto
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemController
 * @see com.kurly_hack_festa.server.item.ItemService
 *
 * @author younghoCha
 */
@Builder
@Getter
public class DtoOfUpdateItem {

    /**
     * 수정하려는 아이템 id
     */
    private Long id;
    /**
     * 수정 전 배송 수량
     */
    private int preCount;

    /**
     * 수정 전 배송 날짜
     */
    private LocalDate preDeliveryTime;

    /**
     * 수정 전 배송 아이템 이름
     */
    private String preName;

    /**
     * 수정 전 배송 아이템 위치
     */
    private Location preLocation;

    /**
     * 수정할 배송 수량
     */
    private int count;

    /**
     * 수정할 이름
     */
    private String name;

    /**
     * 수정할 배송 날짜
     */
    private LocalDate deliveryTime;

    /**
     * 수정할 상품 위치
     */
    private Location location;

}
