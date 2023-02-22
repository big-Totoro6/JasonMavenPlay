package com.jason.example.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VipEnum implements IBaseEnum{
    Yellow(0,"黄钻"),
    Green(1,"绿钻"),
    Red(2,"红钻");

    private Integer value;
    //返回给前端显示的
    @JsonValue
    private String description;

    VipEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDescription(){
        return description;
    }

}
