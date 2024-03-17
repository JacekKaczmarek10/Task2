package com.energysolution.iot.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateConfigurationRequestTestFactory {

    public static final String DEVICE_ID = "TestDevice";
    public static final String CONFIGURATION = "configuration";

    public static UpdateConfigurationRequest create() {
        return new UpdateConfigurationRequest(DEVICE_ID, CONFIGURATION);
    }

    public static UpdateConfigurationRequest create(String deviceId, String configuration) {
        return new UpdateConfigurationRequest(deviceId, configuration);
    }
}
