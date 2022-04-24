package com.vik.coffeemachine.entity;

public enum CoffeeType {
    LATTE("latte", 30, 150),
    CAPPUCCINO("cappuccino", 40, 100),
    ESPRESSO("espresso", 50, 0);

    private String type;
    private int coffee;
    private int milk;

    CoffeeType(String type, int coffee, int milk) {
        this.type = type;
        this.coffee = coffee;
        this.milk = milk;
    }

    public String getType() {
        return type;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getMilk() {
        return milk;
    }

}
