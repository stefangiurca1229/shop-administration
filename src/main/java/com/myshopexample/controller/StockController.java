package com.myshopexample.controller;

import com.myshopexample.annotation.CheckProductRole;
import com.myshopexample.annotation.CheckStockRole;
import com.myshopexample.model.dto.stock.StockDto;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/get-stock")
    public StockDto getStock(@RequestParam Long productId){
        return stockService.findStockDtoByProductId(productId);
    }

    @PostMapping("/add-new-stock")
    @CheckProductRole
    public GenericResponse<StockDto> addNewStock(
            @RequestBody StockDto stockDto,
            @RequestParam Long productId
            ){
           return stockService.addStock(stockDto,productId);
       }
    @PutMapping("/update-stock")
    @CheckStockRole
    public StockDto updateStock(
            @RequestBody StockDto stockDto
    ){
        return stockService.updateStock(stockDto);
    }
}
