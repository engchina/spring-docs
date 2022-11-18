package com.oracle.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "tracing")
@Data
public class TracingProperty {

    Boolean enabled;

    String name;

    String service;

    String dataUploadEndpoint;

    String privateDataKey;

    Boolean collectMetrics;

    Boolean collectResources;

    String logDirectory;

    Map<String, Object> tags;

    Map<String, String> properties;
}
