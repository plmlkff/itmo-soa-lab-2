package ru.itmo.ebay.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.ebay.api.dto.ProductDto;
import ru.itmo.ebay.service.EbayFilterService;
import ru.itmo.ebay.service.facade.ProductsApiFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EbayFilterServiceImpl implements EbayFilterService {
    private final ProductsApiFacade productsApiFacade;

    @Override
    public List<ProductDto> getProductsByManufacturerId(int manufacturerId) {
        if (manufacturerId < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Manufacturer id should be above 0");
        }

        log.info("Getting products by provided manufacturer id: {}", manufacturerId);
        var products = productsApiFacade.getProductsByManufacturerId(manufacturerId);
        if (products.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer hasn't got products");
        log.info("Received products: {}", products);

        return products;
    }
}
