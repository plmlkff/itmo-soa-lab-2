package ru.itmo.ebay.ejb.remote;

import jakarta.ejb.Remote;
import ru.itmo.ebay.ejb.dto.ProductDto;

import java.util.List;

@Remote
public interface EbayFilterServiceRemote {
    List<ProductDto> getProductsByManufacturerId(int manufacturerId);
    
    List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo);
}

