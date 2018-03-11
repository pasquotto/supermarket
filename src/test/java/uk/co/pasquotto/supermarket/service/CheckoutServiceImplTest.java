package uk.co.pasquotto.supermarket.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.pasquotto.supermarket.model.Basket;
import uk.co.pasquotto.supermarket.model.Product;
import uk.co.pasquotto.supermarket.repositories.ProductRepository;
import uk.co.pasquotto.supermarket.service.impl.CheckoutServiceImpl;
import uk.co.pasquotto.supermarket.writer.BasketWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceImplTest {

    @Mock
    private BasketWriter basketWriter;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BasketCalculatorService basketCalculatorService;

    @InjectMocks
    private CheckoutServiceImpl checkout;


    @Test
    public void testAddProductsToBasket() {
        String[] products = new String[] {"A", "B", "A", "C", "A", "D"};

        when(productRepository.getProductById("A")).thenReturn(new Product("A", "prodA", 123D));
        when(productRepository.getProductById("B")).thenReturn(new Product("B", "prodB", 24D));
        when(productRepository.getProductById("C")).thenReturn(new Product("C", "prodC", 80D));
        when(productRepository.getProductById("D")).thenReturn(new Product("D", "prodD", 345D));

        Basket basket = checkout.checkoutProducts(products);

        verify(productRepository).getProductById("A");
        verify(productRepository).getProductById("B");
        verify(productRepository).getProductById("C");
        verify(productRepository).getProductById("D");
        verify(basketCalculatorService).calculateDiscounts(basket);
        verify(basketWriter).write(basket);
    }
}
