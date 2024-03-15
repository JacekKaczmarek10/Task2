package com.energysolution.iot.configuration;

import java.time.LocalDateTime;

public record UpdatedConfigurationResponse(String configuration, LocalDateTime createdAt) {

    public UpdatedConfigurationResponse(ConfigurationEntity configurationEntity) {
        this(configurationEntity.getConfiguration(), configurationEntity.getCreatedAt());
    }
}
