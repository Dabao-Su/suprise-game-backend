package com.dabao.global;

import com.dabao.global.enums.ResultEnum;
import com.dabao.global.vo.ResultBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by dabao on 2021-01-07.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultBody exceptionHandle(Exception e){
        return ResultBody.error(ResultEnum.INTERNAL_SERVER_ERROR, e);
    }
}
