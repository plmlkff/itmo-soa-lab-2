package ru.itmo.product.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.itmo.product.entity.Product;
import ru.itmo.common.entity.UnitOfMeasure;

public record ProductDto(
        Integer id,

        @NotNull(message = "Поле name не может быть null")
        @Size(min = 1, message = "Строка name не может быть пустой")
        String name,

        @NotNull(message = "Поле coordinates не может быть null")
        @Valid
        CoordinatesDto coordinates,

        @NotNull(message = "Поле price не может быть null")
        @Min(value = 1, message = "Значение поля price должно быть больше 0")
        Integer price,

        Float manufactureCost,

        UnitOfMeasure unitOfMeasure,

        @NotNull(message = "Поле owner не может быть null")
        @Valid
        PersonDto owner
) {
    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setCoordinates(this.coordinates.toEntity());
        product.setPrice(this.price);
        product.setManufactureCost(this.manufactureCost);
        product.setUnitOfMeasure(this.unitOfMeasure);
        product.setOwner(this.owner.toEntity());
        return product;
    }

    public static ProductDto fromEntity(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                CoordinatesDto.fromEntity(product.getCoordinates()),
                product.getPrice(),
                product.getManufactureCost(),
                product.getUnitOfMeasure(),
                PersonDto.fromEntity(product.getOwner())
        );
    }
}
