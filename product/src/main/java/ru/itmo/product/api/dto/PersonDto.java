package ru.itmo.product.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.itmo.common.entity.Country;
import ru.itmo.product.entity.Person;

import java.time.LocalDate;

public record PersonDto(
        int id,

        @NotNull(message = "Поле name не может быть null")
        @Size(min = 1, message = "Строка name не может быть пустой")
        String name,

        LocalDate birthday,

        Country nationality
) {
    public Person toEntity() {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setBirthday(birthday);
        person.setNationality(nationality);
        return person;
    }

    public static PersonDto fromEntity(Person person) {
        return new PersonDto(
                person.getId(),
                person.getName(),
                person.getBirthday(),
                person.getNationality()
        );
    }
}
