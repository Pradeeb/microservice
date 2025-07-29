package com.microservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrederReponseDTO {

	private long orderId;
	private long productId;
	private int quantity;
	private double totalPrice;
	
	private String productName;
	private double productPrice;
}
