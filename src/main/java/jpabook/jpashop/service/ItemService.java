package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//service는 repository에 위임만하는 클래스라고 볼수있음
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){ //merge랑 똑같은 역할임
        Item findItem = itemRepository.findOne(itemId);
//        findItem.setPrice(price);
//        findItem.setName(name);
//        findItem.setStockQuantity(stockQuantity);
        findItem.change(findItem,name, price, stockQuantity);
        //이부분을 다 set으로 하지 말고 메소드로 만들어야지 코드 추적이 쉬워짐
    }
    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
