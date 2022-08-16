package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
public class DtoOfCreatedItem {

    private Long id;
    private String name;
    private LocalDateTime deliveryTime;
    private int count;
    private Location location;
}
