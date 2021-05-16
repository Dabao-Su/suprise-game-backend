package com.dabao.szzjj.vo;

import com.dabao.szzjj.utils.PatternUtils;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Building {
    public long id;
    public String 宗地号;
    public String 项目名称;
    public String 楼名;
    public String 建设工程规划许可证;
    public String 建筑工程施工许可证;
    public String 座号;

    public List<House> houses;//套房信息

    public void set套房信息ByElement(Element element){
        String href = element.getElementsByTag("a").get(0).attr("href");
        Matcher matcher = Pattern.compile("id=\\d+").matcher(href);
        if(matcher.find()){
            String idQueryString = matcher.group();
            String idString = PatternUtils.NOT_NUMERAL.matcher(idQueryString).replaceAll("");
            this.id = Long.parseLong(idString);
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public void set宗地号(String 宗地号) {
        this.宗地号 = 宗地号;
    }

    public void set项目名称(String 项目名称) {
        this.项目名称 = 项目名称;
    }

    public void set楼名(String 楼名) {
        this.楼名 = 楼名;
    }

    public void set建设工程规划许可证(String 建设工程规划许可证) {
        this.建设工程规划许可证 = 建设工程规划许可证;
    }

    public void set建筑工程施工许可证(String 建筑工程施工许可证) {
        this.建筑工程施工许可证 = 建筑工程施工许可证;
    }

    public void set座号(String 座号) {
        this.座号 = 座号;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", 宗地号='" + 宗地号 + '\'' +
                ", 项目名称='" + 项目名称 + '\'' +
                ", 楼名='" + 楼名 + '\'' +
                ", 建设工程规划许可证='" + 建设工程规划许可证 + '\'' +
                ", 建筑工程施工许可证='" + 建筑工程施工许可证 + '\'' +
                ", 座号='" + 座号 + '\'' +
                '}';
    }
}
