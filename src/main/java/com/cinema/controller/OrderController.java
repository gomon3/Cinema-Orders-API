package com.cinema.controller;

import com.cinema.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: Implementar los endpoints REST.
 *
 * POST   /v1/countries/{country}/orders          - Crear una orden (201)
 * GET    /v1/countries/{country}/orders/{orderId} - Obtener una orden (200)
 * PATCH  /v1/countries/{country}/orders/{orderId} - Actualizar parcialmente (200)
 * DELETE /v1/countries/{country}/orders/{orderId} - Eliminar orden (204)
 *
 * Considerar:
 * - Códigos HTTP apropiados (201, 200, 204, 404, 400/409)
 * - Manejo de errores cuando la orden no existe
 * - Validar reglas de negocio desde el servicio
 */
@RestController
@RequestMapping("/v1/countries/{country}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // TODO: Implementar endpoints
}
