package com.energysolution.iot.configuration;

import java.time.LocalDateTime;

public record ConfigurationResponse(String configuration, LocalDateTime createdAt) {

    public ConfigurationResponse(ConfigEntity configEntity) {
        this(configEntity.getConfiguration(), configEntity.getCreatedAt());
    }
}
