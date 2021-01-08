package com.roy.aop;

import lombok.Data;

/**
 * @description：
 * @author： dingyawu
 * @date： created in 15:39 2021-01-07
 * @history:
 */
@Data
public class RequestErrorInfo {
	private String ip;
	private String url;
	private String httpMethod;
	private String classMethod;
	private Object requestParams;
	private Exception exception;
}