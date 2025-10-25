package ru.itmo.ebay.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.ebay.api.dto.ProductDto;
import ru.itmo.ebay.service.EbayFilterService;

import java.util.List;

@RestController
@RequestMapping("/api/ebay/filter")
@RequiredArgsConstructor
public class EbayFilterController {
    private final EbayFilterService ebayFilterService;

    @GetMapping("/manufacturer/{manufacturer-id}")
    public List<ProductDto> getProductsByManufacturerId(
            @PathVariable(name = "manufacturer-id")
            int manufacturerId
    ) {
        return ebayFilterService.getProductsByManufacturerId(manufacturerId);
    }

    @GetMapping("/price/{price-from}/{price-to}")
    public List<ProductDto> getProductsByPriceRange(
            @PathVariable(name = "price-from")
            int priceFrom,
            @PathVariable(name = "price-to")
            int priceTo
    ) {
        return ebayFilterService.getProductsByPriceRange(priceFrom, priceTo);
    }
}
