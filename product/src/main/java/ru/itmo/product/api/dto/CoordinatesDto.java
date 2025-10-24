package ru.itmo.product.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.itmo.product.entity.Coordinates;

public record CoordinatesDto(
        @NotNull(message = "Поле x не может быть null")
        Long x,

        @Min(value = -892, message = "Значение поля y должно быть больше -893")
        float y
) {
    public Coordinates toEntity() {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(x);
        coordinates.setY(y);
        return coordinates;
    }

    public static CoordinatesDto fromEntity(Coordinates coordinates) {
        return new CoordinatesDto(
                coordinates.getX(),
                coordinates.getY()
        );
    }
}
