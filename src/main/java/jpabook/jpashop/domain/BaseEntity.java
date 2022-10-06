package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    private String createdBy = "jaemin";
    private LocalDateTime createdDate = LocalDateTime.now();
    private String lastModifiedBy = "jaemin";
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

}
