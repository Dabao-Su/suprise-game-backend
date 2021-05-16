package com.dabao.szzjj.service;

import com.dabao.szzjj.utils.ElementToInstanceUtils;
import com.dabao.szzjj.utils.PatternUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CommonRequestService {

    default <T> List<T> loadPage(String url, Class<T> t) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        int ddlPageCount = 20, pageNum = 1, totalDataCount = 0;
        ArrayList<T> list = new ArrayList<>();

        Document document = Jsoup.connect(url).method(Connection.Method.POST).maxBodySize(0).post();
        Elements trs = document.getElementsByTag("tr");
        Elements headerTds = trs.get(0).getElementsByTag("th");
        String totalDataCountStr = PatternUtils.NOT_NUMERAL.matcher(document.getElementById("SelectPageSizeDiv").getElementsByTag("span").get(0).html()).replaceAll("");
        totalDataCount = Integer.parseInt(totalDataCountStr);
        String __VIEWSTATE = document.getElementById("__VIEWSTATE").val(),
                __EVENTVALIDATION = document.getElementById("__EVENTVALIDATION").val();

        while(totalDataCount > 0){
            document = Jsoup.connect(url).method(Connection.Method.POST).maxBodySize(0)
                    .data("__VIEWSTATE", __VIEWSTATE)
                    .data("__EVENTVALIDATION", __EVENTVALIDATION)
                    .data("__EVENTTARGET", "AspNetPager1")
                    .data("__EVENTARGUMENT", String.valueOf(pageNum))
                    .data("__VIEWSTATEENCRYPTED","")
                    .data("ddlPageCount", String.valueOf(ddlPageCount))
                    .post();
            trs = document.getElementsByTag("tr");

            for (int i = 1; i < trs.size() && i < ddlPageCount; i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");
                ElementToInstanceUtils elementToInstanceUtils = new ElementToInstanceUtils(t);

                for (int tdIndex = 0; tdIndex < tds.size(); tdIndex++) {
                    String headerName = PatternUtils.NOT_CHINESE_WORDS.matcher(headerTds.get(tdIndex).html()).replaceAll("");
                    Element td = tds.get(tdIndex);
                    elementToInstanceUtils.setProperty(headerName, td);
//                System.out.print(tdValue + "   ");
                }
                totalDataCount--;
                list.add((T) elementToInstanceUtils.getInstance());
//                System.out.println(esSource);
            }
            pageNum++;
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(int.class.toString());
        System.out.println(Integer.class.toString());

        System.out.println(long.class.toString());
        System.out.println(Long.class.toString());

        System.out.println(float.class.toString());
        System.out.println(Float.class.toString());

        System.out.println(double.class.toString());
        System.out.println(Double.class.toString());

        System.out.println(Date.class.toString());

        System.out.println(String.class.toString());

        System.out.println(BigDecimal.class.toString());
    }
}
