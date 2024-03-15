package com.energysolution.iot.configuration;

import java.time.LocalDateTime;

public class ConfigurationResponseTestFactory {

    public static final String CONFIGURATION = "configuration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2024,10,10,10,10);

    public static UpdatedConfigurationResponse create() {
        return new UpdatedConfigurationResponse(CONFIGURATION, CREATED_AT);
    }
}