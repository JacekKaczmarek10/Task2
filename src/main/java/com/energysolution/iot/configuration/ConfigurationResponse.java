package com.energysolution.iot.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ConfigurationResponse(
    Long id,
    String deviceId,
    Long device,
    String configuration,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime modifiedAt
) {
    public ConfigurationResponse(ConfigurationEntity configurationEntity) {
        this(
            configurationEntity.getId(),
            configurationEntity.getDeviceId(),
            configurationEntity.getDeviceKey().getId(),
            configurationEntity.getConfiguration(),
            configurationEntity.getCreatedAt(),
            configurationEntity.getModifiedAt()
        );
    }
}
