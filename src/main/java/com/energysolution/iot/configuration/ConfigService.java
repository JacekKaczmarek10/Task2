package com.energysolution.iot.configuration;

import com.energysolution.iot.exception.ErrorResponse;
import com.energysolution.iot.iotdevice.IoTDeviceEntity;
import com.energysolution.iot.iotdevice.IotDeviceRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigService {

    private final ConfigRepository configRepository;
    private final IotDeviceRepository ioTDeviceRepository;

    public static final String DOES_NOT_EXIST_MESSAGE = " does not exist";
    public static final String CONFIGURATION_ID_PREFIX = "Configuration with id ";
    public static final String DEVICE_ID_PREFIX = "Device with id ";

    ResponseEntity<Object> createConfiguration(final String deviceId, final String configuration) {
        final var iotDevice = ioTDeviceRepository.findByDeviceId(deviceId);
        if (iotDevice.isEmpty()) {
            return handleCheckExistingObjectException(new IllegalArgumentException(
                DEVICE_ID_PREFIX + deviceId + DOES_NOT_EXIST_MESSAGE));
        }
        final var newConfiguration = saveConfig(iotDevice.get(), deviceId, configuration);
        return ResponseEntity.ok(new ConfigResponse(newConfiguration));
    }

    ConfigEntity saveConfig(IoTDeviceEntity iotDevice, String deviceId, String configuration){
        final var newConfiguration = new ConfigEntity();
        newConfiguration.setDeviceId(deviceId);
        newConfiguration.setDevice(Objects.requireNonNull(iotDevice));
        newConfiguration.setConfiguration(configuration);
        return configRepository.save(newConfiguration);
    }

    ResponseEntity<Object> getConfiguration(final Long configId) {
        final var config = configRepository.findById(configId);
        if (config.isEmpty()){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                CONFIGURATION_ID_PREFIX + configId + DOES_NOT_EXIST_MESSAGE));
        }
        return ResponseEntity.ok(config.get());
    }

    ResponseEntity<Object> updateConfiguration(final Long configId, final String newConfiguration) {
        final var config = configRepository.findById(configId);
        if (config.isEmpty()){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                CONFIGURATION_ID_PREFIX + configId + DOES_NOT_EXIST_MESSAGE));
        }
        return ResponseEntity.ok(new ConfigurationResponse(saveUpdatedConfig(newConfiguration, config.get())));
    }

    ConfigEntity saveUpdatedConfig(String newConfiguration, ConfigEntity config){
        config.setConfiguration(newConfiguration);
        config.setModifiedAt(LocalDateTime.now());
        return configRepository.save(config);
    }

    ResponseEntity<Object> deleteConfiguration(Long configId) {
        final var config = configRepository.findById(configId);
        if (config.isEmpty()){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                CONFIGURATION_ID_PREFIX + configId + DOES_NOT_EXIST_MESSAGE));
        }
        configRepository.deleteById(configId);
        return ResponseEntity.ok().build();
    }

    ResponseEntity<Object> handleCheckExistingObjectException(IllegalArgumentException e) {
        final var errorMessage = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
    }
}