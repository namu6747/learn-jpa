package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "categories")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Item extends BaseEntity {
    // 아이템만 단독으로 생성할 필요 없으면 추상

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public static Item data(){
        return Item.builder()
                .name("product")
                .price(10000)
                .stockQuantity(100)
                .build();
    }

    public void addCategories(Category category){
        categories.add(category);
    }

}
