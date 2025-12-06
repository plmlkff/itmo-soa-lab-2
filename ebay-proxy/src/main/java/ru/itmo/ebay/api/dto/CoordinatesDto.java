package ru.itmo.ebay.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CoordinatesDto {
    @NotNull(message = "Поле x не может быть null")
    private Long x;

    @Min(value = -892, message = "Значение поля y должно быть больше -893")
    private float y;
}
