package io.github.guqiyao.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import io.github.guqiyao.ParameterParser;
import org.springframework.core.MethodParameter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/4/10 16:24
 */
public class JacksonParameterParser implements ParameterParser {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Object parse(String body, String key, MethodParameter methodParameter) throws IOException {
        return OBJECT_MAPPER.readValue(parseValue(body, key), generateJavaType(methodParameter));
    }

    /**
     * 生成java type
     * @param parameter    method parameter
     * @return             java type
     */
    private JavaType generateJavaType(MethodParameter parameter) {
        Class<?> parameterClass = parameter.getParameterType();
        Class<?> genericClass = getGenericType(parameterClass, parameter);
        if (Objects.isNull(genericClass)) {
            return SimpleType.constructUnsafe(parameterClass);
        }
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(parameter.getParameterType(), genericClass);
    }

    /**
     * 根据置顶的key获取body
     * @param body              请求体
     * @param key               key
     * @return                  需要解析的body
     * @throws IOException      io exception
     */
    private String parseValue(String body, String key) throws IOException {
        return OBJECT_MAPPER.readTree(body).get(key).toString();
    }

    /**
     * 获取泛型的参数类型
     * @param parameterType     参数类型
     * @param parameter         method parameter
     * @return                  泛型类型
     */
    private Class<?> getGenericType(Class<?> parameterType, MethodParameter parameter) {
        if (parameterType.isAssignableFrom(List.class)) {
            ParameterizedType parameterizedType = ((ParameterizedType) parameter.getGenericParameterType());
            Type[] types = parameterizedType.getActualTypeArguments();
            return (Class<?>) types[0];
        }

        return null;
    }
}