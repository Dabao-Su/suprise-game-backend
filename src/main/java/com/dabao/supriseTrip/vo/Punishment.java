package com.dabao.supriseTrip.vo;

/**
 * Created by dabao on 2020-09-18.
 * 惩罚类
 */
public class Punishment {
    private long id;

    /**
     * 惩罚内容
     */
    private String name;

    /**
     * 解除惩罚的关键字，关键字对上我们就认为惩罚已经完成了
     */
    private String keyword;

    /**
     * 该惩罚是否可以选择跳过
     */
    private Boolean isCanSkip;

    /**
     * 这个旅程模板是否可以公开
     */
    private Boolean isPublic;

    public Punishment() {
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getCanSkip() {
        return isCanSkip;
    }

    public void setCanSkip(Boolean canSkip) {
        isCanSkip = canSkip;
    }
}
