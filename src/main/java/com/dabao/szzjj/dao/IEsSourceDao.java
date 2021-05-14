package com.dabao.szzjj.dao;

import com.dabao.szzjj.vo.EsSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dabao on 2021-05-12.
 */
@Mapper
public interface IEsSourceDao {

    void insert(List<EsSource> esSources);

    void deleteAll();

    int countPriceNull();

    List<EsSource> getPriceNull(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    void updatePrice(EsSource esSource);
}
