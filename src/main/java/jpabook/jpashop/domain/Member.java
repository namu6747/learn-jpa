package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public static Member data(){
        return Member.builder()
                .name("jaemin")
                .city("pusan")
                .street("999-7")
                .zipcode("12345")
                .orders(new ArrayList<>())
                .build();
    }

    public void addOrders(Order order){
        System.out.println(orders);
        orders.add(order);
    }

}
