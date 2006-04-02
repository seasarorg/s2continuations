package org.seasar.continuations.exception;

/**
 * @author t-wada
 */
public class SContinuableClassLoadingException extends SContinuationException {
    private static final long serialVersionUID = 1L;

    public SContinuableClassLoadingException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }
    public SContinuableClassLoadingException(String messageCode, Object[] args) {
        super(messageCode, args);
    }
    public SContinuableClassLoadingException(String messageCode, String arg, Throwable cause) {
        super(messageCode, arg, cause);
    }
    public SContinuableClassLoadingException(String messageCode, String arg) {
        super(messageCode, arg);
    }
    public SContinuableClassLoadingException(String messageCode) {
        super(messageCode);
    }
}
