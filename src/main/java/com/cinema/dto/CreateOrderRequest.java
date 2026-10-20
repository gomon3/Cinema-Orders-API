package com.cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateOrderRequest(String cinemaId, String userId, List<ItemRequest> items) {}
