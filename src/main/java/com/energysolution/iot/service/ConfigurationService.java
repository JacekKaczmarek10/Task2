package com.energysolution.iot.service;

import com.energysolution.iot.dto.ConfigurationResponse;
import com.energysolution.iot.model.ConfigurationEntity;
import com.energysolution.iot.repository.ConfigurationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;


    public ConfigurationResponse createConfiguration(String deviceId, String configuration) {
        ConfigurationEntity entity = new ConfigurationEntity(null, deviceId, configuration, LocalDateTime.now(), LocalDateTime.now());
        ConfigurationEntity savedEntity = configurationRepository.save(entity);
        return mapEntityToResponse(savedEntity);
    }

    public ConfigurationResponse readConfiguration(Long id) {
        return configurationRepository.findById(id)
            .map(this::mapEntityToResponse)
            .orElse(null);
    }

    public ConfigurationResponse updateConfiguration(Long id, String configuration) {
        return configurationRepository.findById(id)
            .map(entity -> {
                entity.setConfiguration(configuration);
                entity.setModifiedAt(LocalDateTime.now());
                ConfigurationEntity updatedEntity = configurationRepository.save(entity);
                return mapEntityToResponse(updatedEntity);
            })
            .orElse(null);
    }

    public void deleteConfiguration(Long id) {
        configurationRepository.deleteById(id);
    }

    private ConfigurationResponse mapEntityToResponse(ConfigurationEntity entity) {
        return new ConfigurationResponse(entity.getId(), entity.getDeviceId(), entity.getConfiguration(), entity.getCreatedAt(), entity.getModifiedAt());
    }
}