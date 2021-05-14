package com.dabao.szzjj.service;

import com.dabao.szzjj.vo.EsSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dabao on 2021-05-13.
 * 房源编码
 */
@Service
public class HouseSourceService {
    private static final String url = "http://zjj.sz.gov.cn/ris/szfdc/MLS/Index.aspx";

    @Autowired
    private EsSourceService esSourceService;

    public static void main(String[] args) throws IOException {
//        loadEsPrice();
    }

    /**
     * 根据房源编码查意向成交价
     */
    public void loadEsPrice(String __VIEWSTATE, String __EVENTVALIDATION, String cookie, EsSource esSource) throws IOException{
        Document document = Jsoup.connect(url)
                .data("__VIEWSTATE", __VIEWSTATE)
                .data("__EVENTVALIDATION", __EVENTVALIDATION)
                .data("checkCode", "1732")
                .data("txtCode", String.valueOf(esSource.房源编码))
                .data("BtCheck", "核对")
                .header("cookie", cookie)
                .data("__VIEWSTATEENCRYPTED", "").maxBodySize(0).post();

        Element detail1_lbllpdz = document.getElementById("detail1_lbllpdz");
        Element detail1_lblEmptyMsg = document.getElementById("detail1_lblEmptyMsg");
        if(detail1_lblEmptyMsg != null){
            esSource.set意向成交价(BigDecimal.valueOf(-1L));
        }else if(detail1_lbllpdz == null || !detail1_lbllpdz.html().equals(esSource.项目名称)){
            throw new IllegalArgumentException("验证码错误");
        }else{
            double detail1_lblyxjg = Double.parseDouble(document.getElementById("detail1_lblyxjg").html());//意向成交价
            esSource.set意向成交价(BigDecimal.valueOf(detail1_lblyxjg).setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        esSourceService.updatePrice(esSource);
    }

    public void loadEsPrice() throws IOException {
        String cookie = "ASP.NET_SessionId=v3n4y1ikfetmknem5lndf045",
                __VIEWSTATE , __EVENTVALIDATION;

        Document document = Jsoup.connect(url).maxBodySize(0).post();
        __VIEWSTATE = document.getElementById("__VIEWSTATE").val();
        __EVENTVALIDATION = document.getElementById("__EVENTVALIDATION").val();

        int count = esSourceService.countPriceNull();
        int startIndex = 0, pageSize = 100;

        try{
            while (count > 0){
                List<EsSource> priceNullEsSources = esSourceService.getPriceNull(startIndex, pageSize);
                for (EsSource esSource : priceNullEsSources){
                    loadEsPrice( __VIEWSTATE, __EVENTVALIDATION, cookie, esSource);
                    count--;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
