package uk.co.pasquotto.supermarket.reader.impl;

public class ProductFileNotFoundException extends RuntimeException {
    public ProductFileNotFoundException(String message) {
        super(message);
    }
    public ProductFileNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
