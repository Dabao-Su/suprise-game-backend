package com.dabao.supriseTrip.vo;

/**
 * Created by dabao on 2020-12-02.
 * 问题选项
 */
public class QuestionItem {
    private String content;

    private String prifix;

    public QuestionItem() {
    }

    public QuestionItem(String prifix, String content) {
        this.content = content;
        this.prifix = prifix;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrifix() {
        return prifix;
    }

    public void setPrifix(String prifix) {
        this.prifix = prifix;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "content='" + content + '\'' +
                ", prifix='" + prifix + '\'' +
                '}';
    }
}
