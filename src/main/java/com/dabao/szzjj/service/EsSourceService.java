package com.dabao.szzjj.service;

import com.dabao.szzjj.dao.IEsSourceDao;
import com.dabao.szzjj.utils.PatternUtils;
import com.dabao.szzjj.vo.EsSource;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dabao on 2021-05-12.
 */
@Service
public class EsSourceService {
    private static final String url = "http://zjj.sz.gov.cn/ris/bol/szfdc/EsSource.aspx?zone=%E7%A6%8F%E7%94%B0";

    @Autowired
    private IEsSourceDao sourceDao;

    public void updatePrice(EsSource esSource){
        sourceDao.updatePrice(esSource);
    }

    public int countPriceNull(){
        return sourceDao.countPriceNull();
    }

    public List<EsSource> getPriceNull(int startIndex, int pageSize){
        return sourceDao.getPriceNull(0,100);
    }

    public void loadEsSource() throws IOException, NoSuchFieldException, IllegalAccessException {
        int ddlPageCount = 20, pageNum = 1, totalDataCount = 0;
        ArrayList<EsSource> esSources = new ArrayList<>();

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

            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");
                EsSource esSource = new EsSource();

                for (int tdIndex = 0; tdIndex < tds.size(); tdIndex++) {
                    String headerName = PatternUtils.NOT_CHINESE_WORDS.matcher(headerTds.get(tdIndex).html()).replaceAll("");
                    Element td = tds.get(tdIndex);
                    Field field = EsSource.class.getField(headerName);
                    try {
                        Method writeMethod = EsSource.class.getMethod("set" + headerName + "ByElement", Element.class);
                        writeMethod.invoke(esSource, td);
                    } catch (NoSuchMethodException | InvocationTargetException ex) {
                        field.set(esSource, td.html());
                    }
//                System.out.print(tdValue + "   ");
                }
                totalDataCount--;
                esSources.add(esSource);
//                System.out.println(esSource);
            }
            pageNum++;
        }

        try{
            sourceDao.deleteAll();
            sourceDao.insert(esSources);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
