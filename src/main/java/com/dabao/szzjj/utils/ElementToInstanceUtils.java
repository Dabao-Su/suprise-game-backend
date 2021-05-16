package com.dabao.szzjj.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ElementToInstanceUtils<T> {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    private T instance;

    private Class<T> clazz;//转换后的对象类型


    public ElementToInstanceUtils(Class<T> objectClass) throws IllegalAccessException, InstantiationException {
        this.clazz = objectClass;
        this.instance = objectClass.newInstance();//默认构造方法生成对象
    }

    //给对象设置属性
    public void setProperty(String propertieName, Element element) throws IllegalAccessException, ParseException {
        try {
            Method writeMethod = clazz.getMethod("set" + propertieName + "ByElement", org.jsoup.nodes.Element.class);//有自定义设置属性的方法
            writeMethod.invoke(instance, element);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            try {
                Field field = clazz.getField(propertieName);
                if(!element.html().trim().equals("")){
                    Object value = null;
                    switch (field.getType().toString()){
                        case("class java.lang.String"):
                            value = element.html();break;
                        case("int"):
                        case("class java.lang.Integer"):
                            value = Integer.parseInt(element.html());break;
                        case("long"):
                        case("class java.lang.Long"):
                            value = Long.parseLong(element.html());break;
                        case("float"):
                        case("class java.lang.Float"):
                            value = Float.parseFloat(element.html());break;
                        case("double"):
                        case("class java.lang.Double"):
                            value = Double.parseDouble(element.html());break;
                        case("class java.util.Date"):
                            value = SDF.parse(element.html().replaceAll("&nbsp;",""));break;
                        case("class java.math.BigDecimal"):
                            double d = Double.parseDouble(element.html());
                            value = BigDecimal.valueOf(d).setScale(4, BigDecimal.ROUND_HALF_UP);break;
                        default:
                            value = element.html();
                    }

                    field.set(instance, value);
                }
            } catch (NoSuchFieldException e) {
                //没有这个字段就是不需要处理了
            }
        }
    }

    //根据表格的表头设置对象的属性
    public T setPropertiesByTableWithTh(Element table){
        Elements ths = table.getElementsByTag("th");

        table.getElementsByTag("tr").forEach(tr -> {
            Elements tds = tr.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i++) {
                Element td = tds.get(i);
                Element th = ths.get(i);
                try{
                    setProperty(th.html(), td);
                } catch (IllegalAccessException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return instance;
    }



    public T getInstance() {
        return instance;
    }
}
