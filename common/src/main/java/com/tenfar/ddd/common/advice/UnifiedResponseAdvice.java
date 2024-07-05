package com.tenfar.ddd.common.advice;

import com.tenfar.ddd.common.response.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Objects;

@ControllerAdvice
@Log4j2
public class UnifiedResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();

        if (Objects.isNull(method) || method.getReturnType().equals(Void.TYPE)) {
            log.debug("Unified Response:method为空、返回值为void和Response类型、非JSON，跳过");
            return false;
        }

        log.debug("Unified Response:非空返回值，需要进行封装");
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return new Result<>();
    }
}
