package com.course.server.enums;

public enum YesNoEnum {

    YES("1", "是"),
    NO("0", "否");

    private String code;

    private String desc;

    YesNoEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
