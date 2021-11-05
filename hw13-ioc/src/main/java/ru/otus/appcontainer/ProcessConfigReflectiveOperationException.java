package ru.otus.appcontainer;

public class ProcessConfigReflectiveOperationException extends RuntimeException {
    public ProcessConfigReflectiveOperationException(String message) {
        super(message);
    }

    public ProcessConfigReflectiveOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessConfigReflectiveOperationException(Throwable cause) {
        super(cause);
    }
}
