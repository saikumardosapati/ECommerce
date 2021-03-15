package com.dosapati.ecommerce.order;

import lombok.Data;

@Data
public class UpdateOrder {

    public String orderId;
    public OrderStatus orderStatus;
}
