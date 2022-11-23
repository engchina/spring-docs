package com.example.springbootjaeger.config;

import io.jaegertracing.internal.metrics.Metrics;
import io.jaegertracing.internal.reporters.CompositeReporter;
import io.jaegertracing.internal.reporters.LoggingReporter;
import io.jaegertracing.spi.Reporter;
import io.jaegertracing.spi.Sender;
import io.opentracing.contrib.java.spring.jaeger.starter.JaegerAutoConfiguration;
import io.opentracing.contrib.java.spring.jaeger.starter.JaegerConfigurationProperties;
import io.opentracing.contrib.java.spring.jaeger.starter.ReporterAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ConditionalOnClass(io.jaegertracing.internal.JaegerTracer.class)
@ConditionalOnMissingBean(io.opentracing.Tracer.class)
@ConditionalOnProperty(value = "opentracing.jaeger.reporter.enabled", havingValue = "false", matchIfMissing = true)
@AutoConfigureBefore(JaegerAutoConfiguration.class)
@EnableConfigurationProperties({PropagateJaegerProperties.class, JaegerConfigurationProperties.class})
public class PropagateJaegerConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public Reporter reporter(JaegerConfigurationProperties properties,
                             Metrics metrics,
                             @Autowired(required = false) ReporterAppender reporterAppender) {

        List<Reporter> reporters = new LinkedList<>();
        JaegerConfigurationProperties.RemoteReporter remoteReporter = properties.getRemoteReporter();

        JaegerConfigurationProperties.HttpSender httpSender = properties.getHttpSender();
        if (StringUtils.hasLength(httpSender.getUrl())) {
            reporters.add(getHttpReporter(metrics, remoteReporter, httpSender));
        } else {
            reporters.add(getUdpReporter(metrics, remoteReporter, properties.getUdpSender()));
        }

        if (properties.isLogSpans()) {
            reporters.add(new LoggingReporter());
        }

        if (reporterAppender != null) {
            reporterAppender.append(reporters);
        }

        return new CompositeReporter(reporters.toArray(new Reporter[reporters.size()]));
    }

    private Reporter getUdpReporter(Metrics metrics,
                                    JaegerConfigurationProperties.RemoteReporter remoteReporter,
                                    JaegerConfigurationProperties.UdpSender udpSenderProperties) {
        io.jaegertracing.thrift.internal.senders.UdpSender udpSender =
                new io.jaegertracing.thrift.internal.senders.UdpSender(
                        udpSenderProperties.getHost(), udpSenderProperties.getPort(),
                        udpSenderProperties.getMaxPacketSize());

        return createReporter(metrics, remoteReporter, udpSender);
    }

    private Reporter getHttpReporter(Metrics metrics,
                                     JaegerConfigurationProperties.RemoteReporter remoteReporter,
                                     JaegerConfigurationProperties.HttpSender httpSenderProperties) {
        io.jaegertracing.thrift.internal.senders.HttpSender.Builder builder =
                new io.jaegertracing.thrift.internal.senders.HttpSender.Builder(httpSenderProperties.getUrl());
        if (httpSenderProperties.getMaxPayload() != null) {
            builder = builder.withMaxPacketSize(httpSenderProperties.getMaxPayload());
        }
        if (!StringUtils.isEmpty(httpSenderProperties.getUsername())
                && !StringUtils.isEmpty(httpSenderProperties.getPassword())) {
            builder.withAuth(httpSenderProperties.getUsername(), httpSenderProperties.getPassword());
        } else if (!StringUtils.isEmpty(httpSenderProperties.getAuthToken())) {
            builder.withAuth(httpSenderProperties.getAuthToken());
        }

        return createReporter(metrics, remoteReporter, builder.build());
    }

    private Reporter createReporter(Metrics metrics,
                                    JaegerConfigurationProperties.RemoteReporter remoteReporter, Sender udpSender) {
        io.jaegertracing.internal.reporters.RemoteReporter.Builder builder =
                new io.jaegertracing.internal.reporters.RemoteReporter.Builder()
                        .withSender(udpSender)
                        .withMetrics(metrics);

        if (remoteReporter.getFlushInterval() != null) {
            builder.withFlushInterval(remoteReporter.getFlushInterval());
        }
        if (remoteReporter.getMaxQueueSize() != null) {
            builder.withMaxQueueSize(remoteReporter.getMaxQueueSize());
        }

        return builder.build();
    }

//    @ConditionalOnMissingBean
//    @Bean
//    public Reporter reporter(@Autowired(required = false) ReporterAppender reporterAppender) {
//
//        List<Reporter> reporters = new LinkedList<>();
//        if (reporterAppender != null) {
//            reporterAppender.append(reporters);
//        }
//
//        return new CompositeReporter(reporters.toArray(new Reporter[reporters.size()]));
//    }
}
