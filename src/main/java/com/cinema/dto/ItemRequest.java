package com.cinema.dto;

import com.cinema.model.ItemType;

public record ItemRequest(ItemType type, String name, Double price) {}
