package com.programmer.controller;

import com.programmer.dto.OrderRequest;
import com.programmer.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/order") @RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallBackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

    public String fallBackMethod(OrderRequest orderRequest , RuntimeException runtimeException)
    {
        return "Oops ! somthing went wrong please try after some time!";
    }
}
