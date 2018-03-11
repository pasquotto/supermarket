package uk.co.pasquotto.supermarket.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.pasquotto.supermarket.model.*;
import uk.co.pasquotto.supermarket.service.BasketCalculatorService;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketCalculatorIntegrationTest {
	@Autowired
	private BasketCalculatorService basketCalculatorService;


	@Test
	public void testAddProductNoDiscount() {
		Basket basket = new Basket();
		Product product = new Product("A", "Apple", 160D);
		basket.add(product);

		basketCalculatorService.calculateDiscounts(basket);

		assertEquals(0, basket.getDiscounts().size());
	}

	@Test
	public void testProduct3for130() {
	    Basket basket = new Basket();
	    Product product = new Product("A", "Apple", 50D);
	    basket.add(product);
        basket.add(product);
        basket.add(product);

	    basketCalculatorService.calculateDiscounts(basket);

		assertEquals(1, basket.getDiscounts().size());
		Discount discount = basket.getDiscounts().get(0);
		assertEquals(20D, discount.getAmount(), 0.01D);
		assertEquals("3 for 130", discount.getDescription());
	}
	@Test
	public void testProduct3for130With4Producs() {
		Basket basket = new Basket();
		Product product = new Product("A", "Apple", 50D);
		basket.add(product);
		basket.add(product);
		basket.add(product);
		basket.add(product);

		basketCalculatorService.calculateDiscounts(basket);

		assertEquals(1, basket.getDiscounts().size());
		Discount discount = basket.getDiscounts().get(0);
		assertEquals(20D, discount.getAmount(), 0.01D);
		assertEquals("3 for 130", discount.getDescription());
	}

	@Test
	public void testProduct2for45() {
		Basket basket = new Basket();
		Product product = new Product("B", "Banana", 30D);
		basket.add(product);
		basket.add(product);
		basket.add(product);

		basketCalculatorService.calculateDiscounts(basket);

		assertEquals(1, basket.getDiscounts().size());
		Discount discount = basket.getDiscounts().get(0);
		assertEquals(15D, discount.getAmount(), 0.01D);
		assertEquals("2 for 45", discount.getDescription());
	}


	@Test
	public void testProduct10PercentDiscountThisWeek() {
		Basket basket = new Basket();
		Product product = new Product("Apples", "Apple bag", 100D);
		basket.add(product);
		basket.add(product);
		basket.add(product);

		basketCalculatorService.calculateDiscounts(basket);

		assertEquals(1, basket.getDiscounts().size());
		Discount discount = basket.getDiscounts().get(0);
		assertEquals(30D, discount.getAmount(), 0.01D);
		assertEquals("10% off", discount.getDescription());
	}

	@Test
	public void testProduct10PercentDiscountThisWeekWithOtherProducts() {
		Basket basket = new Basket();
		Product product = new Product("Apples", "Apple bag", 100D);
		basket.add(product);
		basket.add(product);
		basket.add(product);
		Product product2 = new Product("Bananas", "Bananas", 120D);
		basket.add(product2);
        basket.add(product2);

		basketCalculatorService.calculateDiscounts(basket);

		assertEquals(1, basket.getDiscounts().size());
		Discount discount = basket.getDiscounts().get(0);
		assertEquals(30D, discount.getAmount(), 0.01D);
		assertEquals("10% off", discount.getDescription());
	}


    @Test
    public void testProductBuy2SoupAndHaveBreadHalfPrice() {
        Basket basket = new Basket();
        Product product = new Product("Soup", "Soup", 65D);
        basket.add(product);
        basket.add(product);
        Product product2 = new Product("Bread", "Bread", 80D);
        basket.add(product2);

        basketCalculatorService.calculateDiscounts(basket);

        assertEquals(1, basket.getDiscounts().size());
        Discount discount = basket.getDiscounts().get(0);
        assertEquals(40D, discount.getAmount(), 0.01D);
        assertEquals("Bread half price for each 2 soups", discount.getDescription());
    }
}
