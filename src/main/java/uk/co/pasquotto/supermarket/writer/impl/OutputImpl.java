package uk.co.pasquotto.supermarket.writer.impl;

import org.springframework.stereotype.Component;
import uk.co.pasquotto.supermarket.writer.Output;

@Component
public class OutputImpl implements Output {
    @Override
    public void print(String s) {
        System.out.print(s);
    }
}
