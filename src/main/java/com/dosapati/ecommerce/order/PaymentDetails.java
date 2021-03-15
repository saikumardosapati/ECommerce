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
public class PaymentDetails {

    @javax.persistence.Transient
    private String order_payment_confirmation_number;

    private String order_payment_method;

    private String order_payment_date;

    private String order_billing_addressline1;

    private String order_billing_addressline2;

    private String order_billing_city;

    private String order_billing_state;

    private String order_billing_zip;

    private String order_payment_price;
}
