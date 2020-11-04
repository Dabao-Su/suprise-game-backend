package com.dabao.supriseTrip.vo;

import java.util.List;

/**
 * Created by dabao on 2020-09-18.
 * 旅程模板
 */
public class Journey {

    private long id;

    /**
     * 这个旅程模板是否可以公开
     */
    private Boolean isPublic;

    /**
     * 创建旅程的人的昵称
     */
    private String creatorName;

    /**
     * 旅程游戏者昵称
     */
    private String playerName;

    /**
     * 一段旅程会有很多个场景
     */
    private List<Scene> scenes;

    /**
     * 惩罚列表
     */
    private List<Punishment> punishments;

    /**
     * 惩罚出现的频率，每失败多少个场景就会出现一次惩罚，0表示没有惩罚
     */
    private int punishmentFrequency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Journey() {
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }

    public List<Punishment> getPunishments() {
        return punishments;
    }

    public void setPunishments(List<Punishment> punishments) {
        this.punishments = punishments;
    }

    public int getPunishmentFrequency() {
        return punishmentFrequency;
    }

    public void setPunishmentFrequency(int punishmentFrequency) {
        this.punishmentFrequency = punishmentFrequency;
    }
}
