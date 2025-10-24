package ru.itmo.product.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.product.api.dto.ProductDto;
import ru.itmo.product.entity.filter.ProductFilter;
import ru.itmo.product.service.ProductService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDto> getProducts(
            ProductFilter productFilter
    ){
        return productService.getFilteredProducts(productFilter).map(ProductDto::fromEntity);
    }

    @PostMapping("/products")
    public ProductDto createProduct(
            @Valid
            @RequestBody
            ProductDto productDto
    ){
        return ProductDto.fromEntity(productService.createProduct(productDto));
    }

    @PutMapping("/products/{id}")
    public ProductDto createProduct(
            @PathVariable(name = "id")
            int productId,
            @Valid
            @RequestBody
            ProductDto productDto
    ){
        return ProductDto.fromEntity(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/products/{id}")
    public void createProduct(
            @PathVariable(name = "id")
            int productId
    ){
        productService.deleteProduct(productId);
    }
}
