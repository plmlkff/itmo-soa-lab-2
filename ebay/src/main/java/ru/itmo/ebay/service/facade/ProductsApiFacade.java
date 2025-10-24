package ru.itmo.ebay.service.facade;

import ru.itmo.ebay.api.dto.ProductDto;

import java.util.List;

public interface ProductsApiFacade {
    List<ProductDto> getProductsByManufacturerId(int manufacturerId);

    List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo);
}
