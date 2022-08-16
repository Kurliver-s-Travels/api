package com.kurly_hack_festa.server.item.util;

import com.kurly_hack_festa.server.item.Item;
import com.kurly_hack_festa.server.item.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * item을 테스트에 사용하기 위한 Util 테스트 클래스입니다.
 */
public class ItemUtil {

    public static Item setUpItem(){
        return Item.builder()
                .id(1L)
                .name("우유")
                .location(Location.A)
                .count(200)
                .deliveryTime(LocalDate.now())
                .build();
    }
}
