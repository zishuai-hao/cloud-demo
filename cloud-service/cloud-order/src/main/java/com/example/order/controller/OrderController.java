package com.example.order.controller;

import com.example.common.util.Result;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping("/submit")
    public Result submitOrder() {
        return Result.of(orderService.submitOrder());
    }

}
