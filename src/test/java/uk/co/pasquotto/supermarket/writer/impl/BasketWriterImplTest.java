package uk.co.pasquotto.supermarket.writer.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.pasquotto.supermarket.model.Basket;
import uk.co.pasquotto.supermarket.model.Discount;
import uk.co.pasquotto.supermarket.model.Product;
import uk.co.pasquotto.supermarket.service.exception.InvalidBasketException;
import uk.co.pasquotto.supermarket.writer.Output;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasketWriterImplTest {

    @Mock
    private Output output;
    @InjectMocks
    private BasketWriterImpl underTest;

    @Test
    public void writeBasketEmpty() {
        Basket basket = new Basket();

        underTest.write(basket);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output, times(3)).print(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("Subtotal: £0.00\n", allValues.get(0));
        assertEquals("(no offers available)\n", allValues.get(1));
        assertEquals("Total: £0.00\n", allValues.get(2));
    }

    @Test
    public void writeBasketOneProduct() {
        Basket basket = new Basket();
        basket.add(createProduct("A", "Apple", 160D));

        underTest.write(basket);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output, times(3)).print(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("Subtotal: £1.60\n", allValues.get(0));
        assertEquals("(no offers available)\n", allValues.get(1));
        assertEquals("Total: £1.60\n", allValues.get(2));
    }

    @Test
    public void writeBasketOneProductWithPercentDiscount() {
        Basket basket = new Basket();
        Product apple = createProduct("A", "Apple", 160D);
        basket.add(apple);
        basket.addDiscount(new Discount(apple, 16, "10% off"));

        underTest.write(basket);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output, times(3)).print(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("Subtotal: £1.60\n", allValues.get(0));
        assertEquals("Apple 10% off: -16p\n", allValues.get(1));
        assertEquals("Total: £1.44\n", allValues.get(2));
    }

    @Test
    public void writeBasketOneProductWithAbsoluteDiscount() {
        Basket basket = new Basket();
        Product apple = createProduct("A", "Apple", 160D);
        basket.add(apple);
        basket.add(apple);
        basket.add(apple);
        basket.addDiscount(new Discount(apple, 80, "3 for £4"));

        underTest.write(basket);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output, times(3)).print(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("Subtotal: £4.80\n", allValues.get(0));
        assertEquals("Apple 3 for £4: -80p\n", allValues.get(1));
        assertEquals("Total: £4.00\n", allValues.get(2));
    }

    @Test
    public void writeBasketTwoProductWithAbsoluteDiscount() {
        Basket basket = new Basket();
        Product apple = createProduct("A", "Apple", 160D);
        basket.add(apple);
        basket.add(apple);
        basket.add(apple);
        basket.add(createProduct("B", "Banana", 220D));
        basket.addDiscount(new Discount(apple, 80, "3 for £4"));

        underTest.write(basket);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output, times(3)).print(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("Subtotal: £7.00\n", allValues.get(0));
        assertEquals("Apple 3 for £4: -80p\n", allValues.get(1));
        assertEquals("Total: £6.20\n", allValues.get(2));
    }

    @Test
    public void writeBasketTwoProductWithAbsoluteDiscountInBothProducts() {
        Basket basket = new Basket();
        Product apple = createProduct("A", "Apple", 160D);
        basket.add(apple);
        basket.add(apple);
        basket.add(apple);
        Product banana = createProduct("B", "Banana", 220D);
        basket.add(banana);
        basket.add(banana);
        basket.addDiscount(new Discount(apple, 80, "3 for £4"));
        basket.addDiscount(new Discount(banana, 20, "2 for £4.20"));

        underTest.write(basket);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output, times(4)).print(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("Subtotal: £9.20\n", allValues.get(0));
        assertEquals("Apple 3 for £4: -80p\n", allValues.get(1));
        assertEquals("Banana 2 for £4.20: -20p\n", allValues.get(2));
        assertEquals("Total: £8.20\n", allValues.get(3));
    }

    @Test(expected = InvalidBasketException.class)
    public void writeBasketNull() {
        underTest.write(null);
    }

    private Product createProduct(String id, String name, double price) {
        Product product = new Product(id, name, price);
        return product;
    }
}