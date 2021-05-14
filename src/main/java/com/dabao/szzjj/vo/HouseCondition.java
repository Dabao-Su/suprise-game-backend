package com.dabao.szzjj.vo;

import com.dabao.szzjj.utils.PatternUtils;

/**
 * Created by dabao on 2021-05-10.
 */
public class HouseCondition {
    public String 序号;
    public String 行政区;
    public String 街道;
    public String 项目名称;
    public String 成交参考价格;

    public void set项目名称(String 项目名称) {
        this.项目名称 = PatternUtils.NOT_CHINESE_WORDS.matcher(项目名称).replaceAll("");
    }

    @Override
    public String toString() {
        return "HouseCondition{" +
                "序号='" + 序号 + '\'' +
                ", 行政区='" + 行政区 + '\'' +
                ", 街道='" + 街道 + '\'' +
                ", 项目名称='" + 项目名称 + '\'' +
                ", 成交参考价格='" + 成交参考价格 + '\'' +
                '}';
    }
}
