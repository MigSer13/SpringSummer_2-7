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

@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    private List<OrderItemDto> items;

    @BeforeEach
    public void init(){
        cart.clear();

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(1L);
        orderItemDto.setQuantity(1);
        orderItemDto.setPricePerProduct(BigDecimal.valueOf(10));
        orderItemDto.setPrice(BigDecimal.valueOf(10));
        orderItemDto.setProductTitle("test");

        List<OrderItemDto> items = cart.getItems();
        cart.getItems().add(orderItemDto);
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
        cart.remove(2L);
        Assertions.assertEquals(1, cart.getItems().size());
    }
}
