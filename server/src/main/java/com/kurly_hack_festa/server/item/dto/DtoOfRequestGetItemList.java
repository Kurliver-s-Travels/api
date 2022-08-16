package com.kurly_hack_festa.server.item.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Getter
public class DtoOfRequestGetItemList {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryTime;
}
