package com.kurly_hack_festa.server.item;


import com.jayway.jsonpath.internal.Utils;
import com.kurly_hack_festa.server.item.dto.DtoOfCreateItem;
import com.kurly_hack_festa.server.item.dto.DtoOfCreatedItem;
import com.kurly_hack_festa.server.item.dto.DtoOfGetItem;
import com.kurly_hack_festa.server.item.dto.DtoOfGetItemList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kurly_hack_festa.server.item.util.ItemUtil.setUpItem;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = ItemService.class)
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;
    @MockBean
    private org.aspectj.weaver.Utils utils;

    @MockBean
    private Pageable pageable;

    @MockBean
    private Page<Item> itemMockList;

    @DisplayName("item 이 정상적으로 등록되어야 한다.")
    @Test
    public void check_create_item_OnSuccess() throws Exception{

        //given
        Item sampleItem = setUpItem();
        DtoOfCreateItem sampleCreateItemDto = DtoOfCreateItem.builder()
                .count(sampleItem.getCount())
                        .deliveryTime(sampleItem.getDeliveryTime())
                                .location(sampleItem.getLocation())
                                        .name(sampleItem.getName())
                                                .build();

        //when
        given(itemRepository.save(any())).willReturn(sampleItem);
        DtoOfCreatedItem actualResult = itemService.createItem(sampleCreateItemDto);

        //then
        Assertions.assertEquals(sampleItem.getId(), actualResult.getId());

    }

    @DisplayName("item 이 사전에 등록되어있다면 사전에 등록한 item 에 개수만 추가한다.")
    @Test
    public void check_preCreate_item() throws Exception{

        //given & mocking
        Item item = setUpItem();
        given(itemRepository.existsByNameAndDeliveryTime(item.getName(), item.getDeliveryTime())).willReturn(true);
        given(itemRepository.findByNameAndDeliveryTime(anyString(), any())).willReturn(Optional.of(item));
        DtoOfCreateItem sampleDto = DtoOfCreateItem.builder()
                .count(item.getCount())
                .deliveryTime(item.getDeliveryTime())
                .location(item.getLocation())
                .name(item.getName())
                .build();
        //when
        DtoOfCreatedItem actualResult = itemService.createItem(sampleDto);

        //then
        Assertions.assertEquals(400, actualResult.getCount());
        Assertions.assertEquals(item.getId(), actualResult.getId());
        Assertions.assertEquals(item.getLocation(), actualResult.getLocation());
        Assertions.assertEquals(item.getName(), actualResult.getName());
        Assertions.assertEquals(item.getDeliveryTime(), actualResult.getDeliveryTime());


    }

    @DisplayName("사전에 등록한 item 이고, 같은 날짜면 true 를 리턴한다.")
    @Test
    public void add_item_preCreate_same_deliveryTime() throws Exception{

        //given & mocking
        Item sampleItem = setUpItem();
        given(itemRepository.existsByNameAndDeliveryTime(sampleItem.getName(), sampleItem.getDeliveryTime()))
                .willReturn(true);

        //when
        boolean actualResult = itemService.checkIsExists(sampleItem.getName(), sampleItem.getDeliveryTime());

        //then
        Assertions.assertTrue(actualResult);

    }

    @DisplayName("사전에 등록한 item 이고, 다른 날짜면 false 를 리턴한다.")
    @Test
    public void add_item_preCreate_not_same_deliveryTime() throws Exception{

        //given & moking
        Item sampleItem = setUpItem();
        given(itemRepository.existsByNameAndDeliveryTime(sampleItem.getName(), sampleItem.getDeliveryTime()))
                .willReturn(false);

        //when
        boolean actualResult = itemService.checkIsExists(sampleItem.getName(), sampleItem.getDeliveryTime());

        //then
        Assertions.assertFalse(actualResult);

    }

    @DisplayName("findById일 경우 정상적으로 item 이 리턴되어야 한다.")
    @Test
    public void check_getItemEntityById_OnSuccess() throws Exception{

        //given & mocking
        Item sampleItem = setUpItem();
        given(itemRepository.findById(1L)).willReturn(Optional.of(sampleItem));

        //when
        Item actualItem = itemService.getItemEntityById(1L);

        //then
        Assertions.assertEquals(sampleItem.getId(), actualItem.getId());
        Assertions.assertEquals(sampleItem.getName(), actualItem.getName());
        Assertions.assertEquals(sampleItem.getLocation(), actualItem.getLocation());
        Assertions.assertEquals(sampleItem.getDeliveryTime(), actualItem.getDeliveryTime());
        Assertions.assertEquals(sampleItem.getCount(), actualItem.getCount());

    }

    @DisplayName("findByNameAndDeliveryTime일 경우 정상적으로 item이 리턴되어야 한다.")
    @Test
    public void check_findByNameAndDeliveryTime_OnSuccess() throws Exception{

        //given & mocking
        Item sampleItem = setUpItem();
        given(itemRepository.findByNameAndDeliveryTime(anyString(), any())).willReturn(Optional.of(sampleItem));

        //when
        Item actualResult = itemService.getItemEntityByNameAndTime(sampleItem.getName(), sampleItem.getDeliveryTime());

        //then
        Assertions.assertEquals(sampleItem.getId(), actualResult.getId());
        Assertions.assertEquals(sampleItem.getName(), actualResult.getName());
        Assertions.assertEquals(sampleItem.getLocation(), actualResult.getLocation());
        Assertions.assertEquals(sampleItem.getDeliveryTime(), actualResult.getDeliveryTime());
        Assertions.assertEquals(sampleItem.getCount(), actualResult.getCount());

    }


    @DisplayName("아이템 1건이 정상적으로 조회되어야 한다.")
    @Test
    public void get_information_item() throws Exception{

        //given & mocking
        Item item = setUpItem();
        DtoOfGetItem expectedResult = DtoOfGetItem.builder()
                .name(item.getName())
                .id(item.getId())
                .location(item.getLocation())
                .deliveryTime(item.getDeliveryTime())
                .count(item.getCount()).build();

        given(itemRepository.findById(anyLong())).willReturn(Optional.of(item));
        //when
        DtoOfGetItem actualResult = itemService.getItemInformation(1L);

        //then
        Assertions.assertEquals(expectedResult.getCount(), actualResult.getCount());
        Assertions.assertEquals(expectedResult.getId(), actualResult.getId());
        Assertions.assertEquals(expectedResult.getName(), actualResult.getName());
        Assertions.assertEquals(expectedResult.getDeliveryTime(), actualResult.getDeliveryTime());
        Assertions.assertEquals(expectedResult.getLocation(), actualResult.getLocation());

    }
    //todo pagination 테스트 진행해야함
//    @DisplayName("해당 배송 날짜의 모든 아이템들이 조회되어야 한다.")
//    @Test
//    public void get_itemList_by_deliveryTime() throws Exception{
//
//        //given & mocking
//        Item item = setUpItem();
//
//        List<Item> itemList = new ArrayList<>();
//        itemList.add(item);
//        itemList.add(item);
//        itemList.add(item);
//        Pageable pageable = PageRequest.of(0, 10);
//        given(itemRepository.findAllByDeliveryTime(any(), any())).willReturn(itemMockList);
//        //when
//        DtoOfGetItemList actualResult = itemService.getItemsInformation(LocalDate.now(), pageable);
//
//        //then
//        Assertions.assertNotNull(actualResult);
//
//    }
    







}
