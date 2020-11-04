package com.dabao.supriseTrip.vo;

/**
 * Created by dabao on 2020-09-18.
 * 问题或线索类
 */
public class QuestionClue {
    public static final int TYPE_QUESTION = 0;
    public static final int TYPE_CLUE = 1;

    private long id;

    /**
     * 类型，0：问题，1：线索
     */
    private int type;

    /**
     * 问题或线索的内容
     */
    private String name;

    /**
     * 正确答案
     */
    private String trueAnswer;

    /**
     * 错误答案1
     */
    private String wrongAnswer1;

    /**
     * 错误答案2
     */
    private String wrongAnswer2;

    /**
     * 这个问题或者线索是否可以公开
     */
    private Boolean isPublic;

    public QuestionClue() {
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }
}
