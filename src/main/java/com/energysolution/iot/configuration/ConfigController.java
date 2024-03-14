package com.energysolution.iot.configuration;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configurations")
@AllArgsConstructor
public class ConfigController {

    private final ConfigService configService;

    @PostMapping
    public ResponseEntity<ConfigEntity> createConfiguration(@RequestBody ConfigurationRequest configurationRequest) {
        ConfigEntity newConfiguration = configService.createConfiguration(configurationRequest.deviceId(),
                                                                          configurationRequest.configuration());
        return ResponseEntity.status(HttpStatus.CREATED).body(newConfiguration);
    }

    @GetMapping("/{configId}")
    public ResponseEntity<ConfigEntity> getConfiguration(@PathVariable Long configId) {
        ConfigEntity configuration = configService.getConfiguration(configId);
        if (configuration != null) {
            return ResponseEntity.ok(configuration);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{configId}")
    public ResponseEntity<ConfigEntity> updateConfiguration(@PathVariable Long configId, @RequestBody String newConfiguration) {
        try {
            ConfigEntity updatedConfiguration = configService.updateConfiguration(configId, newConfiguration);
            return ResponseEntity.ok(updatedConfiguration);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{configId}")
    public ResponseEntity<Void> deleteConfiguration(@PathVariable Long configId) {
        try {
            configService.deleteConfiguration(configId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}