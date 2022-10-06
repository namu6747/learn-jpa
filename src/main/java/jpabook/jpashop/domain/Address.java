package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;

@Embeddable
@Getter
// 값 타입이라 Setter 가 없다.
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Transient
    private static Long sequence = 1L;
    private String city;
    private String street;
    private String zipcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!getCity().equals(address.getCity())) return false;
        if (!getStreet().equals(address.getStreet())) return false;
        return getZipcode().equals(address.getZipcode());
    }

    @Override
    public int hashCode() {
//        int result = getCity().hashCode();
//        result = 31 * result + getStreet().hashCode();
//        result = 31 * result + getZipcode().hashCode();
//        return result;
        return Objects.hash(getCity(),getStreet(),getZipcode());
    }

    public static Address getAddress(){
        String a = "addr" + sequence++;
        return new Address(a,a,a);
    }

}
