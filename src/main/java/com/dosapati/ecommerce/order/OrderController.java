package com.dosapati.ecommerce.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/ecommerce/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-order/{orderId}")
    public OrderDetail getOrder(@PathVariable String orderId) {
        log.info("getting order details for order id {}", orderId);
        return orderService.getOrder(orderId);
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestBody OrderDetail orderDetail) {
        log.info("Received a request to place an order with details: {}", orderDetail);
        return orderService.createOrder(orderDetail);
    }


    @PatchMapping("/cancel-order/{orderId}")
    public void createOrder(@PathVariable String orderId) {
        log.info("Received a request to cancel order order [{}]", orderId);
        orderService.cancelOrder(orderId);
    }


    @PostMapping("/create-batch-orders")
    public void createBatchOrders(@RequestBody List<OrderDetail> ordersDetails) {
        log.info("Received a request to place a batch of orders with details: {}", ordersDetails);

        for (OrderDetail orderDetails : ordersDetails) {
            try {
                //If there is an error in creating one order, we have to continue with the rest of the orders
                //We can add all the futures for each order to a list of futures, and return order confirmations or failed orders
                CompletableFuture<String> future =
                        CompletableFuture.supplyAsync(() ->
                                orderService.createOrder(orderDetails));
            } catch (Exception e) {
                log.error("Failed to create an order with details: [{}]", orderDetails);
                log.error(e.getMessage(), e);
            }
        }
    }


    @PostMapping("/update-orders")
    public void updateBatchOrders(@RequestBody List<UpdateOrder> updateOrders) {
        log.info("Received a request to update a batch of orders with details: {}", updateOrders);
        for (UpdateOrder updateOrderDetails : updateOrders){
            try {
                //If there is an error in updating one order, we have to continue with the rest of the orders
                //We can add all the futures for each order to a list of futures, and return confirmations or failed orders
                CompletableFuture<Boolean> future =
                        CompletableFuture.supplyAsync(() ->
                                orderService.updateOrderDetails(updateOrderDetails));
            } catch (Exception e) {
                log.error("Failed to update an order with details: [{}]", updateOrderDetails);
                log.error(e.getMessage(), e);
            }
        }
    }


}
