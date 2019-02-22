package com.boot.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Order(100)
public class RestActionInterceptor {
    private static Log logger = LogFactory.getLog(RestActionInterceptor.class);

    public RestActionInterceptor() {
    }

    @Pointcut("execution(* com.boot.core..*.*(..)) && ( @annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping)   || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)  || @annotation(org.springframework.web.bind.annotation.DeleteMapping) || @annotation(org.springframework.web.bind.annotation.PatchMapping))")
    private void anyMethod() {
    }

    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
        String action = joinPoint.getSignature().toLongString();
        Object[] args = joinPoint.getArgs();
        StringBuilder argsBuilder = new StringBuilder();
        Exception exception = null;
        StringBuilder logBuilder = new StringBuilder();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String appVersion = request.getHeader("APPVersion");
        String deviceModel = request.getHeader("DeviceModel");
        String deviceResolution = request.getHeader("DeviceResolution");
        String sysVersion = request.getHeader("SysVersion");
        String channel = request.getHeader("channel");
        String channelNo = request.getHeader("channel_no");
        logBuilder.append("--APPVersion:").append(appVersion).append("--DeviceModel:").append(deviceModel).append("--DeviceResolution:").append(deviceResolution).append("--SysVersion:").append(sysVersion).append("--channel:").append(channel).append("--channel_no:").append(channelNo).append("--servletPath:").append(request.getServletPath()).append("--methodInfo:").append(action);
        long startTime = System.currentTimeMillis();
        String retStr;
        if (args.length > 0) {
            if (args[0] instanceof Map) {
                retStr = this.getServletPath(request);
                Map argsMap = this.skipServletPathKeys(retStr, "faceCheckByDataPackage", (Map) args[0], Arrays.asList("dataPackage"));
                argsMap = this.skipServletPathKeys(retStr, "faceCheckByDataType", argsMap, Arrays.asList("fileList", "dataPackage", "delta"));
                argsMap = this.skipServletPathKeys(retStr, "merchFaceCheckByDataType", argsMap, Arrays.asList("fileList", "delta"));
                argsMap = this.skipServletPathKeys(retStr, "getOCRCertInfo", argsMap, Arrays.asList("data"));
                argsBuilder.append(new JSONObject(argsMap));
            } else {
                argsBuilder.append(args[0]);
            }
        }

        for (int i = 1; i < args.length; ++i) {
            argsBuilder.append(", ").append(args[i]);
        }
        // 异常的获取可以根据框架中已封装的方法获取
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception var19) {
            exception = var19;
            result = var19.getMessage();
        }
        retStr = JSONObject.valueToString(result);
        logBuilder.append(" args(").append(argsBuilder).append(")").append(" return(").append(retStr).append(")");
        if (exception != null) {
            logBuilder.append(" EXCEPTION:").append("exception.getMessage()").append("\n");
        }

        Long timeSpan = System.currentTimeMillis() - startTime;
        logBuilder.append(" TOOK:").append(timeSpan).append(" ms.");
        if (exception == null && timeSpan <= 1000L) {
            this.logger.info(logBuilder.toString());
        } else {
            this.logger.warn(logBuilder.toString());
        }

        return result;
    }

    public String getServletPath(HttpServletRequest request) {
        String[] servletPaths = request.getServletPath().split("/");
        return servletPaths[servletPaths.length - 1];
    }

    public Map skipServletPathKeys(String curServletPath, String skipServletPath, Map map, List<String> skipKeys) {
        if (!StringUtils.isEmpty(skipServletPath) && curServletPath.equals(skipServletPath)) {
            Map<String, Object> resultMap = new HashMap();
            resultMap.putAll(map);
            resultMap.forEach((key, value) -> {
                if (skipKeys.contains(key)) {
                    resultMap.put(key, "**");
                }

            });
            return resultMap;
        } else {
            return map;
        }
    }
}
