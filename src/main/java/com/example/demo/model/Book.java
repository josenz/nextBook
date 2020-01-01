package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Book {

    private final UUID id;

    @NotBlank
    private final String title;

    @NotBlank
    private final Integer isbn;

    @NotBlank
    private final Double price;

    public Book(@JsonProperty("id") UUID id,
                @JsonProperty("title") String title,
                @JsonProperty("isbn") Integer isbn,
                @JsonProperty("price") Double price) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public Double getPrice() {
        return price;
    }
}
