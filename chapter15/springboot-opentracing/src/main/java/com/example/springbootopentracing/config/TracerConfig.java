package com.example.springbootopentracing.config;

import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfig {

    @Bean
    public Tracer initTracer() {
        io.jaegertracing.Configuration.SamplerConfiguration samplerConfiguration = new io.jaegertracing.Configuration.SamplerConfiguration().withType("const").withParam(1);
        io.jaegertracing.Configuration.ReporterConfiguration reporterConfiguration = new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true);
        return new io.jaegertracing.Configuration("apm-tracer").withSampler(samplerConfiguration).withReporter(reporterConfiguration).getTracer();
    }
}
