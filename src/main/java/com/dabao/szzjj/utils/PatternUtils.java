package com.dabao.szzjj.utils;

import java.util.regex.Pattern;

/**
 * Created by dabao on 2021-05-12.
 */
public class PatternUtils {

    public static final Pattern NOT_CHINESE_WORDS = Pattern.compile("[^\\u4E00-\\u9FA5]");

    public static final Pattern NOT_NUMERAL = Pattern.compile("[^0-9]");

    public static final Pattern HOUSE_NAME = Pattern.compile("[(（第]?[A-z | ,、\\-#| 0-9  | 首一二三四五六七八九十ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ①②③④⑤⑥⑦⑧⑨⑩]+[,、\\-#]?[座区栋期号楼(单元)]?.*");
}
