package uk.co.pasquotto.supermarket.service.impl;

import org.springframework.stereotype.Service;
import uk.co.pasquotto.supermarket.model.Basket;
import uk.co.pasquotto.supermarket.model.Product;
import uk.co.pasquotto.supermarket.repositories.ProductRepository;
import uk.co.pasquotto.supermarket.service.BasketCalculatorService;
import uk.co.pasquotto.supermarket.service.CheckoutService;
import uk.co.pasquotto.supermarket.writer.BasketWriter;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private ProductRepository productRepository;
    private BasketCalculatorService basketCalculatorService;
    private BasketWriter basketWriter;

    public CheckoutServiceImpl(ProductRepository productRepository,
                               BasketCalculatorService basketCalculatorService,
                               BasketWriter basketWriter) {
        this.productRepository = productRepository;
        this.basketCalculatorService = basketCalculatorService;
        this.basketWriter = basketWriter;
    }

    @Override
    public Basket checkoutProducts(String... productsId) {

        Basket basket = new Basket();

        Arrays.stream(productsId)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .forEach((productId, quantity) -> {
                Product product = this.productRepository.getProductById(productId);
                if (product != null) {
                    for (int i = 0; i < quantity; i++) {
                        basket.add(product);
                    }
                }
            });
        basketCalculatorService.calculateDiscounts(basket);
        basketWriter.write(basket);
        return basket;
    }
}
