package org.seasar.continuations.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * @author t-wada
 */
public class SContinuationException extends SRuntimeException {
    private static final long serialVersionUID = 1L;
    
    public SContinuationException(String messageCode) {
        super(messageCode);
    }
    public SContinuationException(String messageCode, Object[] args) {
        super(messageCode, args);
    }
    public SContinuationException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }
    public SContinuationException(String messageCode, String arg) {
        super(messageCode, new Object[]{arg});
    }
    public SContinuationException(String messageCode, String arg, Throwable cause) {
        super(messageCode, new Object[]{arg}, cause);
    }
}
