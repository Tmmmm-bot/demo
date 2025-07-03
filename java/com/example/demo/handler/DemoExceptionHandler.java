package com.example.demo.handler;

import com.example.demo.dataview.ResultView;
import com.example.demo.exception.DemoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by SqMax on 2018/4/2.
 */
@ControllerAdvice
public class DemoExceptionHandler {


    @ExceptionHandler(value = DemoException.class)
    @ResponseBody
    public ResultView handlerSellerException(DemoException e){
        return ResultView.error(e.getMsg());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){

    }
}
