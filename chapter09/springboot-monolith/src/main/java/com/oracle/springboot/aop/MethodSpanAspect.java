package com.oracle.springboot.aop;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@Slf4j
public class MethodSpanAspect {

    @Resource
    Tracer tracer;

    /**
     * 取得真实方法的相关信息
     *
     * @param proceedingJoinPoint
     * @return
     */
    private String getOperationDesc(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        // 提取类名
        return StringUtils.substringAfterLast(signature.getDeclaringTypeName(), ".") + ":" + signature.getName();
    }

    @Around("@annotation(com.oracle.springboot.annotation.MethodSpan)")
    public Object traceChildSpan(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("=====Aspect处理=======");
        Object object = null;
//        Object[] args = proceedingJoinPoint.getArgs();
//        for (Object arg : args) {
//            log.info("参数为:" + arg);
//        }
        long start = System.currentTimeMillis();
        // 类名:方法名
        String operationDesc = getOperationDesc(proceedingJoinPoint);

        // 从上下文中取得已存在的span
        Span parentSpan = tracer.activeSpan();

        if (null == parentSpan) {
            log.error("can not get span from context");
        } else {
            Span span = tracer.buildSpan(operationDesc).asChildOf(parentSpan).start();
            try (Scope scope = tracer.scopeManager().activate(span)) {
                MDC.put("X-MS-TenantId", operationDesc);
                MDC.put("X-MS-ActorId", operationDesc);
                // span日志
//                span.log("child span log of " + operationDesc);

                // 增加一个tag
                span.setTag("child-operation-desc", operationDesc);
                object = proceedingJoinPoint.proceed();
            } finally {
                // span结束
                span.finish();
            }
        }

        // 相当于Filter 的 chain.doFilter()  调用被拦截的那个方法  返回值 object 与 controller中方法的返回值相同
//        log.info("Aspect 耗时:" + (System.currentTimeMillis() - start));
        return object;
    }
}
