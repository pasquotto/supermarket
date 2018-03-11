package uk.co.pasquotto.supermarket.writer.impl;

import org.springframework.stereotype.Component;
import uk.co.pasquotto.supermarket.model.Basket;
import uk.co.pasquotto.supermarket.service.exception.InvalidBasketException;
import uk.co.pasquotto.supermarket.writer.Output;
import uk.co.pasquotto.supermarket.writer.BasketWriter;

import java.text.NumberFormat;
import java.util.Locale;

@Component
public class BasketWriterImpl implements BasketWriter {

    private Output output;

    public BasketWriterImpl(Output output) {
        this.output = output;
    }

    @Override
    public void write(Basket basket) {
        validate(basket);
        NumberFormat currencyFormatter = createNumberFormatForCurrency();

        output.print("Subtotal: " + currencyFormatter.format(toPounds(basket.getSubTotal())) + "\n");

        printDiscounts(basket, currencyFormatter);

        output.print("Total: " + currencyFormatter.format(toPounds(basket.getTotal())) + "\n");

    }

    private void printDiscounts(Basket basket, NumberFormat currencyFormatter) {
        if (!basket.getDiscounts().isEmpty()) {
            basket.getDiscounts().forEach(discount -> {
                String productName = discount.getProduct().getName();
                String description = discount.getDescription();
                String amount = discount.getAmount() < 100 ? (int)discount.getAmount()*-1 +"p" : currencyFormatter.format(toPounds(discount.getAmount())*-1);
                output.print(productName + " " + description + ": " + amount + "\n");
            });
        } else {
            output.print("(no offers available)\n");
        }
    }

    private double toPounds(double value) {
        double pounds = 0D;
        if(value != 0) {
            pounds = value / 100;
        }
        return pounds;
    }


    private NumberFormat createNumberFormatForCurrency() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);
        currencyFormatter.setGroupingUsed(false);
        return currencyFormatter;
    }

    private void validate(Basket basket) {
        if (basket == null) throw new InvalidBasketException("Basket cannot be null");

    }
}
