package com.kurly_hack_festa.server.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @DisplayName("Item 객체는 정상적으로 생성되어야 한다.")
    @Test
    public void create_ItemEntity_OnSuccess() throws Exception{

        //given
        //when
        Item item = Item.builder()
                .id(1L)
                .name("우유")
                .location(Location.A)
                .count(200)
                .build();

        //then
        Assertions.assertNotNull(item);
        Assertions.assertEquals(item.getId(), 1L);
        Assertions.assertEquals(item.getName(), "우유");
        Assertions.assertEquals(item.getCount(), 200);
        Assertions.assertEquals(item.getLocation(), Location.A);

    }

    @DisplayName("item의 count 개수는 정상적으로 줄어들어야 한다.")
    @Test
    public void decrease_count_OnSuccess() throws Exception{

        //given
        Item item = Item.builder()
                .id(1L)
                .name("우유")
                .location(Location.A)
                .count(200)
                .build();
        //when
        item.decreaseCount(100);

        //then
        Assertions.assertEquals(item.getCount(), 100);

    }

    @DisplayName("제품의 count는 음수가 될 경우에 0으로 되어야 한다.")
    @Test
    public void check_count_minus() throws Exception{

        //given
        Item item = Item.builder()
                .id(1L)
                .name("우유")
                .location(Location.A)
                .count(200)
                .build();

        //when
        item.decreaseCount(250);

        //then
        Assertions.assertEquals(0, item.getCount());

    }



}
