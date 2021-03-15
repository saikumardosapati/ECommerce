package com.dosapati.ecommerce.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-integrationtest.properties")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    private OrderDetail orderDetail;

    @Before
    public void setUp() throws Exception {
        orderDetail = new OrderDetail();
        //TODO complete the orderdetail obj
    }

    @Test
    public void shouldReceiveItemDetailLevelResponseFromAgent() {

        String orderConfirmation = orderService.createOrder(orderDetail);
        assertNotNull(orderConfirmation);
    }

    

}
