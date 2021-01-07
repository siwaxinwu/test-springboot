package com.roy.aop;

import lombok.Data;
import lombok.ToString;

/**
 * @description：
 * @author： dingyawu
 * @date： created in 15:38 2021-01-07
 * @history:
 */
@Data
@ToString
public class RequestInfo {
	private String ip;
	private String url;
	private String httpMethod;
	private String classMethod;
	private Object requestParams;
	private Object result;
	private Long timeCost;
}
