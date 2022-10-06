package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "order")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    public static Delivery data() {
        return Delivery.builder()
                .address(Address.getAddress())
                .status(DeliveryStatus.START)
                .build();
    }

    public void setOrder(Order order){
        this.order = order;
    }
}