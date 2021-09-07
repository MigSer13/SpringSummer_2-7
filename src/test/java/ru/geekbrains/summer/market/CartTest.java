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

    @BeforeEach
    public void init(){
        cart.clear();
    }

    @Test
    public void changeQuantityTest(){
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

        Assertions.assertEquals(6, items.get(0).getQuantity());
    }
}
