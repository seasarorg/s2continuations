package org.seasar.continuations.exception;

/**
 * @author t-wada
 */
public class SContinuableObjectConstraintException extends SContinuationException {
    private static final long serialVersionUID = 1L;

    public SContinuableObjectConstraintException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }
    public SContinuableObjectConstraintException(String messageCode, Object[] args) {
        super(messageCode, args);
    }
    public SContinuableObjectConstraintException(String messageCode, String arg, Throwable cause) {
        super(messageCode, arg, cause);
    }
    public SContinuableObjectConstraintException(String messageCode, String arg) {
        super(messageCode, arg);
    }
    public SContinuableObjectConstraintException(String messageCode) {
        super(messageCode);
    }
}
