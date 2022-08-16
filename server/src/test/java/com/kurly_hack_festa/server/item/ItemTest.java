package com.kurly_hack_festa.server.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.kurly_hack_festa.server.item.util.ItemUtil.setUpItem;

public class ItemTest {

    @DisplayName("Item 객체는 정상적으로 생성되어야 한다.")
    @Test
    public void create_ItemEntity_OnSuccess() throws Exception{

        //given
        //when
        Item item = setUpItem();

        //then
        Assertions.assertNotNull(item);
        Assertions.assertEquals(item.getId(), 1L);
        Assertions.assertEquals(item.getName(), "우유");
        Assertions.assertEquals(item.getCount(), 200);
        Assertions.assertEquals(item.getLocation(), Location.A);

    }

    @DisplayName("item 의 count 개수는 정상적으로 줄어들어야 한다.")
    @Test
    public void decrease_count_OnSuccess() throws Exception{

        //given
        Item item = setUpItem();
        //when
        item.decreaseCount(100);

        //then
        Assertions.assertEquals(item.getCount(), 100);

    }

    @DisplayName("제품의 count는 음수가 될 경우에 0으로 되어야 한다.")
    @Test
    public void check_count_minus() throws Exception{

        //given
        Item item = setUpItem();

        //when
        item.decreaseCount(250);

        //then
        Assertions.assertEquals(0, item.getCount());

    }

    @DisplayName("item의 count 가 plus될 경우 정상으로 개수가 증가해야한다.")
    @Test
    public void check_increase_count() throws Exception{

        //given
        Item sampleItem = setUpItem();

        //when
        sampleItem.increaseCount(100);

        //then
        Assertions.assertEquals(300, sampleItem.getCount());

    }




}
