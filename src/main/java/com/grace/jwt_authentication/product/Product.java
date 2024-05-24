package com.grace.jwt_authentication.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @NotBlank
    private String name;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    public @Null Long getId() {
        return id;
    }

    public void setId(@Null Long id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @DecimalMin(value = "0.01") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "0.01") BigDecimal price) {
        this.price = price;
    }
}
