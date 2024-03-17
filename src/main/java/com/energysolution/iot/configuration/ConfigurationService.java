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

/**
 * Service class for configuration operations.
 */
@Service
@AllArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;
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
        if(configuration.length() > 10000){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                "Configuration length exceeds 10000 characters"));
        }
        final var newConfiguration = saveConfig(iotDevice.get(), deviceId, configuration);
        return ResponseEntity.ok(new ConfigurationResponse(newConfiguration));
    }

    ConfigurationEntity saveConfig(IoTDeviceEntity iotDevice, String deviceId, String configuration){
        final var newConfiguration = new ConfigurationEntity();
        newConfiguration.setDeviceId(deviceId);
        newConfiguration.setDeviceKey(Objects.requireNonNull(iotDevice));
        newConfiguration.setConfiguration(configuration);
        return configurationRepository.save(newConfiguration);
    }

    ResponseEntity<Object> getConfiguration(final Long configId) {
        final var config = configurationRepository.findById(configId);
        if (config.isEmpty()){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                CONFIGURATION_ID_PREFIX + configId + DOES_NOT_EXIST_MESSAGE));
        }
        return ResponseEntity.ok(new GetConfigurationResponse(config.get()));
    }

    ResponseEntity<Object> updateConfiguration(final Long configId, final UpdateConfigurationRequest updateConfigurationRequest) {
        final var config = configurationRepository.findById(configId);
        if(updateConfigurationRequest.configuration().length() > 10000){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                "Configuration length exceeds 10000 characters."));
        }
        if (config.isEmpty()){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                CONFIGURATION_ID_PREFIX + configId + DOES_NOT_EXIST_MESSAGE));
        }
        final var iotDevice = ioTDeviceRepository.findByDeviceId(updateConfigurationRequest.deviceId());
        if (iotDevice.isEmpty()) {
            return handleCheckExistingObjectException(new IllegalArgumentException(
                DEVICE_ID_PREFIX + updateConfigurationRequest.deviceId() + DOES_NOT_EXIST_MESSAGE));
        }
        return ResponseEntity.ok(new ConfigurationResponse(saveUpdatedConfig(updateConfigurationRequest, config.get(), iotDevice.get())));
    }

    ConfigurationEntity saveUpdatedConfig(final UpdateConfigurationRequest updateConfigurationRequest, ConfigurationEntity config, IoTDeviceEntity iotDevice){
        config.setConfiguration(updateConfigurationRequest.configuration());
        config.setDeviceKey(iotDevice);
        config.setModifiedAt(LocalDateTime.now());
        return configurationRepository.save(config);
    }

    ResponseEntity<Object> deleteConfiguration(Long configId) {
        final var config = configurationRepository.findById(configId);
        if (config.isEmpty()){
            return handleCheckExistingObjectException(new IllegalArgumentException(
                CONFIGURATION_ID_PREFIX + configId + DOES_NOT_EXIST_MESSAGE));
        }
        configurationRepository.deleteById(configId);
        return ResponseEntity.ok().build();
    }

    ResponseEntity<Object> handleCheckExistingObjectException(IllegalArgumentException e) {
        final var errorMessage = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
    }
}
