package com.energysolution.iot.configuration;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationResponseTestFactory {

    public static final String CONFIGURATION = "configuration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2024,10,10,10,10);

    public static UpdatedConfigurationResponse create() {
        return new UpdatedConfigurationResponse(CONFIGURATION, CREATED_AT);
    }
}