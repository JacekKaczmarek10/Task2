package com.energysolution.iot.configuration;


import java.time.LocalDateTime;

public record GetConfigurationResponse(
    String configuration,
    LocalDateTime createdAt
) {

    public GetConfigurationResponse(ConfigurationEntity configurationEntity) {
        this(
            configurationEntity.getConfiguration(),
            configurationEntity.getCreatedAt()
        );
    }

}
