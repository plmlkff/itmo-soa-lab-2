package ru.itmo.ebay.ejb.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class EbayServiceException extends RuntimeException {
    private final int statusCode;

    public EbayServiceException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public EbayServiceException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

