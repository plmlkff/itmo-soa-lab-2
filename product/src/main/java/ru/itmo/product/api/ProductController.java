package ru.itmo.product.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.product.api.dto.ProductDto;
import ru.itmo.common.entity.filter.ProductFilter;
import ru.itmo.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getProducts(
            ProductFilter productFilter
    ){
        return productService.getFilteredProducts(productFilter).stream()
                .map(ProductDto::fromEntity)
                .toList();
    }

    @PostMapping("/")
    public ProductDto createProduct(
            @Valid
            @RequestBody
            ProductDto productDto
    ){
        return ProductDto.fromEntity(productService.createProduct(productDto));
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(
            @PathVariable(name = "id")
            int productId,
            @Valid
            @RequestBody
            ProductDto productDto
    ){
        return ProductDto.fromEntity(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable(name = "id")
            int productId
    ){
        productService.deleteProduct(productId);
    }
}
