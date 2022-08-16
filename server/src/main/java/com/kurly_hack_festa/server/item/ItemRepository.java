package com.kurly_hack_festa.server.item;


import com.kurly_hack_festa.server.item.dto.DtoOfGetItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * 아이템의 존재 유무 확인 메소드
     * @param name : where name을 적용하기위한 파라미터
     * @return : 존재할 경우 true, 존재하지 않을 경우 false
     */
    boolean existsByName(String name);

    /**
     * 아이템의 존재 유무 확인 메소드
     * @param name : where name을 적용하기 위한 파라미터
     * @param localDateTime : where deliveryTime을 적용하기 위한 파라미터
     * @return 존재할 경우 true, 존재하지 않을 경우 false
     */
    boolean existsByNameAndDeliveryTime(String name, LocalDate localDateTime);

    /**
     * 아이템 이름과 아이템 배송날짜를 통해 엔티티를 select하기 위한 메소드
     * @param name : 아이템 이름
     * @param localDateTime : 아이템 배송날짜
     * @return : 찾은 아이템 wrapped Optional
     */
    Optional<Item> findByNameAndDeliveryTime(String name, LocalDate localDateTime);


    /**
     * 아이템의 배송 날짜를 통해 해당 배송날짜의 모든 엔티티를 조회하기위한 메소드
     * @param deliveryTime : 배송 날짜
     * @return : 해당 배송 날짜에 배송되어야할 모든 아이템 엔티티
     */
    List<Item> findAllByDeliveryTime(LocalDate deliveryTime);

    /**
     * 아이템의 배송 날짜를 통해 해당 배송날짜의 모든 엔티티를 pagination 조회하기위한 메소드
     * @param deliveryTime : 배송 날짜
     * @param pageable : 페이징을 위한 offset, limit
     * @return : 해당 배송 날짜에 배송되어야할 모든 아이템 Page 엔티티
     */
    Page<Item> findAllByDeliveryTime(LocalDate deliveryTime, Pageable pageable);
}
