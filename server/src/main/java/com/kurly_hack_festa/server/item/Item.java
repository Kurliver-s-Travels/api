package com.kurly_hack_festa.server.item;

import com.kurly_hack_festa.server.item.dto.DtoOfUpdateItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <h1>Item</h1>
 * <p>
 *     Entity of Item
 * </p>
 * <p>
 *     아이템에 대한 엔티티
 * </p>
 *
 * @see com.kurly_hack_festa.server.item.ItemRepository
 * @author younghoCha
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * item name
     */
    @Column(name = "NAME")
    private String name;

    /**
     * count to delivery
     */
    @Column(name = "COUNT")
    private int count;

    /**
     * item location in storage
     */
    @Column(name = "LOCATION")
    @Enumerated(EnumType.STRING)
    private Location location;

    /**
     * delivery Date
     */
    @Column(name = "DELIVERY_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryTime;

    /**
     * decrease Count method
     * @param minusCount : 감소해야할 수량
     * @return : 감소된 후 수량
     */
    public int decreaseCount(int minusCount){

        if(count < minusCount){
            this.count = 0;
            return 0;
        }
        this.count = count - minusCount;

        return count;
    }

    /**
     * increase count method
     * @param plusCount : 추가해야할 수량
     * @return : 추가된 후 수량
     */
    public int increaseCount(int plusCount){

        this.count = count + plusCount;

        return count;
    }

    /**
     * 아이템의 내용을 수정하기 위한 메서드
     * @param dtoOfUpdateItem : 사용자가 요청한 아이템 수정 데이터 dto
     * @return : 수정한 후의 아이템 내용
     */
    public Item updateItem(DtoOfUpdateItem dtoOfUpdateItem){
        this.count = dtoOfUpdateItem.getCount();
        this.deliveryTime = dtoOfUpdateItem.getDeliveryTime();
        this.name = dtoOfUpdateItem.getName();
        this.location = dtoOfUpdateItem.getLocation();
        return this;
    }

}
