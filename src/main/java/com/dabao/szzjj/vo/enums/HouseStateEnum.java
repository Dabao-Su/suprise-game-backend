package com.dabao.szzjj.vo.enums;

import java.util.HashMap;
import java.util.Map;

public enum HouseStateEnum {
//    “期房待售”， 指房屋为期房，可以销售但尚未售出。
//    “已签认购书” 指房屋已经签订认购书，但尚未签订正式的预售或现售合同。
//    “已签预售合同”，指房屋为期房，已售出并签订预售合同。
//    “已备案”， 指签订的买卖合同已在产权登记部门备案。
//    “初始登记”，指房屋已进入初始登记的状态。
//    “安居房”，指房屋为安居型商品房。
//    “自动锁定”，指开发商未及时办理合同备案，导致售房系统自动锁定，暂时无法使用。
//    “区局锁定”， 指房屋处于限制状态或开发商存在违规行为，导致售房系统被区局锁定。
//    “市局锁定”， 指房屋处于限制状态或开发商存在违规行为，导致售房系统被市局锁定。
//    “司法查封”，指房屋被司法机关锁定。

    Presale(1,"期房待售"),
    SignedSubscriptionForm(2, "已签认购书"),
    PresaleContractSigned(3, "已签预售合同"),
    Filed(4, "已备案"),
    InitialRegistration(5, "初始登记"),
    Anjufang(6, "安居房"),
    AutoLock(7, "自动锁定"),
    RegionalBureauLocking(8, "区局锁定"),
    MunicipalBureauLocking(9, "市局锁定"),
    JudicialSeizure(10, "司法查封");

    private int code;
    private String name;
    private static final Map<Object, HouseStateEnum> houseStateEnumMap = new HashMap<>();


    HouseStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    static {
        for (HouseStateEnum houseStateEnum : values()) {
            houseStateEnumMap.put(houseStateEnum.getCode(), houseStateEnum);
            houseStateEnumMap.put(houseStateEnum.getName(), houseStateEnum);
        }
    }

    public static HouseStateEnum getName(String name){
        return houseStateEnumMap.get(name);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
