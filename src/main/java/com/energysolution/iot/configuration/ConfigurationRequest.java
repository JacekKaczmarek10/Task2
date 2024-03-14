package com.energysolution.iot.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
@NotNull
public record ConfigurationRequest(
    @NotBlank(message = "deviceId must not be blank") @NotNull String deviceId,
    @NotNull String configuration
) {}