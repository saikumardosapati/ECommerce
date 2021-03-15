package com.dosapati.ecommerce.order;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail getOrder(String orderId){
        Optional<OrderDetail> orderDetails = orderDetailRepository.findById(orderId);
        if(!orderDetails.isPresent()){
            log.info("Order with order id [{}] not found", orderId);
            throw new ServiceException(String.format("Order [%s] not found", orderId));
        }

        return orderDetails.get();
    }

    @Transactional
    public String createOrder(OrderDetail orderDetail){
        try {
            OrderDetail setDetails = new OrderDetail();
            String orderId = UUID.randomUUID().toString();
            String paymentConf = UUID.randomUUID().toString();

            setDetails.setOrder_id(orderId);
            setDetails.setOrder_created_date(new Date(System.currentTimeMillis()).toString());
            setDetails.setOrder_customer_id(orderDetail.getOrder_customer_id());
            setDetails.setOrder_sub_total(orderDetail.getOrder_sub_total());
            setDetails.setOrder_shipping_charges(orderDetail.getOrder_shipping_charges());
            setDetails.setOrder_tax(orderDetail.getOrder_tax());
            setDetails.setOrder_total(orderDetail.getOrder_total());
            setDetails.setOrder_payment_confirmation_number(paymentConf);

            List<OrderItem> items = new ArrayList<>();
            long currentTime = System.currentTimeMillis();

            for (OrderItem item : orderDetail.getItems()) {
                OrderItem currentItem = new OrderItem();
                currentItem.setOrder_id(orderId);
                currentItem.setOrder_item_name(item.getOrder_item_name());
                currentItem.setOrder_item_qty(item.getOrder_item_qty());
                currentItem.setOrder_type(item.getOrder_type());
                currentItem.setOrder_shipping_addressline1(item.getOrder_shipping_addressline1());
                currentItem.setOrder_shipping_addressline2(item.getOrder_shipping_addressline2());
                currentItem.setOrder_shipping_city(item.getOrder_shipping_city());
                currentItem.setOrder_shipping_state(item.getOrder_shipping_state());
                currentItem.setOrder_shipping_zip(item.getOrder_shipping_zip());
                items.add(currentItem);
            }
            setDetails.setItems(items);

            List<PaymentDetails> paymentDetails = new ArrayList<>();
            for (PaymentDetails paymentDetail : orderDetail.getPayments()) {
                PaymentDetails payment = new PaymentDetails();
                payment.setOrder_payment_confirmation_number(paymentConf);
                payment.setOrder_billing_addressline1(paymentDetail.getOrder_billing_addressline1());
                payment.setOrder_billing_addressline2(paymentDetail.getOrder_billing_addressline2());
                payment.setOrder_billing_city(paymentDetail.getOrder_billing_city());
                payment.setOrder_billing_state(paymentDetail.getOrder_billing_state());
                payment.setOrder_billing_zip(paymentDetail.getOrder_billing_zip());
                payment.setOrder_payment_method(paymentDetail.getOrder_payment_method());
                payment.setOrder_payment_price(paymentDetail.getOrder_payment_price());
                payment.setOrder_payment_date(new Date(currentTime).toString());
                paymentDetails.add(payment);
            }
            setDetails.setPayments(paymentDetails);

            orderDetailRepository.save(setDetails);
            log.info("Successfully created an order for customer [{}] with details: [{}]", orderDetail.getOrder_customer_id(), setDetails);
            return orderId;
        } catch(Exception e) {
            log.error("Error while creating order for customer [{}]", orderDetail.getOrder_customer_id());
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    public boolean cancelOrder(String orderId){
        boolean orderCancelled = false;
        Optional<OrderDetail> orderDetails = orderDetailRepository.findById(orderId);
        if(orderDetails.isPresent()){
            orderDetails.get().setOrder_status(OrderStatus.ORDER_CANCELLED);
            orderDetailRepository.save(orderDetails.get());
            log.info("Successfully cancelled order [{}]", orderId);
            orderCancelled = true;
        } else {
            log.error("Cancel request failed. Order [{}] not found", orderId);
            throw new ServiceException(String.format("Order [%s] not found", orderId));
        }
        return orderCancelled;
    }


    @Transactional
    public boolean updateOrderDetails(UpdateOrder updateOrder){
        boolean orderCancelled = false;
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(updateOrder.orderId);
        if(orderDetail.isPresent()){
            orderDetail.get().setOrder_status(updateOrder.orderStatus);
            orderDetailRepository.save(orderDetail.get());
            log.info("Successfully updated order [{}]", updateOrder.orderId);
            orderCancelled = true;
        } else {
            log.error("Cancel request failed. Order [{}] not found", updateOrder.orderId);
            throw new ServiceException(String.format("Order [%s] not found", updateOrder.orderId));
        }
        return orderCancelled;
    }
}
