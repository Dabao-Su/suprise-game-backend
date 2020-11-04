package com.dabao.supriseTrip.vo;

/**
 * Created by dabao on 2020-09-18.
 * 场景类
 */
public class Scene {
    private long id;

    /**
     * 地点，如撒哈拉沙漠
     */
    private String location;

    /**
     * 行为，比如跋山涉水了好多天
     */
    private String behavior;

    /**
     * 困难，比如水喝光了找不到水源生命奄奄一息
     */
    private String problem;

    /**
     * 场景目的，比如水源或者提问的问题等
     */
    private String purpose;

    /**
     * 奖品，该场景对应的奖品
     */
    private String prize;

    /**
     * 问题，必须要有的
     */
    private QuestionClue question;

    /**idea
     * 线索，可以没有
     */
    private QuestionClue clue;

    /**
     * 这个场景模板是否可以公开
     */
    private Boolean isPublic;

    public Scene() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public QuestionClue getQuestion() {
        return question;
    }

    public void setQuestion(QuestionClue question) {
        this.question = question;
    }

    public QuestionClue getClue() {
        return clue;
    }

    public void setClue(QuestionClue clue) {
        this.clue = clue;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
