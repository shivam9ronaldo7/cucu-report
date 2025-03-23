package com.cucu.report.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.cucu.report.annotations",
        "com.cucu.report.file",
        "com.cucu.report.configuration",
        "com.cucu.report.envelope"})
@PropertySource(value = {"classpath:cucu.properties"})
public class CucuConfiguration {
}
