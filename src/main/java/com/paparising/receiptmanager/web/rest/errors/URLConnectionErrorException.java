package com.paparising.receiptmanager.web.rest.errors;

public class URLConnectionErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;

    public URLConnectionErrorException(String message, String... params) {
        super(message);
        this.message = message;
        this.params = params;
    }

    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(message, params);
    }

}
