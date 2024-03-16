package com.energysolution.iot.configuration;

import com.energysolution.iot.iotdevice.IoTDeviceEntity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationEntityTestFactory {

    public static final Long ID = 1L;
    public static final String DEVICE_ID = "TestDevice";
    public static final String CONFIGURATION = "TestConfiguration";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2024,10,10,10,10);
    public static final LocalDateTime MODIFIED_AT = LocalDateTime.of(2024,10,10,10,10);

    public static ConfigurationEntity create() {
        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        configurationEntity.setId(ID);
        configurationEntity.setDeviceId(DEVICE_ID);
        configurationEntity.setDeviceKey(IoTDeviceEntityTestFactory.create());
        configurationEntity.setConfiguration(CONFIGURATION);
        configurationEntity.setCreatedAt(CREATED_AT);
        configurationEntity.setModifiedAt(MODIFIED_AT);
        return configurationEntity;
    }
}
