package com.tenfar.ddd.common.advice;

import com.tenfar.ddd.common.enums.ResultCode;
import com.tenfar.ddd.common.handler.MetaHandler;
import com.tenfar.ddd.common.response.Meta;
import com.tenfar.ddd.common.response.Result;
import com.tenfar.ddd.common.response.ResultList;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class UnifiedResponseAdvice implements ResponseBodyAdvice<Object> {

    private final Map<Class<?>, MetaHandler> metaHandlerMap;

    @Autowired
    public UnifiedResponseAdvice(Map<Class<?>, MetaHandler> metaHandlerMap) {
        this.metaHandlerMap = metaHandlerMap; // Use the dynamically registered handlers
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();

        if (method == null || method.getReturnType().equals(Void.TYPE)) {
            log.debug("Unified Response: method is null, return type is void, or non-JSON response, skipping.");
            return false;
        }

        log.debug("Unified Response: non-null return value, proceeding to wrap.");
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body == null) {
            Result<Object> result = new Result<>();
            result.setCode(ResultCode.FAIL.getCode()); // Set a default error code
            result.setMessage("No data available"); // Set a default message
            return result;
        }
        if (body instanceof Result || body instanceof ResultList) {
            return body;
        }
        if (body instanceof List<?>) {
            ResultList<Object> resultList = new ResultList<>();
            resultList.setData((List<Object>) body); // Directly cast to List<Object>
            resultList.setMeta(generateMeta(body)); // Generate meta information
            return resultList;
        }
        Result<Object> result = new Result<>();
        result.setData(body);
        result.setMeta(generateMeta(body)); // Generate meta information
        return result;
    }

    private Meta generateMeta(Object body) {
        Meta meta = new Meta();
        // Automatically register handler
        MetaHandler handler = metaHandlerMap.get(body.getClass());
        if (handler != null) {
            handler.handle(meta, body);
        } else {
            log.warn("No corresponding MetaHandler found, please check registered handlers.");
        }
        return meta;
    }
}