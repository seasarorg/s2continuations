package org.seasar.continuations.config.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.seasar.continuations.config.AbstractS2ContinuationConfig;

/**
 * @author t-wada
 */
public class S2ContinuationConfig extends AbstractS2ContinuationConfig {
    private List patternSet = new ArrayList();
    
    public boolean matches(String name) {
        for (Iterator iter = patternSet.iterator(); iter.hasNext();) {
            Pattern pattern = (Pattern) iter.next();
            Matcher matcher = pattern.matcher(name);
            if(matcher.matches()) {
                return true;
            }
        }
        return false;
    }
    
    public void addPattern(String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        this.patternSet.add(pattern);
    }
}
