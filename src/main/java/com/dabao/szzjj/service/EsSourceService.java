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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by dabao on 2021-05-12.
 */
@Service
public class EsSourceService implements CommonRequestService{
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

    public List<EsSource> loadEsSource() throws NoSuchFieldException, InstantiationException, IllegalAccessException, IOException, ParseException {
        return Optional.ofNullable(loadPage(url, EsSource.class)).filter(list -> list.size() > 0).map(list -> {
            try{
//                sourceDao.deleteAll();
//                sourceDao.insert(list);
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }).get();
    }
}
