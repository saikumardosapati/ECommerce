package com.dosapati.ecommerce.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

        orderDetail.setOrder_customer_id("SaiDosapati123");
        orderDetail.setOrder_sub_total(105);
        orderDetail.setOrder_tax(8);
        orderDetail.setOrder_shipping_charges(7);
        orderDetail.setOrder_total(120);

        List<OrderItem> items = new ArrayList();
        OrderItem item = new OrderItem();
        item.setOrder_item_name("Books");
        item.setOrder_item_qty(10);
        item.setOrder_type(OrderType.SHIPPING);
        item.setOrder_shipping_addressline1("100 Receiving Ave");
        item.setOrder_shipping_city("Books city");
        item.setOrder_shipping_state("Library state");
        item.setOrder_shipping_zip("455520");

        items.add(item);
        orderDetail.setItems(items);

        List<PaymentDetails> payments = new ArrayList<>();
        PaymentDetails payment = new PaymentDetails();
        payment.setOrder_payment_method("Credit card");
        payment.setOrder_billing_addressline1("100 Receiving Ave");
        payment.setOrder_billing_city("Books city");
        payment.setOrder_billing_state("Library state");
        payment.setOrder_billing_zip("455520");
        payment.setOrder_payment_price("120");

        payments.add(payment);
        orderDetail.setPayments(payments);


    }

    @Test
    public void shouldReceiveItemDetailLevelResponseFromAgent() {

        //We can have more data returned and have more assertions
        String orderConfirmation = orderService.createOrder(orderDetail);
        assertNotNull(orderConfirmation);
    }

    

}
