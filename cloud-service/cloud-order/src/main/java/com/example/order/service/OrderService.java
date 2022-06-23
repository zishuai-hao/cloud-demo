package com.example.order.service;

import com.example.api.feign.RepositoryClient;
import com.example.common.exception.BaseException;
import com.example.common.util.Result;
import com.example.order.entity.OrderEntity;
import com.example.order.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final RepositoryClient repositoryClient;

    @GlobalTransactional
    public boolean submitOrder() {
        final OrderEntity order = OrderEntity.getDefault();
        final int insert = orderMapper.insert(order);
        final Result result = repositoryClient.releaseInventory(order.getProductId());
        if (insert < 1) {
            return false;
        }
        if (!result.isOK()) {
            throw new BaseException(result.getCode());
        }
        return true;
    }
}
