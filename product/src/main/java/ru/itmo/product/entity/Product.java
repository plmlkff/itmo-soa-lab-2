package ru.itmo.product.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(nullable = false)
    @NotNull
    @Size(min = 1)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @Column(name = "creation_date", nullable = false, columnDefinition = "DATE")
    @CreationTimestamp
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(nullable = false)
    @NotNull
    @Min(1)
    private Integer price; //Поле не может быть null, Значение поля должно быть больше 0

    @Column(name = "manufacture_cost")
    private Float manufactureCost; //Поле может быть null

    @Column(name = "unit_of_measure")
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure; //Поле может быть null

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private Person owner; //Поле не может быть null
}