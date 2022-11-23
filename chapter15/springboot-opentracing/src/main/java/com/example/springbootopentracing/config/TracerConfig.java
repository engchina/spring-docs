package com.example.springbootopentracing.config;

import com.example.springbootopentracing.manager.CustomizedMDCScopeManager;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.LoggingReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.jaegertracing.spi.Reporter;
import io.jaegertracing.spi.Sampler;
import io.opentracing.ScopeManager;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TracerConfig {

//    @Bean
//    public Tracer initTracer() {
//        log.info("### in TracerConfig.initTracer() ###");
////        io.jaegertracing.Configuration.SamplerConfiguration samplerConfiguration = new io.jaegertracing.Configuration.SamplerConfiguration().withType("const").withParam(1);
////        io.jaegertracing.Configuration.SenderConfiguration senderConfiguration = new io.jaegertracing.Configuration.SenderConfiguration().withAgentHost("localhost").withAgentPort(6831);
////        io.jaegertracing.Configuration.ReporterConfiguration reporterConfiguration = new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true).withSender(senderConfiguration);
////        Tracer tracer = new io.jaegertracing.Configuration("customized-tracer").withSampler(samplerConfiguration).withReporter(reporterConfiguration).getTracer();
////        return tracer;
//        Sampler constSampler = new ConstSampler(true);
//        Sampler probabilisticSampler = new ProbabilisticSampler(1);
//        Reporter reporter = new LoggingReporter();
//        ScopeManager scopeManager = new CustomizedMDCScopeManager.Builder().build();
//        JaegerTracer tracer =
//                new JaegerTracer.Builder("\"customized-tracer\"").withSampler(constSampler).withSampler(probabilisticSampler).withReporter(reporter).withScopeManager(scopeManager).build();
//        return tracer;
//    }
}
