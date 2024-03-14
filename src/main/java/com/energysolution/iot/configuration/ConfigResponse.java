package com.energysolution.iot.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ConfigResponse(
    Long id,
    String deviceId,
    Long device,
    String configuration,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime modifiedAt
) {
    public ConfigResponse(ConfigEntity configEntity) {
        this(
            configEntity.getId(),
            configEntity.getDeviceId(),
            configEntity.getDevice().getId(),
            configEntity.getConfiguration(),
            configEntity.getCreatedAt(),
            configEntity.getModifiedAt()
        );
    }
}