package org.seasar.continuations.intertype;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.seasar.framework.aop.intertype.AbstractInterType;

import com.uwyn.rife.continuations.ContinuableObject;

/**
 * @author t-wada
 */
public class ContinuableObjectInterType extends AbstractInterType {
    public ContinuableObjectInterType() {
    }
    protected void introduce() throws CannotCompileException, NotFoundException {
        addInterface(ContinuableObject.class);
        addMethod(Object.class, "clone", new Class[]{}, new Class[]{CloneNotSupportedException.class}, "return super.clone();");
    }
}
