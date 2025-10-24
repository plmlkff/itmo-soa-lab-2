package ru.itmo.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.product.entity.projection.ProductCountByDate;
import ru.itmo.product.repository.ProductRepository;
import ru.itmo.product.service.ProductAnalyticsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAnalyticsServiceImpl implements ProductAnalyticsService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductCountByDate> countProductsByCreationDate(){
        return productRepository.countProductsByCreationDate();
    }

    @Override
    public int getAveragePrice() {
        return productRepository.averagePriceByProducts();
    }

    @Override
    public List<Float> getUniqueManufactureCosts() {
        return productRepository.uniqueManufactureCosts();
    }
}
