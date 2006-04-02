package org.seasar.continuations.classloader;

import java.io.IOException;

import org.seasar.continuations.config.InstrumentationPattern;
import org.seasar.continuations.exception.SContinuableClassLoadingException;
import org.seasar.framework.log.Logger;

import com.uwyn.rife.continuations.ContinuationInstrumentor;
import com.uwyn.rife.continuations.util.ClassByteUtil;

/**
 * @author t-wada
 */
public class ContinuableClassLoader extends ClassLoader {
    private static Logger logger_ = Logger.getLogger(ContinuableClassLoader.class);
    private ClassLoader parentLoader;
    private InstrumentationPattern instrumentationPattern;
    
    public ContinuableClassLoader(InstrumentationPattern instrumentationPattern, ClassLoader parent) {
        super(parent);
        this.instrumentationPattern = instrumentationPattern;
        this.parentLoader = parent;
    }
    
    public Class loadClass(String name) throws ClassNotFoundException {
        if (shouldInstrument(name) && isNotLoadedYet(name)) {
            try {
                byte[] bytes = ClassByteUtil.getBytes(name, parentLoader);
                if (bytes == null) {
                    throw new SContinuableClassLoadingException("ECNT0001", name);
                }

                byte[] resumedBytes = ContinuationInstrumentor.instrument(bytes, name, false);

                if (resumedBytes == null) {
                    return parentLoader.loadClass(name);
                } else {
                    logger_.info("## instrumented class=[" + name + "]");
                    return defineClass(name, resumedBytes, 0, resumedBytes.length);
                }
            } catch (IOException e) {
                throw new SContinuableClassLoadingException("ECNT0002", name, e);
            }
        } else {
            return parentLoader.loadClass(name);
        }
    }

    private boolean isNotLoadedYet(String name) {
        return findLoadedClass(name) == null;
    }
    private boolean shouldInstrument(String name) {
        return this.instrumentationPattern.matches(name);
    }
}
