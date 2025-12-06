package ru.itmo.ebay.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.common.entity.Country;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {
    private Integer id;

    @NotNull(message = "Поле name не может быть null")
    @Size(min = 1, message = "Строка name не может быть пустой")
    private String name;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthday;

    private Country nationality;
}
