/**
 * Copyright 2016-2019 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.opentracing.contrib.spring.web.starter;

import io.opentracing.Tracer;
import io.opentracing.contrib.spring.tracer.configuration.TracerAutoConfiguration;
import io.opentracing.contrib.spring.web.webfilter.TracingWebFilter;
import io.opentracing.contrib.spring.web.webfilter.WebFluxSpanDecorator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Instrumentation of WebFlux.
 *
 * @author Csaba Kos
 * @author Gilles Robert
 */
@Configuration
@ConditionalOnBean(Tracer.class)
@AutoConfigureAfter({TracerAutoConfiguration.class, SkipPatternAutoConfiguration.class})
@EnableConfigurationProperties(WebTracingProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnProperty(name = WebTracingProperties.CONFIGURATION_PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class WebFluxTracingAutoConfiguration {

    @ConditionalOnMissingBean(WebFluxSpanDecorator.class)
    @Configuration
    static class DefaultWebFluxSpanDecorators {
        @Bean
        WebFluxSpanDecorator standardTagsWebFluxSpanDecorator() {
            return new WebFluxSpanDecorator.StandardTags();
        }

        @Bean
        WebFluxSpanDecorator webFluxTagsWebFluxSpanDecorator() {
            return new WebFluxSpanDecorator.WebFluxTags();
        }
    }

    @Bean
    @ConditionalOnMissingBean(TracingWebFilter.class)
    public TracingWebFilter traceFilter(
            final Tracer tracer,
            final WebTracingProperties webTracingProperties,
            final ObjectProvider<List<WebFluxSpanDecorator>> webFilterSpanDecorators,
            final @Qualifier("skipPattern") Pattern skipPattern
    ) {
        return new TracingWebFilter(
                tracer,
                webTracingProperties.getOrder(),
                skipPattern,
                webTracingProperties.getUrlPatterns(),
                webFilterSpanDecorators.getObject()
        );
    }
}
