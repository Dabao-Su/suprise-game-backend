package com.dabao.supriseTrip.vo;

import java.util.List;
import java.util.Set;

/**
 * Created by dabao on 2020-09-18.
 * 问题或线索类
 */
public class Question {
    private long id;
    /**
     * 问题或线索的内容
     */
    private String name;

    /**
     * 问题类型
     * 具体查看 QuestionTypeEnum
     */
    private Integer type;

    /**
     * 答案选项
     */
    private List<QuestionItem> items;

    /**
     * 正确答案前缀
     */
    private Set<String> answer;

    /**
     * 这个问题是否可以公开
     */
    private Boolean isPublic;

    public Question() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<QuestionItem> getItems() {
        return items;
    }

    public void setItems(List<QuestionItem> items) {
        this.items = items;
    }

    public Set<String> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<String> answer) {
        this.answer = answer;
    }
}
