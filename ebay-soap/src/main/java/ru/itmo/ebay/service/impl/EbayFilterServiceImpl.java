package ru.itmo.ebay.service.impl;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.ebay.api.dto.ProductDto;
import ru.itmo.ebay.service.EbayFilterService;
import ru.itmo.ebay.service.facade.ProductsApiFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@WebService(endpointInterface = "ru.itmo.ebay.service.EbayFilterService", serviceName = "EbayService")
public class EbayFilterServiceImpl implements EbayFilterService {
    private final ProductsApiFacade productsApiFacade;

    @Override
    public List<ProductDto> getProductsByManufacturerId(int manufacturerId) {
        if (manufacturerId < 0){
            throw new RuntimeException("Manufacturer id should be above 0");
        }

        log.info("Getting products by provided manufacturer id: {}", manufacturerId);
        var products = productsApiFacade.getProductsByManufacturerId(manufacturerId);
        if (products.isEmpty()) {
             throw new RuntimeException("Manufacturer hasn't got products");
        }
        log.info("Received products: {}", products);

        return products;
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo) {
        if (priceFrom < 1 || priceTo < priceFrom){
            throw new RuntimeException("Provided values are not valid!");
        }

        log.info("Getting products with price in range [{}, {}]", priceFrom, priceTo);
        var products = productsApiFacade.getProductsByPriceRange(priceFrom, priceTo);
        log.info("Received products: {}", products);

        return products;
    }
}
