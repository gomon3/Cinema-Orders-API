package com.cinema.controller;

import com.cinema.dto.CreateOrderRequest;
import com.cinema.dto.UpdateOrderRequest;
import com.cinema.model.Order;
import com.cinema.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/countries/{country}/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@PathVariable String country, @RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(country, request);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{orderId}").buildAndExpand(order.getOrderId()).toUri();
        return ResponseEntity.created(location).body(order);
    }

    @GetMapping("/{orderId}")
    public Order get(@PathVariable String country, @PathVariable String orderId) {
        return orderService.getOrder(country, orderId);
    }

    @PatchMapping("/{orderId}")
    public Order update(@PathVariable String country, @PathVariable String orderId,
                        @RequestBody UpdateOrderRequest request) {
        return orderService.updateOrder(country, orderId, request);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable String country, @PathVariable String orderId) {
        orderService.deleteOrder(country, orderId);
        return ResponseEntity.noContent().build();
    }
}
