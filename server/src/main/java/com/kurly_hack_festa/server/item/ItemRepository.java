package com.kurly_hack_festa.server.item;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
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
    boolean existsByNameAndDeliveryTime(String name, LocalDateTime localDateTime);

    /**
     * 아이템 이름과 아이템 배송날짜를 통해 엔티티를 select하기 위한 메소드
     * @param name : 아이템 이름
     * @param localDateTime : 아이템 배송날짜
     * @return : 찾은 아이템 wrapped Optional
     */
    Optional<Item> findByNameAndDeliveryTime(String name, LocalDateTime localDateTime);
}
