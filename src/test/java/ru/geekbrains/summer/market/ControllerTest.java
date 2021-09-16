package ru.geekbrains.summer.market;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.summer.market.dto.ProductDto;
import ru.geekbrains.summer.market.model.Product;
import ru.geekbrains.summer.market.repositories.ProductRepository;
import ru.geekbrains.summer.market.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void findAllProductsTest() throws Exception {
        Product productTomato = new Product();
        productTomato.setId(1L);
        productTomato.setTitle("Tomato");
        productTomato.setPrice(BigDecimal.valueOf(10));

        Product productApple = new Product();
        productApple.setId(2L);
        productApple.setTitle("Apple");
        productApple.setPrice(BigDecimal.valueOf(20));

        List<Product> productList = new ArrayList<>();
        productList.add(productTomato);
        productList.add(productApple);
        Page<Product> productPage = new PageImpl<Product>(productList);

        Mockito.doReturn(productList).when(productService.findAll());
        //given(productService.findPage(1, 5, null)).willReturn(productPage);

        mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
