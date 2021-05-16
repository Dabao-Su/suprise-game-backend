package com.dabao.szzjj.vo;

import com.dabao.szzjj.vo.enums.HouseStateEnum;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class House {
    public long buildingId;//楼id
    public long id;//套房id
    public HouseStateEnum state;//状态

    public int 房号;
    public int 楼层;
    public String 项目楼栋情况;
    public String 用途;
    public double 建筑面积;
    public double 户内面积;
    public double 分摊面积;
    public BigDecimal 拟售价格;//  xx元/平方米(按建筑面积计)

    public void set房号ByElement(Element 房号) {
        this.房号 = Integer.parseInt(房号.html());
    }

    public void set楼层ByElement(Element 楼层) {
        this.楼层 = Integer.parseInt(楼层.html());
    }

    public void set建筑面积ByElement(Element 建筑面积) {
        this.建筑面积 = Double.parseDouble(建筑面积.html());
    }

}
