package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <h1>DtoOfCreateItem</h1>
 * <p>
 *     Request Dto of create itemEntity
 * </p>
 * <p>
 *     아이템 엔티티를 생성하기 위한 요청 바디 Dto
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemController
 * @see com.kurly_hack_festa.server.item.ItemService
 *
 * @author younghoCha
 */
@Getter
@Builder
public class DtoOfCreateItem {

    /**
     * 생성할 아이템 이름
     */
    private String name;

    /**
     * 생성할 아이템의 배송 수량
     */
    private int count;

    /**
     * 생성할 아이템의 창고 위치
     */
    private Location location;

    /**
     * 생성할 아이템의 배송 날짜
     */
    private LocalDate deliveryTime;
}
