package ru.itmo.ebay.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CoordinatesDto(
        @NotNull(message = "Поле x не может быть null")
        Long x,

        @Min(value = -892, message = "Значение поля y должно быть больше -893")
        float y
) {
}
