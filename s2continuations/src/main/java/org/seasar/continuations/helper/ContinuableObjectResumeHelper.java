package org.seasar.continuations.helper;

import org.seasar.framework.container.ComponentDef;

/**
 * @author t-wada
 */
public interface ContinuableObjectResumeHelper {
    Object resumeOrCreateNew(Object componentKey, ContinuableObjectFactory newObjectFactory);
    Object resumeOrCreateNew(ComponentDef componentDef, ContinuableObjectFactory newObjectFactory);
}
