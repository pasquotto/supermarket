package uk.co.pasquotto.supermarket.model;

import java.io.Serializable;

public class Discount implements Serializable {

    private Product product;
    private double amount;
    private String description;

    public Discount(Product product, double amount, String description) {
        this.product = product;
        this.amount = amount;
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
