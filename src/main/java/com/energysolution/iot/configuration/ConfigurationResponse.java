package com.energysolution.iot.configuration;


import java.time.LocalDateTime;

public record ConfigurationResponse(
    String configuration,
    LocalDateTime createdAt
) {

    public ConfigurationResponse(ConfigurationEntity configurationEntity) {
        this(
            configurationEntity.getConfiguration(),
            configurationEntity.getCreatedAt()
        );
    }

}
