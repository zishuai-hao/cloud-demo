package com.example.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private Long id;
    private Long productId;

    public static OrderEntity getDefault() {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductId(100L);
        return orderEntity;

    }

}
