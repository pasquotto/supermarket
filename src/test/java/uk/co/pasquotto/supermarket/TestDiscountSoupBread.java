package uk.co.pasquotto.supermarket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDiscountSoupBread {

    @Test
    public void testDiscount2soups1bread() {
        int soupQuantity = 2;
        int breadQuantity = 1;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(40D, discount, 0.001D);
    }

    @Test
    public void testDiscount2soups2bread() {
        int soupQuantity = 2;
        int breadQuantity = 2;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(40D, discount, 0.001D);
    }
    @Test
    public void testDiscount2soups3bread() {
        int soupQuantity = 2;
        int breadQuantity = 3;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(40D, discount, 0.001D);
    }
    @Test
    public void testDiscount3soups1bread() {
        int soupQuantity = 3;
        int breadQuantity = 1;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(40D, discount, 0.001D);
    }
    @Test
    public void testDiscount3soups2bread() {
        int soupQuantity = 2;
        int breadQuantity = 2;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(40D, discount, 0.001D);
    }

    @Test
    public void testDiscount4soups1bread() {
        int soupQuantity = 4;
        int breadQuantity = 1;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(40D, discount, 0.001D);
    }
    @Test
    public void testDiscount4soups2bread() {
        int soupQuantity = 4;
        int breadQuantity = 2;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(80D, discount, 0.001D);
    }
    @Test
    public void testDiscount5soups2bread() {
        int soupQuantity = 5;
        int breadQuantity = 2;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(80D, discount, 0.001D);
    }
    @Test
    public void testDiscount6soups2bread() {
        int soupQuantity = 6;
        int breadQuantity = 2;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(80D, discount, 0.001D);
    }
    @Test
    public void testDiscount6soups3bread() {
        int soupQuantity = 6;
        int breadQuantity = 3;
        double discount = this.calculate(soupQuantity, breadQuantity);
        assertEquals(120D, discount, 0.001D);
    }

    /**
     * soupQuantity >= 2
     * breadQuantity >= 1
     */
    private double calculate(int soupQuantity, int breadQuantity) {
        int soupByQuantity = (int)(soupQuantity / 2);
        double discount = 0D;
        for (int i = 0; i < breadQuantity; i++) {
            discount += 40;
            soupByQuantity--;
            if(soupByQuantity == 0) break;
        }

        return discount;
    }

}
