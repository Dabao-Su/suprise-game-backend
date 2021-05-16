package com.dabao.szzjj.service;

import com.dabao.szzjj.utils.ElementToInstanceUtils;
import com.dabao.szzjj.vo.Building;
import com.dabao.szzjj.vo.PresellInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectInfoService implements CommonRequestService{
    private static final String GET_PRE_SELE_INFO_URL = "http://zjj.sz.gov.cn/ris/bol/szfdc/index.aspx";

    private static final String GET_PRODUCT_DETAIL_URL = "http://zjj.sz.gov.cn/ris/bol/szfdc/projectdetail.aspx?id=";

    private static final String GET_BUILDING_INFO_URL = "http://zjj.sz.gov.cn/ris/bol/szfdc/building.aspx?";


    public List<PresellInfo> getPresaleInfo() throws IOException, IllegalAccessException, NoSuchFieldException, InstantiationException, ParseException {
        return Optional.ofNullable(loadPage(GET_PRE_SELE_INFO_URL, PresellInfo.class)).filter(list -> list.size() > 0).map(list -> {
            try{
//                sourceDao.deleteAll();
//                sourceDao.insert(list);
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }).get();
    }

    public static List<Building> getProductDetailByPresaleId(long presaleId) throws IOException {
        Document document = Jsoup.connect(GET_PRODUCT_DETAIL_URL + presaleId).maxBodySize(0).get();
        return Optional.ofNullable(document.getElementsByTag("table")).filter(tables -> tables != null && tables.size() > 1).map(tables -> {
            Element buildingTable = tables.get(1);
            List<Building> buildings = new ArrayList<>();
            try {
                ElementToInstanceUtils elementToInstanceUtils = new ElementToInstanceUtils(Building.class);
                buildings.add((Building) elementToInstanceUtils.setPropertiesByTableWithTh(buildingTable));
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            return buildings;
        }).get();
    }

    public static Building getBuildingInfo(long presaleId, long buildingId, Building building){
        Document document = Jsoup.connect(GET_BUILDING_INFO_URL + "id=" + buildingId + "&presellid=" + presaleId ).maxBodySize(0).get();
        document.getElementById("divShowBranch").getElementsByTag("a").forEach(branch -> {



        };
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getProductDetailByPresaleId(54393L));
    }
}
