package wl.api;

import wl.config.DefaultConfig;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ContextConfig {
    Class<?> value() default DefaultConfig.class;
}
