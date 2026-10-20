package com.cinema.repository;

import com.cinema.model.Item;
import com.cinema.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.UnaryOperator;

@Repository
public class OrderRepository {
    private final ConcurrentHashMap<String, Order> orders = new ConcurrentHashMap<>();

    public Order save(Order order) {
        Order stored = copy(order);
        orders.put(stored.getOrderId(), stored);
        return copy(stored);
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(copy(orders.get(orderId)));
    }

    /** Runs the mutation atomically for this id; returning null deletes the order. */
    public Order compute(String orderId, UnaryOperator<Order> mutation) {
        return copy(orders.compute(orderId, (id, current) -> copy(mutation.apply(copy(current)))));
    }

    private static Order copy(Order order) {
        if (order == null) {
            return null;
        }
        var items = new ArrayList<Item>();
        for (Item item : order.getItems()) {
            items.add(new Item(item.getType(), item.getName(), item.getPrice()));
        }
        return new Order(order.getOrderId(), order.getCountry(), order.getCinemaId(),
                order.getUserId(), items, order.getTotal(), order.getStatus(), order.getCreatedAt());
    }
}
