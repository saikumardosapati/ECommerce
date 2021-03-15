package com.dosapati.ecommerce.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OrderDetail {

    @Id
    private String order_id;

    private String order_customer_id;

    private OrderStatus order_status;

    private long order_sub_total;

    private long order_tax;

    private long order_shipping_charges;

    private long order_total;

    private String order_payment_confirmation_number;

    private String order_created_date;

    private String order_modified_date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "OrderItem", joinColumns = @JoinColumn(name = "order_id"))
    @OrderColumn
    private List<OrderItem> items;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PaymentDetails", joinColumns = @JoinColumn(name = "order_payment_confirmation_number"))
    @OrderColumn
    private List<PaymentDetails> payments;

}
