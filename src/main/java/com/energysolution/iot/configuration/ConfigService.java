package com.energysolution.iot.configuration;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigService {

    private final ConfigRepository configRepository;
    private final IotDeviceRepository ioTDeviceRepository;

    private static final String DOES_NOT_EXIST_MESSAGE = " does not exist";

    public ConfigEntity createConfiguration(String deviceId, String configuration) {
        checkDeviceId(deviceId);

        ConfigEntity newConfiguration = new ConfigEntity();
        newConfiguration.setDeviceId(deviceId);
        newConfiguration.setConfiguration(configuration);
        newConfiguration.setCreatedAt(LocalDateTime.now());
        newConfiguration.setModifiedAt(LocalDateTime.now());
        return configRepository.save(newConfiguration);
    }

    public ConfigEntity getConfiguration(Long configId) {
        Optional<ConfigEntity> optionalConfig = configRepository.findById(configId);
        return optionalConfig.orElse(null);
    }

    public ConfigEntity updateConfiguration(Long configId, String newConfiguration) {
        Optional<ConfigEntity> optionalConfig = configRepository.findById(configId);
        if (optionalConfig.isPresent()) {
            ConfigEntity configEntity = optionalConfig.get();
            configEntity.setConfiguration(newConfiguration);
            configEntity.setModifiedAt(LocalDateTime.now());
            return configRepository.save(configEntity);
        } else {
            throw new IllegalArgumentException("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE);
        }
    }

    public void deleteConfiguration(Long configId) {
        if (configRepository.existsById(configId)) {
            configRepository.deleteById(configId);
        } else {
            throw new IllegalArgumentException("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE);
        }
    }

    private void checkDeviceId(String deviceId) {
        if (!ioTDeviceRepository.existsByDeviceId(deviceId)) {
            throw new IllegalArgumentException("Device with id " + deviceId + DOES_NOT_EXIST_MESSAGE);
        }
    }
}
