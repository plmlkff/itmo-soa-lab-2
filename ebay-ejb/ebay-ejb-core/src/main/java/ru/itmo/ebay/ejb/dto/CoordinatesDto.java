package ru.itmo.ebay.ejb.dto;

import java.io.Serializable;

public class CoordinatesDto implements Serializable {
    private Long x;
    private float y;

    public CoordinatesDto() {
    }

    public CoordinatesDto(Long x, float y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

