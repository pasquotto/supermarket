package uk.co.pasquotto.supermarket.model;

import java.io.Serializable;
import java.util.*;

public class Basket implements Serializable{

    private final Map<String, Item> itemsByProductId;
    private final List<Discount> discounts;

    public Basket() {
        itemsByProductId = new HashMap<>();
        discounts = new ArrayList<>();
    }

    public void add(Product product) {
        if (itemsByProductId.containsKey(product.getId())) {
            itemsByProductId.get(product.getId()).incrementQuantity();
        } else {
            Item item = new Item(product, 1);
            itemsByProductId.put(item.getProduct().getId(), item);
        }
    }

    public Product getProduct(String productId) {
        return itemsByProductId.getOrDefault(productId, new Item()).getProduct();
    }
    public int getQuantityOfProduct(String productId) {
        return itemsByProductId.getOrDefault(productId, new Item()).getQuantity();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount disc) {
        this.discounts.add(disc);
    }

    public Double getSubTotal() {
        return itemsByProductId.values().stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();
    }

    public Double getTotal() {
        return getSubTotal() - getDiscountAmount();
    }

    private Double getDiscountAmount() {
        return discounts.stream().mapToDouble(Discount::getAmount).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(itemsByProductId, basket.itemsByProductId) &&
                Objects.equals(discounts, basket.discounts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemsByProductId, discounts);
    }
}
