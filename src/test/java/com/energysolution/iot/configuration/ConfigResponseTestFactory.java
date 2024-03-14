package com.energysolution.iot.configuration;

import java.time.LocalDateTime;

public class ConfigResponseTestFactory {

    public static final Long ID = 1L;
    public static final String DEVICE_ID = "TestDevice";
    public static final Long DEVICE = 1L;
    public static final String CONFIGURATION = "TestConfiguration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.now();
    public static final LocalDateTime MODIFIED_AT = LocalDateTime.now();

    public static ConfigResponse create() {
        return new ConfigResponse(
            ID,
            DEVICE_ID,
            DEVICE,
            CONFIGURATION,
            CREATED_AT,
            MODIFIED_AT
        );
    }

}