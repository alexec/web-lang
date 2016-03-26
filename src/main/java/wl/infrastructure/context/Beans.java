package wl.infrastructure.context;

import org.openqa.selenium.WebDriver;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Beans {

    public static <T> T getBeanByType(Object target, Class<T> type) {
        try {
            @SuppressWarnings("OptionalGetWithoutIsPresent")
            Method webDriverMethod = Arrays.stream(target.getClass().getMethods())
                    .filter(method -> WebDriver.class.isAssignableFrom(method.getReturnType()))
                    .filter(method -> method.getParameterCount() == 0)
                    .findFirst()
                    .get();
            return type.cast(webDriverMethod.invoke(target));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("failed to create driver", e);
        }
    }

    public static void inject(Object target, Object bean) {

        Arrays.stream(target.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Resource.class) != null)
                .peek(field -> field.setAccessible(true))
                .forEach(
                        field -> {
                            try {
                                field.set(target, bean);
                            } catch (IllegalAccessException e) {
                                throw new AssertionError(e);
                            }
                        }
                );
    }

    public static <A extends Annotation> void invokeMethodsByAnnotation(Object target, Class<A> annotationClass, Object... arguments) {

        Arrays.stream(target.getClass().getMethods())
                .filter(method -> method.getAnnotation(annotationClass) != null)
                .peek(method -> method.setAccessible(true))
                .forEach(method -> {
                    try {
                        method.invoke(target, arguments);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException(e);
                    }
                });


    }
}
