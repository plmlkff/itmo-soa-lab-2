package ru.itmo.ebay.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import ru.itmo.ebay.api.dto.ProductDto;

import java.util.List;

@WebService(targetNamespace = "service.ebay.itmo.ru")
public interface EbayFilterService {
    @WebMethod
    List<ProductDto> getProductsByManufacturerId(@WebParam(name = "manufacturerId") int manufacturerId);

    @WebMethod
    List<ProductDto> getProductsByPriceRange(@WebParam(name = "priceFrom") int priceFrom, @WebParam(name = "priceTo") int priceTo);
}
