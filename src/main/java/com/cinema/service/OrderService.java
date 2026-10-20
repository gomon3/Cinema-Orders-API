package com.cinema.service;

import com.cinema.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TODO: Implementar la lógica de negocio.
 *
 * Reglas:
 * - El "total" se calcula automáticamente (suma de precios de items)
 * - Solo se puede eliminar una orden si su status es PENDING
 * - El PATCH solo permite modificar "items" y "status"
 * - Al crear, asignar UUID, status PENDING y timestamp
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    // TODO: Implementar createOrder, getOrder, updateOrder, deleteOrder
}
