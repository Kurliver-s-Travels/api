package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h1>DtoOfGetItem</h1>
 * <p>
 *     Request Dto of get itemEntity
 * </p>
 * <p>
 *     아이템 엔티티를 조회하기위한 응답 DTO
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemController
 * @see com.kurly_hack_festa.server.item.ItemService
 *
 * @author younghoCha
 */
@Getter
@Builder
public class DtoOfGetItem {

    /**
     * 조회된 아이템 엔티티의 이름
     */
    private String name;

    /**
     * 조회된 아이템 엔티티의 배송수량
     */
    private int count;

    /**
     * 조회된 아이템 엔티티의 창고 위치
     */
    private Location location;

    /**
     * 조회된 아이템 엔티티의 배송 날짜
     */
    private LocalDate deliveryTime;

    /**
     * 조회된 아이템 엔티티의 id(pk)
     */
    private Long id;
}
