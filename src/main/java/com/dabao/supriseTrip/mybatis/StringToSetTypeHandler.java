package com.dabao.supriseTrip.mybatis;

import com.dabao.supriseTrip.vo.QuestionItem;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by dabao on 2020-12-04.
 * 逗号分隔的字符串转换成 集合
 */
@MappedJdbcTypes({JdbcType.VARCHAR})
@MappedTypes({String.class})
public class StringToSetTypeHandler implements TypeHandler<Set<String>> {
    /**
     * 分隔符
     */
    private String SPLIT = ",";

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Set<String> strings, JdbcType jdbcType) throws SQLException {
        StringBuffer content = new StringBuffer();
        strings.stream().filter(t -> !StringUtils.isEmpty(t)).forEach(t -> content.append(t).append(SPLIT));
        preparedStatement.setString(i, content.toString());
    }

    @Override
    public Set<String> getResult(ResultSet resultSet, String s) throws SQLException {
        Set<String> set = new LinkedHashSet<>();
        return Optional.ofNullable(resultSet.getString(s)).map(value -> {
            set.addAll(Arrays.asList(value.split(SPLIT)));
            return set;
        }).orElse(set);
    }

    @Override
    public Set<String> getResult(ResultSet resultSet, int i) throws SQLException {
        Set<String> set = new LinkedHashSet<>();
        return Optional.ofNullable(resultSet.getString(i)).map(value -> {
            set.addAll(Arrays.asList(value.split(SPLIT)));
            return set;
        }).orElse(set);
    }

    @Override
    public Set<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        Set<String> set = new LinkedHashSet<>();
        return Optional.ofNullable(callableStatement.getString(i)).map(value -> {
            set.addAll(Arrays.asList(value.split(SPLIT)));
            return set;
        }).orElse(set);
    }

    public static void main(String[] args) {
//        Set<String> strings= new LinkedHashSet<>(Arrays.asList("",null,"null"));
//
//        StringBuffer content = new StringBuffer();
//        strings.stream().filter(t -> !StringUtils.isEmpty(t)).forEach(t -> content.append(t).append(","));
//
//        System.out.println(content);
//
//        System.out.println(Optional.ofNullable(content.toString()).map(value -> {
//            Set<String> set = new LinkedHashSet<>();
//            set.addAll(Arrays.asList(value.split(",")));
//            return set;
//        }).orElse(null));

        Queue<QuestionItem> queue = new LinkedBlockingQueue();
        QuestionItem questionItem1 = new QuestionItem("A","A");
        QuestionItem questionItem2 = new QuestionItem("B","B");

        queue.addAll(Arrays.asList(questionItem1,questionItem2));

        QuestionItem top = queue.peek();
        top.setContent("C");
        System.out.println(queue.peek());

    }
}
