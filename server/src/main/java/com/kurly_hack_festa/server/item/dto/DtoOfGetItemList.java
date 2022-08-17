package com.kurly_hack_festa.server.item.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * <h1>DtoOfGetItemList</h1>
 * <p>
 *     Request Dto of get itemEntity List
 * </p>
 * <p>
 *     특정 배송 날짜의 아이템 엔티티를 page처리하여 조회하기위한 request dto
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemController
 * @see com.kurly_hack_festa.server.item.ItemService
 *
 * @author younghoCha
 */
@Builder
@Getter
public class DtoOfGetItemList {

    private List<DtoOfGetItem> itemList;

    private int totalPages;

    private long totalElements;

    private long offset;

    private int pageNumber;
}
