package jpabook.jpashop.domain;

import lombok.Data;

import javax.persistence.*;

//@Entity
@Data
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address = Address.getAddress();

}
