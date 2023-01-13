package com.developer.controller;

import com.developer.dto.InventoryResponse;
import com.developer.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/inventory") @RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService ;

    //localhost:8083/api/inventory/iphone_13_red
    @GetMapping @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode)
    {
        return inventoryService.isInStock(skuCode);
    }
}
