package uk.co.pasquotto.supermarket.model;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private Product product;
    private Integer quantity = 0;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Item() {
        this.quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(product, item.product) &&
                Objects.equals(quantity, item.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
