package uk.co.pasquotto.supermarket.service;

import uk.co.pasquotto.supermarket.model.Basket;

public interface CheckoutService {
    Basket checkoutProducts(String... products);
}
