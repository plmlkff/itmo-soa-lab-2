package ru.itmo.product.service;

import ru.itmo.product.entity.projection.ProductCountByDate;

import java.util.List;

public interface ProductAnalyticsService {
    List<ProductCountByDate> countProductsByCreationDate();

    int getAveragePrice();

    List<Float> getUniqueManufactureCosts();
}
