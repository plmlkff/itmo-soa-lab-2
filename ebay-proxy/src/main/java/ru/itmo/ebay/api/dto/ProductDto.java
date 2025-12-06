package ru.itmo.ebay.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.common.entity.UnitOfMeasure;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto {
    private Integer id;

    @NotNull(message = "Поле name не может быть null")
    @Size(min = 1, message = "Строка name не может быть пустой")
    private String name;

    @NotNull(message = "Поле coordinates не может быть null")
    @Valid
    private CoordinatesDto coordinates;

    @NotNull(message = "Поле price не может быть null")
    @Min(value = 1, message = "Значение поля price должно быть больше 0")
    private Integer price;

    private Float manufactureCost;

    private UnitOfMeasure unitOfMeasure;

    @NotNull(message = "Поле owner не может быть null")
    @Valid
    private PersonDto owner;
}
