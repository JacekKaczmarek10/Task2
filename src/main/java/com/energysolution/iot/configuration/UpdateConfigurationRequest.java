package com.energysolution.iot.configuration;

public record UpdateConfigurationRequest(
    String deviceId,
    String configuration
) {

}
