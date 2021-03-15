# ECommerce


This is a rest application that provides services to create or update an order or a batch of orders.

To run the application, make sure you have docker installed on your machine.
Navigate to the project folder and run "docker compose up" command.
This will create a database in a container and backend application in another container.
You can verify the endpoints exposed by navigating to http://localhost:8080/swagger-ui.html#/
which displays the endpoints and the services supported by the application.

You may try out the services by creating an order first using the POST request to create order
under "order-controller" and use the resulting order confirmation number to view details about
the order using get order.


Here's a sample JSON for create-order service:
{
    "order_customer_id": "TharunCustId",
    "order_sub_total": "3",
    "order_tax": "2",
    "order_shipping_charges": "1",
    "order_total": "6",
    "items": [
        {
            "order_item_name": "Books",
            "order_item_qty": 10,
            "order_type": "SHIPPING",
            "order_shipping_addressline1": "100 Receiving Ave",
            "order_shipping_addressline2": null,
            "order_shipping_city": "Books City",
            "order_shipping_state": "Library",
            "order_shipping_zip": "1234"
        }
    ],
    "payments": [
		{
		"order_payment_method": "SHIPPING",
		"order_payment_date": "03-13-2021",
		"order_billing_addressline1": "100 Receiving Ave",
		"order_billing_addressline2": null,
		"order_billing_city": "Books City",
		"order_billing_state": "Library",
		"order_billing_zip": "1234",
		"order_payment_price": "6"
	}
	]
}
