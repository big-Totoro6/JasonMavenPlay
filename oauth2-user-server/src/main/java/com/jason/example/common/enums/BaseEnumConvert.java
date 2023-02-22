//package com.jason.example.common.enums;
//
//import jakarta.persistence.AttributeConverter;
//
//public abstract class BaseEnumConvert<T extends IBaseEnum>implements AttributeConverter<T,Integer> {
//    private final Class<T> tClass;
//
//    protected BaseEnumConvert(Class<T> tClass) {
//        this.tClass = tClass;
//    }
//
//    public Integer convertToDatabaseColumn(T attribute){
//        return attribute==null? null: (Integer) attribute.getValue();
//    }
//    public T convertToEntityAttribute(Integer value){
//        return value==null?null:IBaseEnum.fromValue(this.tClass,value);
//    }
//}
