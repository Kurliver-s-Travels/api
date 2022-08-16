package com.kurly_hack_festa.server.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "LOCATION")
    @Enumerated(EnumType.STRING)
    private Location location;

    @Column(name = "DELIVERY_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deliveryTime;

    public int decreaseCount(int minusCount){

        if(count < minusCount){
            this.count = 0;
            return 0;
        }
        this.count = count - minusCount;

        return count;
    }

}
