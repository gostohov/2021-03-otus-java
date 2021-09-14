package ru.gostohov.proxy;

import java.lang.reflect.Proxy;

public class ProxyIoC {

    public ProxyIoC() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T createWithLogging(Class<T> itf, T target) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[]{itf},
                new LogInvocationHandler(target)
        );
    }
}
