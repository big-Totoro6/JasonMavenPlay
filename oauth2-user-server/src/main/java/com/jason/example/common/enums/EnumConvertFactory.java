package com.jason.example.common.enums;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class EnumConvertFactory implements ConverterFactory<String, IBaseEnum> {
    @Override
    public <T extends IBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToIEum<>(targetType);
    }

    @SuppressWarnings("all")
    private static class StringToIEum<T extends IBaseEnum> implements Converter<String, T> {
        private Class<T> targerType;
        public StringToIEum(Class<T> targerType) {
            this.targerType = targerType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return (T) EnumConvertFactory.getIEnum(this.targerType, source);
        }
    }

    public static <T extends IBaseEnum> Object getIEnum(Class<T> targerType, String source) {
        for (T enumObj : targerType.getEnumConstants()) {
            if (source.equals(String.valueOf(enumObj.getValue()))) {
                return enumObj;
            }
        }
        return null;
    }
}

