package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.item.dto.DtoOfCreateItem;
import com.kurly_hack_festa.server.item.dto.DtoOfCreatedItem;
import com.kurly_hack_festa.server.item.dto.DtoOfGetItem;
import com.kurly_hack_festa.server.item.dto.DtoOfGetItemList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 아이템을 생성하기 위한 메소드<br>
     * 같은 이름과 같은 날짜의 아이템이 이미 등록되어 있다면 새롭게 생성하지 않고 개수를 추가한다.
     * @param dtoOfCreateItem : request of creating item dto
     * @return : response dto
     */
    public DtoOfCreatedItem createItem(DtoOfCreateItem dtoOfCreateItem){

        if(checkIsExists(dtoOfCreateItem.getName(), dtoOfCreateItem.getDeliveryTime())){
            Item savedItemEntity = getItemEntityByNameAndTime(dtoOfCreateItem.getName(), dtoOfCreateItem.getDeliveryTime());
            savedItemEntity.increaseCount(dtoOfCreateItem.getCount());

            return DtoOfCreatedItem.builder()
                    .id(savedItemEntity.getId())
                    .count(savedItemEntity.getCount())
                    .deliveryTime(savedItemEntity.getDeliveryTime())
                    .location(savedItemEntity.getLocation())
                    .name(savedItemEntity.getName())
                    .build();
        };


        Item newItem = Item.builder()
                .count(dtoOfCreateItem.getCount())
                .location(dtoOfCreateItem.getLocation())
                .name(dtoOfCreateItem.getName())
                .deliveryTime(dtoOfCreateItem.getDeliveryTime())
                .build();

        Item savedItemEntity = itemRepository.save(newItem);

        return DtoOfCreatedItem.builder()
                .id(savedItemEntity.getId())
                .count(savedItemEntity.getCount())
                .deliveryTime(savedItemEntity.getDeliveryTime())
                .location(savedItemEntity.getLocation())
                .name(savedItemEntity.getName())
                .build();
    }

    /**
     * check Existing that Entity what pre created Eneity
     * @param itemName : Item Entity name field
     * @param deliveryTime : Item Entity deliveryTime field
     * @return : found ItemEntity
     */
    public boolean checkIsExists(String itemName, LocalDate deliveryTime){
        return itemRepository.existsByNameAndDeliveryTime(itemName, deliveryTime);
    }

    /**
     * get Item Entity By name, deliveryTime
     * @param name : Item Entity name field
     * @param deliveryTime : Item Entity delivery time field
     * @return : found ItemEntity
     */
    public Item getItemEntityByNameAndTime(String name, LocalDate deliveryTime){
        //fixme .get -> elseThrow로 고쳐야함
        return itemRepository.findByNameAndDeliveryTime(name, deliveryTime).get();
    }

    /**
     * get Item Entity By id(pk)
     * @param id : Item Entity id(pk)
     * @return : found ItemEntity
     */
    public Item getItemEntityById(Long id){
        //fixme .get -> elseThrow로 고쳐야함
        return itemRepository.findById(id).get();
    }

    /**
     * get Information By id(pk)
     * @param id : Item Entity id(pk)
     * @return : Dto of Item Information
     */
    public DtoOfGetItem getItemInformation(Long id){
        Item itemEntity = getItemEntityById(id);

        return DtoOfGetItem.builder()
                .id(itemEntity.getId())
                .count(itemEntity.getCount())
                .deliveryTime(itemEntity.getDeliveryTime())
                .location(itemEntity.getLocation())
                .name(itemEntity.getName())
                .build();
    }

    /**
     * 해당 배송 날짜에 배송해야하는 모든 아이템 값을 조회한다.
     * @param deliveryTime : 배송 날짜
     * @return : 배송 날짜를 통해 조회된 아이템 정보 Dto
     */
    public DtoOfGetItemList getItemsInformation(LocalDate deliveryTime, Pageable pageable){
        Page<Item> itemList = itemRepository.findAllByDeliveryTime(deliveryTime, pageable);

        List<DtoOfGetItem> dtoOfGetItemList = itemList.getContent()
                .stream().map(v -> DtoOfGetItem.builder()
                        .count(v.getCount())
                        .deliveryTime(v.getDeliveryTime())
                        .location(v.getLocation())
                        .id(v.getId())
                        .name(v.getName())
                        .build())
                .collect(Collectors.toList());

        return  DtoOfGetItemList.builder()
                .offset(itemList.getPageable().getOffset())
                .pageNumber(itemList.getPageable().getPageNumber())
                .totalElements(itemList.getTotalElements())
                .totalPages(itemList.getTotalPages())
                .itemList(dtoOfGetItemList)
                .build();

    }

}
