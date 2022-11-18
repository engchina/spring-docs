package com.japan.apm.common;

import com.oracle.apm.tracer.ApmTracer;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
public class ApmTracerConfig {

    @Resource
    TracingProperty tracingProperty;

    @Bean
    @Qualifier("apmtracer")
    public Tracer tracer() throws Exception {

        String tracerName = tracingProperty.getName();
        String serviceName = tracingProperty.getService();

        Map<String, Object> tags = tracingProperty.getTags();
        Boolean tag1 = (Boolean) tags.get("tag1");
        System.out.println(tag1);

        Map<String, String> properties = tracingProperty.getProperties();
        properties.keySet().forEach(o -> {
            System.out.println(o);
        });

        Tracer tracer = new ApmTracer.Builder(tracerName, serviceName)
                .withDataUploadEndpoint(tracingProperty.getDataUploadEndpoint())
                .withDataUploadKey(tracingProperty.getPrivateDataKey())
                .withTags(tracingProperty.getTags())
                .withLogDirectory(tracingProperty.getLogDirectory())
                .withProperties(tracingProperty.getProperties())
                .withCollectMetrics(tracingProperty.getCollectMetrics())
                .withCollectResources(tracingProperty.getCollectMetrics())
                .build();
        return tracer;
    }
}
