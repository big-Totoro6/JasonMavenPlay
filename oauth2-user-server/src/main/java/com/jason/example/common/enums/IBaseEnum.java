package com.jason.example.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public interface IBaseEnum {
    Integer getValue();

//    static <T extends IBaseEnum> T fromValue(Class<T> enumType,Integer value){
//        T t1 = Arrays.stream(enumType.getEnumConstants()).filter(t -> t.getValue().equals(value)).findAny()
//                .orElseThrow(() -> new IllegalArgumentException("No enum value " + value + " of " + enumType.getCanonicalName()));
//        return t1;
//    }
}
