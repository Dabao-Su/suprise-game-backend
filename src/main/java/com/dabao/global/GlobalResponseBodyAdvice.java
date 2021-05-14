package com.dabao.global;

import com.dabao.global.vo.ResultBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dabao on 2021-01-07.
 */
@RestControllerAdvice("com.dabao")
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static Set<String> excludeMethods = new HashSet<>();

    static {
        excludeMethods.add("com.dabao.global.GlobalExceptionHandler.*");
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        Class<?> clazz = method.getDeclaringClass();

        String thisMethodName = clazz.getName() + "." + method.getName();
        String allMethod = clazz.getName() + ".*";
        if(excludeMethods.contains(thisMethodName) || excludeMethods.contains(allMethod)) return false;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return ResultBody.success(body);
    }
}
