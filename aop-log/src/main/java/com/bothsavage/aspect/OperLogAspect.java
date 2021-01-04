package com.bothsavage.aspect;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.bothsavage.annotation.OperLog;
import com.bothsavage.entity.ExceptionLog;
import com.bothsavage.entity.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class OperLogAspect {

    //操作切入点
    @Pointcut("@annotation(com.bothsavage.annotation.OperLog)")
    public void operLogPoinCut() {}

    //异常切入点
    @Pointcut("execution(* com.bothsavage.controller..*.*(..))")
    public void operExceptionLogPoinCut() {}

    //正常返回通知
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperationLog operlog = new OperationLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            OperLog opLog = method.getAnnotation(OperLog.class);
            methodName = className + "." + methodName;
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String params = JSON.toJSONString(rtnMap);

            operlog.setOperId(IdUtil.randomUUID());
            operlog.setOperModul(opLog.operModul());
            operlog.setOperType(opLog.operType());
            operlog.setOperDesc(opLog.operDesc());
            operlog.setOperMethod(methodName); // 请求方法
            operlog.setOperRequParam(params); // 请求参数
            operlog.setOperRespParam(JSON.toJSONString(keys)); // 返回结果
            operlog.setOperUri(request.getRequestURI()); // 请求URI
            operlog.setOperCreateTime(new Date()); // 创建时间

            //打印日志
            System.out.println(JSON.toJSONString(operlog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //异常返回通知
    @AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ExceptionLog excepLog = new ExceptionLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            methodName = className + "." + methodName;
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String params = JSON.toJSONString(rtnMap);

            excepLog.setExcId(IdUtil.randomUUID());
            excepLog.setExcRequParam(params); // 请求参数
            excepLog.setOperMethod(methodName); // 请求方法名
            excepLog.setExcName(e.getClass().getName()); // 异常名称
            excepLog.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
            excepLog.setOperUri(request.getRequestURI()); // 操作URI
            excepLog.setOperCreateTime(new Date()); // 发生异常时间

            //打印日志
            System.out.println(JSON.toJSONString(excepLog));
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    //转换request 请求参数
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    //转换异常信息为字符串
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}