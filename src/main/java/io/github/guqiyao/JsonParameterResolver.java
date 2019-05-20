package io.github.guqiyao;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/4/10 16:22
 */
public class JsonParameterResolver implements HandlerMethodArgumentResolver {

    private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    /**
     * 解析器
     */
    private ParameterParser parameterParser;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParameter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception {

        String body = getRequestBody(webRequest);
        JsonParameter jsonParameter = parameter.getParameterAnnotation(JsonParameter.class);

        String fieldName = jsonParameter.value();
        if (StringUtils.isBlank(fieldName)) {
            fieldName = parameter.getParameterName();
        }

        Object result = parameterParser.parse(body, fieldName, parameter);

        if (jsonParameter.required() && Objects.isNull(result)) {
            throw new ParameterResolverException(String.format("JsonParameter的required参数值为true, 但是解析结果为null, field name : %s", fieldName));
        }

        return result;
    }

    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String body = (String) webRequest.getAttribute(JSON_BODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        if (StringUtils.isBlank(body)) {
            try {
                body = IOUtils.toString(servletRequest.getInputStream(), Charset.forName("UTF-8"));
                webRequest.setAttribute(JSON_BODY_ATTRIBUTE, body, NativeWebRequest.SCOPE_REQUEST);
            } catch (IOException e) {
                throw new ParameterResolverException("参数解析失败", e);
            }
        }
        return body;
    }

    public void setParameterParser(ParameterParser parameterParser) {
        this.parameterParser = parameterParser;
    }
}