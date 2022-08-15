package com.kurly_hack_festa.server.item.dto;

import com.kurly_hack_festa.server.item.Location;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DtoOfCreateItem {

    private String name;
    private int count;
    private Location location;
}
