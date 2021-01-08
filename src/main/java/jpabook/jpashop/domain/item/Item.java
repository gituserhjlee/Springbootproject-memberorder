package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //상속전략임 한테이블에 다 때려박는
@DiscriminatorColumn(name="dtype") //저장할때 구분되려고
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories=new ArrayList<>();

    //비즈니스 로직

    //재고 증가
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }
    //재고 감소
    public void removeStock(int quantity){
        int restStock=this.stockQuantity-quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");

        }
        this.stockQuantity=restStock;

    }

    public void change(Item item,String name, int price, int stockQuantity) {
        item.setPrice(price);
        item.setName(name);
        item.setStockQuantity(stockQuantity);
    }
}
