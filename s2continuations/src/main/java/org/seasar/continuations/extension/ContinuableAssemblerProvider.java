package org.seasar.continuations.extension;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ConstructorAssembler;
import org.seasar.framework.container.assembler.AssemblerFactory;

/**
 * @author t-wada
 */
public class ContinuableAssemblerProvider extends AssemblerFactory.DefaultProvider {
    public ConstructorAssembler createDefaultConstructorConstructorAssembler(ComponentDef cd) {
        return new ContinuableConstructorAssemblerProxy(cd, super.createDefaultConstructorConstructorAssembler(cd));
    }
    public ConstructorAssembler createAutoConstructorAssembler(ComponentDef cd) {
        return new ContinuableConstructorAssemblerProxy(cd, super.createAutoConstructorAssembler(cd));
    }
}
