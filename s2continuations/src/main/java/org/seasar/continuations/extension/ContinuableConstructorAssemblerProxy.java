package org.seasar.continuations.extension;

import org.seasar.continuations.helper.ContinuableObjectFactory;
import org.seasar.continuations.helper.ContinuableObjectResumeHelper;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ConstructorAssembler;
import org.seasar.framework.container.IllegalConstructorRuntimeException;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.S2Container;

/**
 * @author t-wada
 */
public class ContinuableConstructorAssemblerProxy implements ConstructorAssembler {
    private ComponentDef componentDef;
    private ConstructorAssembler realAssembler;
    
    
    public ContinuableConstructorAssemblerProxy(ComponentDef cd, ConstructorAssembler assembler) {
        this.componentDef = cd;
        this.realAssembler = assembler;
    }
    
    
    public Object assemble() throws IllegalConstructorRuntimeException {
        if(isContinuable()) {
            return getContinuableObjectResumeHelper().resumeOrCreateNew(getComponentDef(), new ContinuableObjectFactory() {
                public Object createNew() {
                    return realAssembler.assemble();
                }
            });
        } else {
            return realAssembler.assemble();
        }
    }


    private boolean isContinuable() {
        MetaDef md = getComponentDef().getMetaDef("continuable");
        if(md == null) {
            return false;
        } else {
            return ((Boolean)md.getValue()).booleanValue();
        }
    }
    
    
    private ContinuableObjectResumeHelper getContinuableObjectResumeHelper() {
        return (ContinuableObjectResumeHelper) getContainer().getComponent(ContinuableObjectResumeHelper.class);
    }
    private S2Container getContainer() {
        return getComponentDef().getContainer();
    }
    private ComponentDef getComponentDef() {
        return this.componentDef;
    }
}
