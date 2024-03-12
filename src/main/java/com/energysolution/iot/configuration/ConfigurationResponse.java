package com.energysolution.iot.configuration;

import java.time.LocalDateTime;

public record ConfigurationResponse(Long id, String deviceId, String configuration, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
