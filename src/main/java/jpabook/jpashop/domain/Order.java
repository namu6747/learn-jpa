package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "ORDERS")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orderItems")
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 라이프 사이클을 따로 관리하고 싶다면 상황에 따라 cascade 제거
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID") // 주 테이블에 넣었다.
    private Delivery delivery;

    public static Order data(Member member, Delivery delivery) {
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .delivery(delivery)
                .orderItems(new ArrayList<>())
                .build();
        order.setMember(member);
        delivery.setOrder(order);
        return order;
    }

    private void setMember(Member member){
        this.member = member;
        member.addOrders(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }

}
