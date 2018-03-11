package uk.co.pasquotto.supermarket.service.exception;

public class InvalidBasketException extends RuntimeException {
    public InvalidBasketException(String message) {
        super(message);
    }
}
