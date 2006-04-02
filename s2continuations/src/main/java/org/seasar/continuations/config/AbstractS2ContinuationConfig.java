package org.seasar.continuations.config;


import com.uwyn.rife.continuations.ContinuationConfig;

/**
 * @author t-wada
 */
public abstract class AbstractS2ContinuationConfig extends ContinuationConfig implements InstrumentationPattern {
    private String continuableClassInternalName;
    private String continuableInterfaceInternalName;
    private String entryMethod;
    private String pauseSignature;
    private String continuableClassOrInterfaceName = "com.uwyn.rife.continuations.Continuable";
    
    public String getContinuableClassInternalName() {
        return continuableClassInternalName;
    }
    public void setContinuableClassInternalName(String continuableClassInternalName) {
        this.continuableClassInternalName = continuableClassInternalName;
    }
    public String getContinuableClassOrInterfaceName() {
        return continuableClassOrInterfaceName;
    }
    public void setContinuableClassOrInterfaceName(String continuableClassOrInterfaceName) {
        this.continuableClassOrInterfaceName = continuableClassOrInterfaceName;
    }
    public String getContinuableInterfaceInternalName() {
        return continuableInterfaceInternalName;
    }
    public void setContinuableInterfaceInternalName(String continuableInterfaceInternalName) {
        continuableClassInternalName = continuableInterfaceInternalName;
    }
    public String getEntryMethod() {
        return entryMethod;
    }
    public void setEntryMethod(String entryMethod) {
        this.entryMethod = entryMethod;
    }
    public String getPauseSignature() {
        return this.pauseSignature;
    }
    public void setPauseSignature(String pauseSignature) {
        this.pauseSignature = pauseSignature;
    }
    
    public void init() {
        ContinuationConfig.setInstance(this);
    }
}
