package com.jason.example.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumSex implements IBaseEnum{
    /**
     * 跨性别者 2
     * 男 1
     * 女 0
     */
    Transgender(2,"跨性别者"),Male(1,"男"),Female(0,"女"),UnKnow(3,"未知");

    private final Integer value;

    /**
     * @JsonValue
     * 可以用在get方法或者属性字段上，一个类只能用一个，当加上@JsonValue注解时，该类的json化结果，只有这个get方法的返回值
     * ，而不是这个类的属性键值对.
     * 序列化只序列化@JsonValue 注释的属性
     */
    @JsonValue
    private final String description;

    public String getDescription() {
        return description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

//    public static class EnumSexConvert extends BaseEnumConvert<EnumSex>{
//        protected EnumSexConvert(Class<EnumSex> enumSexClass) {
//            super(enumSexClass);
//        }
//    }
//
//    @JsonCreator
//    public static EnumSex getInstance(Integer value){
//        if (value == null){
//            value=3;
//        }
//        return IBaseEnum.fromValue(EnumSex.class,value);
//    }
}
