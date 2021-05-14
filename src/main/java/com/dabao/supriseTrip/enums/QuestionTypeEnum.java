package com.dabao.supriseTrip.enums;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by dabao on 2020-12-02.
 * 问题类型
 */
public enum QuestionTypeEnum {
    SingleChoice(1, "单选题"),
    MultipleChoice(2, "多选题"),
    TrueFalse(3, "判断题"),
    GapFilling(4, "填空题");

    private int code;
    private String name;
    private static final Map<Integer, QuestionTypeEnum> questionTypeMap = new HashMap<>();

    static {
        for (QuestionTypeEnum questionTypeEnum : QuestionTypeEnum.values()) {
            questionTypeMap.put(questionTypeEnum.getCode(), questionTypeEnum);
        }
    }

    public static QuestionTypeEnum fromCode(int code){
        return questionTypeMap.get(code);
    }

    QuestionTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
