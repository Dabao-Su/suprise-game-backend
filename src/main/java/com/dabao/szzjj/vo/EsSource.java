package com.dabao.szzjj.vo;

import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dabao on 2021-05-12.
 */
public class EsSource {

    public String 项目名称;
    public String 合同流水号;
    public String 区属;
    public Double 面积;
    public String 用途;
    public Integer 楼层;
    public Long 房源编码;
    public String 代理中介名称;
    public Date 发布日期;

    public BigDecimal 意向成交价;


    public void set项目名称ByElement(Element 项目名称) {
        this.项目名称 = 项目名称.getElementsByTag("a").html();
    }

    public void set代理中介名称ByElement(Element 代理中介名称) {
        this.代理中介名称 = 代理中介名称.getElementsByTag("a").html();
    }

//    public void set发布日期ByElement(Element 发布日期) throws ParseException {
//        this.发布日期 = sdf.parse(发布日期.html().replaceAll("&nbsp;",""));
//    }
//
//    public void set楼层ByElement(Element 楼层) {
//        String html = 楼层.html();
//        if(!html.trim().equals("")) this.楼层 = Integer.parseInt(html);
//    }
//
//    public void set房源编码ByElement(Element 房源编码) {
//        this.房源编码 = Long.parseLong(房源编码.html());
//    }
//
//    public void set面积ByElement(Element 面积) {
//        this.面积 = Double.parseDouble(面积.html());
//    }

    public void set项目名称(String 项目名称) {
        this.项目名称 = 项目名称;
    }

    public void set合同流水号(String 合同流水号) {
        this.合同流水号 = 合同流水号;
    }

    public void set区属(String 区属) {
        this.区属 = 区属;
    }

    public void set面积(Double 面积) {
        this.面积 = 面积;
    }

    public void set用途(String 用途) {
        this.用途 = 用途;
    }

    public void set楼层(Integer 楼层) {
        this.楼层 = 楼层;
    }

    public void set房源编码(Long 房源编码) {
        this.房源编码 = 房源编码;
    }

    public void set代理中介名称(String 代理中介名称) {
        this.代理中介名称 = 代理中介名称;
    }

    public void set发布日期(Date 发布日期) {
        this.发布日期 = 发布日期;
    }

    public void set意向成交价(BigDecimal 意向成交价) {
        this.意向成交价 = 意向成交价;
    }
}
