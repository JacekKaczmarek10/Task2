package com.energysolution.iot.configuration;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetConfigurationResponseTestFactory {

    public static final String CONFIGURATION = "TestConfiguration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2024,10,10,10,10);

    public static GetConfigurationResponse create() {
        return new GetConfigurationResponse(CONFIGURATION, CREATED_AT);
    }
}
