package ru.itmo.ebay.service;

import ru.itmo.ebay.api.dto.ProductDto;

import java.util.List;

public interface EbayFilterService {
    List<ProductDto> getProductsByManufacturerId(int manufacturerId);

    List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo);
}
