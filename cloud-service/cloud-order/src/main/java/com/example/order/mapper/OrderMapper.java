package com.example.order.mapper;

import com.example.order.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    @Insert(value = "insert into t_order(product_id) value(#{order.productId})")
    int insert(@Param("order") OrderEntity order);
}
