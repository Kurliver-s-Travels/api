package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h1>DtoOfCreatedItem</h1>
 * <p>
 *     after created Item Dto Of Response Dto
 * </p>
 * <p>
 *     아이템 엔티티가 생성된 후 client에 response될 Dto 객체
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemService
 * @author younghoCha
 */
@Builder
@Getter
public class DtoOfCreatedItem {

    /**
     * 생성된 아이템 엔티티의 primary key
     */
    private Long id;

    /**
     * 생성된 아이템 엔티티의 이름
     */
    private String name;

    /**
     * 생성된 아이템 엔티티의 배송날짜
     */
    private LocalDate deliveryTime;

    /**
     * 생성된 아이템 엔티티의 배송 수량
     */
    private int count;

    /**
     * 생성된 아이템 엔티티가 존재하는 창고 위치
     */
    private Location location;
}
