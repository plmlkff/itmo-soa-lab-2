package ru.itmo.ebay.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.ebay.api.dto.ProductDto;
import ru.itmo.ebay.service.EbayFilterService;

import java.util.List;

@Service
@Primary
@Slf4j
public class EbayFilterServiceImpl implements EbayFilterService {
    private final EbayFilterService soapClient;

    public EbayFilterServiceImpl(@Qualifier("ebaySoapClient") EbayFilterService soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public List<ProductDto> getProductsByManufacturerId(int manufacturerId) {
        if (manufacturerId < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Manufacturer id should be above 0");
        }

        log.info("Proxying getProductsByManufacturerId to SOAP service: {}", manufacturerId);
        try {
            List<ProductDto> products = soapClient.getProductsByManufacturerId(manufacturerId);
            if (products == null || products.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer hasn't got products");
            }
            return products;
        } catch (Exception e) {
            log.error("Error calling SOAP service", e);
            if (e.getMessage() != null && e instanceof ResponseStatusException) {
                 throw e;
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo) {
        if (priceFrom < 1 || priceTo < priceFrom){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided values are not valid!");
        }

        log.info("Proxying getProductsByPriceRange to SOAP service: [{}, {}]", priceFrom, priceTo);
        try {
             return soapClient.getProductsByPriceRange(priceFrom, priceTo);
        } catch (Exception e) {
            log.error("Error calling SOAP service", e);
            throw new RuntimeException(e);
        }
    }
}
