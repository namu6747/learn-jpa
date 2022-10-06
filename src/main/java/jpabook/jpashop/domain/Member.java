package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

//@Entity
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

    @Embedded
    private Address homeAddress;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @ElementCollection(fetch = LAZY) // default
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    @Builder.Default
    private Set<String> favoriteFoods = new HashSet<>();

//    @OrderColumn(name = "ADDRESS_HISTORY_ORDER")
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name = "MEMBER_ID")
//    )
//    @Builder.Default
//    private List<Address> addressHistory = new ArrayList<>();

    // 일대다 단방향
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    @Builder.Default
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public static Member data(){
        return Member.builder()
                .name("jaemin")
                .homeAddress(addressInit())
                .build();
    }

    public void addOrders(Order order){
        System.out.println(orders);
        orders.add(order);
    }

    private static Address addressInit(){
        return new Address("pusan","999-7","12345");
    }

}
