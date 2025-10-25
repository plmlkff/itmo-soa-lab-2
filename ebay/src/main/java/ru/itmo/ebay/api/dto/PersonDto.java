package ru.itmo.ebay.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.itmo.common.entity.Country;

import java.time.LocalDate;

public record PersonDto(
        Integer id,

        @NotNull(message = "Поле name не может быть null")
        @Size(min = 1, message = "Строка name не может быть пустой")
        String name,

        LocalDate birthday,

        Country nationality
) {
}
