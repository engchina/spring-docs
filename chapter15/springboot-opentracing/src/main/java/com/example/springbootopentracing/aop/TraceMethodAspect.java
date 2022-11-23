package com.example.springbootopentracing.aop;

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
public class TraceMethodAspect {

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

    @Around("@annotation(com.example.springbootopentracing.annotation.TraceMethod)")
    public Object traceChildSpan(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("=== Aspect处理开始 ===");
        Object object = null;
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            log.info("=== 参数为:" + arg + " ===");
        }
        long start = System.currentTimeMillis();
        // 类名:方法名
        String operationDesc = getOperationDesc(proceedingJoinPoint);

        // 从上下文中取得已存在的span
        Span parentSpan = tracer.activeSpan();
        Span span = null;

        if (null == parentSpan) {
            log.error("can not get span from context");
            // 创建一个span，在创建的时候就添加一个tag
            span = tracer.buildSpan(operationDesc).start();
        } else {
            span = tracer.buildSpan(operationDesc).asChildOf(parentSpan).start();
        }

        try (Scope scope = tracer.scopeManager().activate(span)) {
            MDC.put("X-MS-TenantId", operationDesc);
            MDC.put("X-MS-ActorId", operationDesc);

            // 相当于Filter 的 chain.doFilter()  调用被拦截的那个方法  返回值 object 与 controller中方法的返回值相同
            object = proceedingJoinPoint.proceed();
            // span日志
//                span.log("child span log of " + operationDesc);
            // 增加一个tag
            span.setTag("elapsed-time", System.currentTimeMillis() - start);
        } finally {
            // span结束
            span.finish();
        }

        log.info("=== Aspect 耗时:"+(System.currentTimeMillis()-start)+" ===");
        return object;
}
}
