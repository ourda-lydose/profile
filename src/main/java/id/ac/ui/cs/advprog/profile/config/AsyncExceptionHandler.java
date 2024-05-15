package id.ac.ui.cs.advprog.profile.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("Method name: " + method.getName() +
                " --- " + Arrays.toString(params) + " --- " +
                "Error message: " + ex.getMessage()
        );
    }
}
