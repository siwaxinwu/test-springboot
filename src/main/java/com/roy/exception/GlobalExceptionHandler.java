package com.roy.exception;

import com.roy.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * description：
 * author：dingyawu
 * date：created in 12:09 2020/11/22
 * history:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 重复请求的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(RepeatSubmitException.class)
    public ResultVO onException(RepeatSubmitException ex){
        //打印日志
        log.error(ex.getMessage());
        //todo 日志入库等等操作

        //统一结果返回
        return  ResultVO.success();
    }


    /**
     * 自定义的业务上的异常
     */
    /*@ExceptionHandler(ServiceException.class)
    public ResultResponse onException(ServiceException ex){
        //打印日志
        log.error(ex.getMessage());
        //todo 日志入库等等操作

        //统一结果返回
        return new ResultResponse(ResultCodeEnum.CODE_SERVICE_FAIL);
    }*/


    /**
     * 捕获一些进入controller之前的异常，有些4xx的状态码统一设置为200
     * @param ex
     * @return
     */
    /*@ExceptionHandler({HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class, MissingServletRequestParameterException.class,
            ServletRequestBindingException.class, ConversionNotSupportedException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MissingServletRequestPartException.class, BindException.class,
            NoHandlerFoundException.class, AsyncRequestTimeoutException.class})
    public ResultResponse onException(Exception ex){
        //打印日志
        log.error(ex.getMessage());
        //todo 日志入库等等操作

        //统一结果返回
        return new ResultResponse(ResultCodeEnum.CODE_FAIL);
    }*/
}