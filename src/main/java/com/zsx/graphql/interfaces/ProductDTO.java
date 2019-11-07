package com.zsx.graphql.interfaces;

public class ProductDTO {

    private final String id;
    private final String name;
    private final String description;
    private final Float cost;
    private final Float tax;

    public ProductDTO(String id, String name, String description, Float cost, Float tax) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.tax = tax;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getCost() {
        return cost;
    }

    public Float getTax() {
        return tax;
    }
}