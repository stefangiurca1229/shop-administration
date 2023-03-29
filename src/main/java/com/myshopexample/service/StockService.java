package com.myshopexample.service;

import com.myshopexample.model.admin.Administrator;
import com.myshopexample.model.logs.Action;
import com.myshopexample.model.logs.Logs;
import com.myshopexample.model.product.Product;
import com.myshopexample.model.Stock;
import com.myshopexample.model.dto.stock.StockDto;
import com.myshopexample.model.mapper.StockMapper;
import com.myshopexample.repositories.LogRepository;
import com.myshopexample.repositories.StockRepository;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.security.PrincipalProvider;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StockService {
    private final StockMapper stockMapper = Mappers.getMapper(StockMapper.class);
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private PrincipalProvider adminFromSpringSecurityContext;
    @Autowired
    private LogRepository logRepository;
    public GenericResponse<StockDto> addStock(StockDto stockDto, Long productId){
        Administrator stockAdmin = adminFromSpringSecurityContext.getPrincipalProvider();
        Product product = productsService.getProductById(productId);
                LocalDate localDate = LocalDate.now();
                stockDto.setCreateDate(localDate);
                Stock stock = stockMapper.StockDtoToStok(stockDto);
                stock.setProduct(product);
                stockRepository.save(stock);
                stockDto.setId(stock.getId());
        Logs log = new Logs(stockAdmin.getName(),"stock with id " + stock.getId().toString() + " was added", Action.SAVE,LocalDate.now());
        logRepository.save(log);
                return new GenericResponse<>("success",stockDto);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public StockDto findStockDtoByProductId(Long productId){
        Stock stock = stockRepository.findFirstByEnableAndProduct_IdOrderByCreateDateAsc(true,productId);
        return stockMapper.StockToStockDto(stock);
    }

    public Stock findStockByProductId(Long productId){
        return stockRepository.findFirstByEnableAndProduct_IdOrderByCreateDateAsc(true,productId);
    }

    public int updateStockByProduct(Product product, int quantity) {
        Stock stock = stockRepository.findFirstByEnableAndProduct_IdOrderByCreateDateAsc(true, product.getId());
        return stockRepository.updateStock(quantity, stock.getId());
    }

    public StockDto updateStock(StockDto stockDto) {
        Stock stock = stockMapper.StockDtoToStok(stockDto);
        stockRepository.save(stock);
        return stockDto;
    }

//    public Double getPriceByProductId(Long id) {
//        return stockRepository.findPriceByProduct_IdAndEnable(id,true);
//    }
}
