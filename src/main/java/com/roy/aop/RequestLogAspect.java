package com.roy.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description：
 * Request Info      :
 * {"classMethod":"com.roy.controller.UserController.getById","httpMethod":"GET",
 * "ip":"0:0:0:0:0:0:0:1","requestParams":{"id":"1"},
 * "result":{"age":18,"birthday":1610008150929,"id":"1","name":"roy"},
 * "timeCost":12,"url":"http://localhost:8080/user/1"}
 * @author： dingyawu
 * @date： created in 15:39 2021-01-07
 * @history:
 */
@Component
@Aspect
public class RequestLogAspect {

	private final static Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

	@Pointcut("@annotation(com.roy.annotation.ReqLog)")
	private void reqLog() {

	}

	@Around("reqLog()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		long start = System.currentTimeMillis();

		//获取请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		if (1 == 1){
			throw new Exception("are you ok");
		}
		Object result = proceedingJoinPoint.proceed();

		RequestInfo requestInfo = new RequestInfo();
		requestInfo.setIp(request.getRemoteAddr());
		requestInfo.setUrl(request.getRequestURL().toString());
		requestInfo.setHttpMethod(request.getMethod());
		requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
			proceedingJoinPoint.getSignature().getName()));
		requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
		requestInfo.setResult(result);
		requestInfo.setTimeCost(System.currentTimeMillis() - start);
		logger.info("Request Info : {}", JSON.toJSONString(requestInfo));
		return result;
	}


	@AfterThrowing(pointcut = "reqLog()", throwing = "e")
	public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
		requestErrorInfo.setIp(request.getRemoteAddr());
		requestErrorInfo.setUrl(request.getRequestURL().toString());
		requestErrorInfo.setHttpMethod(request.getMethod());
		requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
			joinPoint.getSignature().getName()));
		requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
		requestErrorInfo.setException(e);
		logger.info("Error Request Info : {}", JSON.toJSONString(requestErrorInfo));
	}

	/**
	 * 获取入参
	 * @param proceedingJoinPoint
	 *
	 * @return
	 * */
	private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
		//参数名
		String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
		//参数值
		Object[] paramValues = proceedingJoinPoint.getArgs();

		return buildRequestParam(paramNames, paramValues);
	}

	private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
		//参数名
		String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
		//参数值
		Object[] paramValues = joinPoint.getArgs();

		return buildRequestParam(paramNames, paramValues);
	}

	private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
		Map<String, Object> requestParams = new HashMap<>();
		for (int i = 0; i < paramNames.length; i++) {
			Object value = paramValues[i];

			//如果是文件对象
			if (value instanceof MultipartFile) {
				MultipartFile file = (MultipartFile) value;
				//获取文件名
				value = file.getOriginalFilename();
			}
			requestParams.put(paramNames[i], value);
		}
		return requestParams;
	}




}