package ru.itmo.product.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.product.api.dto.CoordinatesDto;
import ru.itmo.product.api.dto.ProductDto;
import ru.itmo.product.entity.Coordinates;
import ru.itmo.product.entity.Product;
import ru.itmo.common.entity.filter.ProductFilter;
import ru.itmo.product.repository.ProductRepository;
import ru.itmo.product.repository.specification.ProductSpecificationBuilder;
import ru.itmo.product.service.ProductService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Page<Product> getFilteredProducts(ProductFilter productFilter) {
        log.info("Collecting products by filter: {}", productFilter);
        var spec = ProductSpecificationBuilder.buildFromFilter(productFilter);
        var sort = productFilter.getSort();
        var pageRequest = productFilter.getPageRequest();
        pageRequest.withSort(sort);

        return productRepository.findAll(spec, pageRequest);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Product createProduct(ProductDto productDto) {
        var product = productRepository.save(productDto.toEntity());
        entityManager.refresh(product);
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(int productId, ProductDto productDto) {
        if (productId < 0) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product id must be greater than 0");

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with provided id doesn't exist!"));

        updateProductFromDto(product, productDto);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        if (productId < 0) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product id must be greater than 0");

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with provided id doesn't exist!"));

        productRepository.delete(product);
    }

    private void updateProductFromDto(Product product, ProductDto dto) {
        if (dto.name() != null && !dto.name().trim().isEmpty()) {
            product.setName(dto.name().trim());
        }
        if (dto.price() != null) {
            product.setPrice(dto.price());
        }
        if (dto.manufactureCost() != null) {
            product.setManufactureCost(dto.manufactureCost());
        }
        if (dto.unitOfMeasure() != null) {
            product.setUnitOfMeasure(dto.unitOfMeasure());
        }
        if (dto.coordinates() != null) {
            updateCoordinates(product, dto.coordinates());
        }
    }

    private void updateCoordinates(Product product, CoordinatesDto coordinatesDto) {
        // Если coordinates уже есть - обновляем, иначе создаем новые
        Coordinates coordinates = product.getCoordinates();
        if (coordinates == null) {
            coordinates = new Coordinates();
            product.setCoordinates(coordinates);
        }
        coordinates.setX(coordinatesDto.x());
        coordinates.setY(coordinatesDto.y());
    }
}
