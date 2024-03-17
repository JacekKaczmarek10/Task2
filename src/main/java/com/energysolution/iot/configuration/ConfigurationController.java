package com.energysolution.iot.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for configuration operations.
 */
@RestController
@RequestMapping("/configurations")
@AllArgsConstructor
class ConfigurationController {

    private final ConfigurationService configurationService;

    @PostMapping
    ResponseEntity<Object> createConfiguration(@RequestBody @Valid @NotNull ConfigurationRequest configurationRequest) {
        return configurationService.createConfiguration(configurationRequest.deviceId(),
                                                        configurationRequest.configuration());
    }

    @GetMapping("/{configId}")
    ResponseEntity<Object> getConfiguration(@PathVariable Long configId) {
        return configurationService.getConfiguration(configId);
    }

    @PutMapping("/{configId}")
    ResponseEntity<Object> updateConfiguration(@PathVariable Long configId, @RequestBody UpdateConfigurationRequest configuration) {
        return configurationService.updateConfiguration(configId, configuration);
    }

    @DeleteMapping("/{configId}")
    ResponseEntity<Object> deleteConfiguration(@PathVariable Long configId) {
       return configurationService.deleteConfiguration(configId);
    }

}
