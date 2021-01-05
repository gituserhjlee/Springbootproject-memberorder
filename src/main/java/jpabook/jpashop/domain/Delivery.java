package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //순서 밀릴때 오류 안나게 꼭 스트링으로쓰자
    private DeliveryStatus status;
<<<<<<< HEAD
}
=======
}
>>>>>>> e8d7ca10d5be7b00d94cf4b615b5199542d8bd70
