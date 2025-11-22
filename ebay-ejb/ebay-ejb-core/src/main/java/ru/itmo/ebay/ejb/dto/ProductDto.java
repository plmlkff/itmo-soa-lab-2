package ru.itmo.ebay.ejb.dto;

import ru.itmo.common.entity.UnitOfMeasure;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private Integer id;
    private String name;
    private CoordinatesDto coordinates;
    private Integer price;
    private Float manufactureCost;
    private UnitOfMeasure unitOfMeasure;
    private PersonDto owner;

    public ProductDto() {
    }

    public ProductDto(Integer id, String name, CoordinatesDto coordinates, Integer price, 
                      Float manufactureCost, UnitOfMeasure unitOfMeasure, PersonDto owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
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

    public CoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Float getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(Float manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public PersonDto getOwner() {
        return owner;
    }

    public void setOwner(PersonDto owner) {
        this.owner = owner;
    }
}

