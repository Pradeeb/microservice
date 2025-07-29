package com.microservice.order.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.order.dto.OrederReponseDTO;
import com.microservice.order.dto.ProductDTO;
import com.microservice.order.entity.Order;
import com.microservice.order.repository.IOrderRepo;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired IOrderRepo orderRepo;
	@Autowired WebClient.Builder webClientBuilder;
	 
	//Place Order
	
	@PostMapping("/placeorder")
	public Mono<ResponseEntity<OrederReponseDTO>> placeOrder(@RequestBody Order order){
		return webClientBuilder.build().get().uri("http://localhost:8082/products/"+order.getProductId()).retrieve()
				                .bodyToMono(ProductDTO.class).map(productDTO ->{
				                	OrederReponseDTO responseDTO=new OrederReponseDTO();
				                	responseDTO.setProductId(order.getProductId());
				                	responseDTO.setQuantity(order.getQuantity());
				                	
				                	// set product details
				                	responseDTO.setProductName(productDTO.getName());
				                	responseDTO.setProductPrice(productDTO.getPrice());
				                	responseDTO.setTotalPrice(productDTO.getPrice()*order.getQuantity());
				                	
				                	orderRepo.save(order);
				                	responseDTO.setOrderId(order.getId());
				                	return ResponseEntity.ok(responseDTO);
				                });
		
	}
	
	@GetMapping
	public List<Order> getAllOrders(){
		return orderRepo.findAll();
	}

}
