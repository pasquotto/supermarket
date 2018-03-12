package uk.co.pasquotto.supermarket.model;

import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Double.compare(discount.amount, amount) == 0 &&
                Objects.equals(product, discount.product) &&
                Objects.equals(description, discount.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, amount, description);
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
