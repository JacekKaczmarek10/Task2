package com.energysolution.iot.configuration;

import java.time.LocalDateTime;

public class ConfigEntityTestFactory {

    public static final Long ID = 1L;
    public static final String DEVICE_ID = "TestDevice";
    public static final String CONFIGURATION = "TestConfiguration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2024,10,10,10,10);
    public static final LocalDateTime MODIFIED_AT = LocalDateTime.of(2024,10,10,10,10);

    public static ConfigEntity create() {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setId(ID);
        configEntity.setDeviceId(DEVICE_ID);
        configEntity.setConfiguration(CONFIGURATION);
        configEntity.setCreatedAt(CREATED_AT);
        configEntity.setModifiedAt(MODIFIED_AT);
        return configEntity;
    }
}
