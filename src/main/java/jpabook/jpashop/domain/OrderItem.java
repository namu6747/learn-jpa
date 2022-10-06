package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;

//@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "order")
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    private int orderPrice;

    private int count;

    public static OrderItem data(Item item){
        return OrderItem.builder()
                .item(item)
                .orderPrice(item.getPrice() * (item.getStockQuantity() / 5))
                .count(item.getStockQuantity() / 5)
                .build();
    }

    public void setOrder(Order order){
        this.order = order;
        order.addOrderItem(this);
    }
}
