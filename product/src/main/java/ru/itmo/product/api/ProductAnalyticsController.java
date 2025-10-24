package ru.itmo.product.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.product.api.dto.AveragePriceDto;
import ru.itmo.product.api.dto.ProductCountByDateDto;
import ru.itmo.product.service.ProductAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/products/analytics")
@RequiredArgsConstructor
public class ProductAnalyticsController {
    private final ProductAnalyticsService productAnalyticsService;

    @GetMapping("/group-by-creation-date")
    public List<ProductCountByDateDto> countProductsByCreationDate(){
        return productAnalyticsService.countProductsByCreationDate().stream()
                .map(ProductCountByDateDto::fromEntity)
                .toList();
    }

    @GetMapping("/average-price")
    public AveragePriceDto getAveragePrice(){
        return new AveragePriceDto(productAnalyticsService.getAveragePrice());
    }

    @GetMapping("/unique-manufacture-cost")
    public List<Float> getUniqueManufactureCosts(){
        return productAnalyticsService.getUniqueManufactureCosts();
    }
}
