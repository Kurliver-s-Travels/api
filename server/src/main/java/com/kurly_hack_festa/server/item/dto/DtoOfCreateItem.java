package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DtoOfCreateItem {

    private String name;
    private int count;
    private Location location;
    private LocalDateTime deliveryTime;
}
