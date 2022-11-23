package com.oracle.springboot.config;

import com.oracle.apm.tracer.ApmTracer;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

@Configuration
@Order(1)
public class ApmTracerConfig {

//    @Resource
//    TracingProperty tracingProperty;
//
//    @Bean
//    public Tracer tracer() throws Exception {
//
//        String tracerName = tracingProperty.getName();
//        String serviceName = tracingProperty.getService();
//
//        /*Map<String, Object> tags = tracingProperty.getTags();
//        Boolean tag1 = (Boolean) tags.get("tag1");
//        System.out.println(tag1);
//
//        Map<String, String> properties = tracingProperty.getProperties();
//        properties.keySet().forEach(o -> {
//            System.out.println(o);
//        });*/
//
//        Tracer tracer = new ApmTracer.Builder(tracerName, serviceName)
//                .withDataUploadEndpoint(tracingProperty.getDataUploadEndpoint())
//                .withDataUploadKey(tracingProperty.getPrivateDataKey())
//                .withLogDirectory(tracingProperty.getLogDirectory())
//                .withCollectMetrics(tracingProperty.getCollectMetrics())
//                .withCollectResources(tracingProperty.getCollectMetrics())
//                .withProperties(tracingProperty.getProperties())
//                .sampling("probabilistic", "1")
//                .withTags(tracingProperty.getTags())
//                .build();
//        GlobalTracer.registerIfAbsent(tracer);
//        return tracer;
//    }

}
