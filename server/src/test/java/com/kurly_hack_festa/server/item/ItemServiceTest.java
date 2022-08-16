package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.item.dto.DtoOfCreateItem;
import com.kurly_hack_festa.server.item.dto.DtoOfCreatedItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.kurly_hack_festa.server.item.util.ItemUtil.setUpItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = ItemService.class)
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

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







}
