package ru.geekbrains.summer.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.summer.market.dto.OrderItemDto;
import ru.geekbrains.summer.market.utils.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    private List<OrderItemDto> items;

    @BeforeEach
    public void init(){
        cart.clear();

        OrderItemDto orderItemDto1 = new OrderItemDto();
        orderItemDto1.setProductId(1L);
        orderItemDto1.setQuantity(1);
        orderItemDto1.setPricePerProduct(BigDecimal.valueOf(10));
        orderItemDto1.setPrice(BigDecimal.valueOf(10));
        orderItemDto1.setProductTitle("test1");

        OrderItemDto orderItemDto2 = new OrderItemDto();
        orderItemDto2.setProductId(2L);
        orderItemDto2.setQuantity(1);
        orderItemDto2.setPricePerProduct(BigDecimal.valueOf(10));
        orderItemDto2.setPrice(BigDecimal.valueOf(10));
        orderItemDto2.setProductTitle("test2");

        List<OrderItemDto> items = cart.getItems();
        cart.getItems().add(orderItemDto1);
        cart.getItems().add(orderItemDto2);
        cart.changeQuantity(1L, 2);
        cart.changeQuantity(1L, 3);
        cart.changeQuantity(2L, 5);
    }

    @Test
    public void changeQuantityTest(){
        Assertions.assertEquals(6, items.get(0).getQuantity());
    }

    @Test
    public void removeToCartTest(){
        cart.remove(1L);
        System.out.println(cart.getItems());
        Assertions.assertEquals(1, cart.getItems().size());
    }
}
