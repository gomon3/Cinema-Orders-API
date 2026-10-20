package com.cinema.dto;

import com.cinema.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Getter;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateOrderRequest {
    private List<ItemRequest> items;
    private OrderStatus status;

    @JsonSetter(nulls = Nulls.FAIL)
    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    @JsonSetter(nulls = Nulls.FAIL)
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
