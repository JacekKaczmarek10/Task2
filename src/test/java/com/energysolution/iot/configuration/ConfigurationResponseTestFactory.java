package com.energysolution.iot.configuration;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationResponseTestFactory {

    public static final Long ID = 1L;
    public static final String DEVICE_ID = "TestDevice";
    public static final Long DEVICE = 1L;
    public static final String CONFIGURATION = "configuration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2024,10,10,10,10);
    public static final LocalDateTime MODIFIED_AT = LocalDateTime.of(2024,10,10,10,10);

    public static ConfigurationResponse create() {
        return new ConfigurationResponse(
            ID,
            DEVICE_ID,
            DEVICE,
            CONFIGURATION,
            CREATED_AT,
            MODIFIED_AT
        );
    }

}
