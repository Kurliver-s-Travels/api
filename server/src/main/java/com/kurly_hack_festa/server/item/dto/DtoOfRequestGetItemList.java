package com.kurly_hack_festa.server.item.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h1>DtoOfRequestGetItemList</h1>
 * <p>
 *     Response Dto of get itemEntity List
 * </p>
 * <p>
 *     특정 배송 날짜의 아이템 엔티티를 page 처리하여 조회한 후의 response dto
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemController
 * @see com.kurly_hack_festa.server.item.ItemService
 *
 * @author younghoCha
 */
@Builder
@Getter
public class DtoOfRequestGetItemList {

    /**
     * 조회하려는 날짜
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryTime;
}
