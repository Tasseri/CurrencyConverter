package se.academy.project1.group3;

/**
 * Created by Administrator on 9/29/2016.
 */
public class CurrencyConverterException extends RuntimeException {
    public CurrencyConverterException() {
    }

    public CurrencyConverterException(String message) {
        super(message);
    }

    public CurrencyConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyConverterException(Throwable cause) {
        super(cause);
    }

    public CurrencyConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
