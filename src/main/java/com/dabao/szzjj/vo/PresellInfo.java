package com.dabao.szzjj.vo;

import com.dabao.szzjj.utils.PatternUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PresellInfo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public long id;
    public int 序号;
    public String 预售证号;
    public String 项目名称;
    public String 开发企业;
    public String 所在区;
    public Date 批准时间;

    public void set预售证号ByElement(Element element){
        Elements as = element.getElementsByTag("a");
        if(as != null && as.size() > 0){
            this.预售证号 = as.get(0).html();
        }
    }

    public void set项目名称ByElement(Element element){
        Elements as = element.getElementsByTag("a");
        if(as != null && as.size() > 0){
            Element a = as.get(0);
            String id = PatternUtils.NOT_NUMERAL.matcher(a.attr("href")).replaceAll("");
            this.id = Long.parseLong(id);
            this.项目名称 = a.html();
        }
    }

//    public void set序号ByElement(Element element){
//        if(!element.html().trim().equals("")) this.序号 = Integer.parseInt(element.html().trim());
//    }
//
//    public void set批准时间ByElement(Element element) throws ParseException {
//        if(!element.html().trim().equals("")) this.批准时间 = sdf.parse(element.html().trim().replaceAll("&nbsp;",""));
//    }

    public void setId(long id) {
        this.id = id;
    }

    public void set序号(int 序号) {
        this.序号 = 序号;
    }

    public void set项目名称(String 项目名称) {
        this.项目名称 = 项目名称;
    }

    public void set开发企业(String 开发企业) {
        this.开发企业 = 开发企业;
    }

    public void set所在区(String 所在区) {
        this.所在区 = 所在区;
    }

    public void set批准时间(Date 批准时间) {
        this.批准时间 = 批准时间;
    }

    public void set预售证号(String 预售证号) {
        this.预售证号 = 预售证号;
    }
}
