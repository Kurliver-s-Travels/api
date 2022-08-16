package com.kurly_hack_festa.server.item;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndDeliveryTime(String name, LocalDateTime localDateTime);
    Optional<Item> findByNameAndDeliveryTime(String name, LocalDateTime localDateTime);
}
