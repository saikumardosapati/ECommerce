package com.dosapati.ecommerce.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @javax.persistence.Transient
    private String order_id;

    private String order_item_name;

    private int order_item_qty;

    private String order_shipped_date;

    private OrderType order_type;

    private String order_shipping_addressline1;

    private String order_shipping_addressline2;

    private String order_shipping_city;

    private String order_shipping_state;

    private String order_shipping_zip;

}
