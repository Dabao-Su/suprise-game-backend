package com.dabao.supriseTrip.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dabao.supriseTrip.vo.QuestionItem;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dabao on 2020-12-02.
 * 将存储在数据库中的 JsonArray 转换成 List 对象
 */
@MappedTypes({QuestionItem.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class StringToJsonArrayTypeHandler<T extends Object> implements TypeHandler<List<T>> {
    private List<T> getListByJsonArrayString(String content){
        List<T> list = null;
        if(StringUtils.isEmpty(content)) list = new ArrayList<>();
        else {
            list = JSON.parseObject(content, new TypeReference<ArrayList<T>>() {});
        }
        return list;
    }

    /**
     * Mybatis 设置参数时该如何把 Java类型的参数转换成对应的数据库类型
     *  PreparedStatement pstmt = con.prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?");
     *  pstmt.setBigDecimal(1, 153833.00)
     *  pstmt.setInt(2, 110592)
     *
     * @param preparedStatement
     * @param i 当前参数的位置
     * @param ts 当前参数的Java对象
     * @param jdbcType 当前参数的数据库类型
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<T> ts, JdbcType jdbcType) throws SQLException {
        String content = null;
        if(CollectionUtils.isEmpty(ts)){
            content = JSON.toJSONString(ts);
        }
        preparedStatement.setString(i, content);
    }

    @Override
    public List<T> getResult(ResultSet resultSet, String s) throws SQLException {
        return getListByJsonArrayString(resultSet.getString(s));
    }

    @Override
    public List<T> getResult(ResultSet resultSet, int i) throws SQLException {
        return getListByJsonArrayString(resultSet.getString(i));
    }

    @Override
    public List<T> getResult(CallableStatement callableStatement, int i) throws SQLException {
        return getListByJsonArrayString(callableStatement.getString(i));
    }
}
