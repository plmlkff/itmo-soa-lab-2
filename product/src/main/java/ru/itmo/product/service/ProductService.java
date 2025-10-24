package ru.itmo.product.service;

import org.springframework.data.domain.Page;
import ru.itmo.product.api.dto.ProductDto;
import ru.itmo.product.entity.Product;
import ru.itmo.product.entity.filter.ProductFilter;

public interface ProductService {
    Page<Product> getFilteredProducts(ProductFilter filter);

    Product createProduct(ProductDto productDto);

    Product updateProduct(int productId, ProductDto productDto);

    void deleteProduct(int productId);
}
