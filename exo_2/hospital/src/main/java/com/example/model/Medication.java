package com.example.model;

import java.util.Map;

public class Medication {
    private final String name;
    private final int stockQuantity;

    public Medication(String name, int stockQuantity) {
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public String getName() { return name; }
    public int getStockQuantity() { return stockQuantity; }
}
