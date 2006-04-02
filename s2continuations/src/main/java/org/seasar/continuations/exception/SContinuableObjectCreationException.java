package org.seasar.continuations.exception;

/**
 * @author t-wada
 */
public class SContinuableObjectCreationException extends SContinuationException {
    private static final long serialVersionUID = 1L;

    public SContinuableObjectCreationException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }
    public SContinuableObjectCreationException(String messageCode, Object[] args) {
        super(messageCode, args);
    }
    public SContinuableObjectCreationException(String messageCode, String arg, Throwable cause) {
        super(messageCode, arg, cause);
    }
    public SContinuableObjectCreationException(String messageCode, String arg) {
        super(messageCode, arg);
    }
    public SContinuableObjectCreationException(String messageCode) {
        super(messageCode);
    }
}
