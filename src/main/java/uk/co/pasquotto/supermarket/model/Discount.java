package uk.co.pasquotto.supermarket.model;

import java.io.Serializable;

public class Discount implements Serializable {

    private final Product product;
    private final double amount;
    private final String description;

    public Discount(Product product, double amount, String description) {
        this.product = product;
        this.amount = amount;
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "product=" + product +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
