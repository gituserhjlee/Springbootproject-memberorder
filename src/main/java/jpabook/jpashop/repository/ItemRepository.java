package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        if(item.getId()==null){ //새로생성하는 객체
            em.persist(item);//신규등록
        }else{ //이미 디비에 한번 들어간것
            em.merge(item);//업데이트같은개념
        }
    }
    public Item fineOne(Long id){
        return em.find(Item.class, id);
    }
    public List<Item> findAll(){
        //여러건 찾는건 jpql작성해야함
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
