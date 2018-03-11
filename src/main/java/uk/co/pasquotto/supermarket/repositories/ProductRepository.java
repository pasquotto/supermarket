package uk.co.pasquotto.supermarket.repositories;

import uk.co.pasquotto.supermarket.model.Product;

public interface ProductRepository {
    public Product getProductById(String id);
}
