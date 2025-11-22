package ru.itmo.ebay.ejb.dto;

import ru.itmo.common.entity.Country;

import java.io.Serializable;
import java.time.LocalDate;

public class PersonDto implements Serializable {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private Country nationality;

    public PersonDto() {
    }

    public PersonDto(Integer id, String name, LocalDate birthday, Country nationality) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}

