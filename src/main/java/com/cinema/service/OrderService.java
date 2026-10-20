package com.cinema.service;

import com.cinema.dto.CreateOrderRequest;
import com.cinema.dto.ItemRequest;
import com.cinema.dto.UpdateOrderRequest;
import com.cinema.model.Item;
import com.cinema.model.Order;
import com.cinema.model.OrderStatus;
import com.cinema.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Order createOrder(String country, CreateOrderRequest request) {
        require(request != null, "Request body is required");
        require(request.cinemaId() != null && !request.cinemaId().isBlank(), "cinema_id is required");
        require(request.userId() != null && !request.userId().isBlank(), "user_id is required");
        List<Item> items = validateItems(request.items());
        return repository.save(new Order(UUID.randomUUID().toString(), country,
                request.cinemaId(), request.userId(), items, total(items),
                OrderStatus.PENDING, LocalDateTime.now()));
    }

    public Order getOrder(String country, String orderId) {
        return requireOrder(country, repository.findById(orderId).orElse(null));
    }

    public Order updateOrder(String country, String orderId, UpdateOrderRequest request) {
        require(request != null, "Request body is required");
        List<Item> items = request.getItems() == null ? null : validateItems(request.getItems());
        return repository.compute(orderId, current -> {
            Order order = requireOrder(country, current);
            if (items != null) {
                order.setItems(items);
                order.setTotal(total(items));
            }
            if (request.getStatus() != null) {
                order.setStatus(request.getStatus());
            }
            return order;
        });
    }

    public void deleteOrder(String country, String orderId) {
        repository.compute(orderId, current -> {
            Order order = requireOrder(country, current);
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Only PENDING orders can be deleted");
            }
            return null;
        });
    }

    private Order requireOrder(String country, Order order) {
        if (order == null || !country.equals(order.getCountry())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return order;
    }

    private List<Item> validateItems(List<ItemRequest> requests) {
        require(requests != null, "items is required");
        return requests.stream().map(item -> {
            require(item != null, "items cannot contain null");
            require(item.type() != null, "Item type is required");
            require(item.name() != null && !item.name().isBlank(), "Item name is required");
            require(item.price() != null && Double.isFinite(item.price()) && item.price() >= 0,
                    "Item price must be finite and non-negative");
            return new Item(item.type(), item.name(), item.price());
        }).toList();
    }

    private double total(List<Item> items) {
        double total = items.stream().mapToDouble(Item::getPrice).sum();
        require(Double.isFinite(total), "Order total must be finite");
        return total;
    }

    private void require(boolean valid, String message) {
        if (!valid) {
            throw new IllegalArgumentException(message);
        }
    }
}
