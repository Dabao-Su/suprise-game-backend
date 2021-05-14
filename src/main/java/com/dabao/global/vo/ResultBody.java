package com.dabao.global.vo;

import com.dabao.global.enums.ResultEnum;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Created by dabao on 2021-01-07.
 */
public class ResultBody {
    private Integer code;

    private String message;

    private Object result;

    public ResultBody() {
    }

    public ResultBody(ResultEnum result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }

    public static ResultBody success(Object data, String message){
        return (ResultBody) Optional.ofNullable(new ResultBody(ResultEnum.SUCCESS)).map(r -> {
            r.result = data;
            if(!StringUtils.isEmpty(message)) r.message = message;
            return r;
        }).get();
    }

    public static ResultBody success(Object data){
        return success(data, null);
    }

    public static ResultBody success(){
        return success(null);
    }

    public static ResultBody error(ResultEnum result, Throwable throwable){
        return (ResultBody) Optional.ofNullable(new ResultBody(result)).map(r -> {
            if(!StringUtils.isEmpty(throwable.getMessage())) r.message = throwable.getMessage();
            return r;
        }).get();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
