package com.energysolution.iot.configuration;

public record UpdateConfigurationRequest(
    String deviceId,
    String configuration
) {

    public UpdateConfigurationRequest withConfiguration(String newConfiguration) {
        return new UpdateConfigurationRequest(this.deviceId, newConfiguration);
    }
}
