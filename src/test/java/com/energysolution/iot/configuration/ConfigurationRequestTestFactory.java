package com.energysolution.iot.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationRequestTestFactory {

    public static final String VALID_DEVICE_ID = "device1";
    public static final String VALID_CONFIGURATION = "validConfiguration";

    public static ConfigurationRequest create() {
        return new ConfigurationRequest(VALID_DEVICE_ID, VALID_CONFIGURATION);
    }
}
