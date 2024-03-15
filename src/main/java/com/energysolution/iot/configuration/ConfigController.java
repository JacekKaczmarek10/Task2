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
public class ConfigController {

    private final ConfigService configService;

    @PostMapping
    public ResponseEntity<Object> createConfiguration(@RequestBody @Valid @NotNull ConfigurationRequest configurationRequest) {
        return configService.createConfiguration(configurationRequest.deviceId(),
                                                                          configurationRequest.configuration());
    }

    @GetMapping("/{configId}")
    public ResponseEntity<Object> getConfiguration(@PathVariable Long configId) {
        return configService.getConfiguration(configId);
    }

    @PutMapping("/{configId}")
    public ResponseEntity<Object> updateConfiguration(@PathVariable Long configId, @RequestBody String newConfiguration) {
        return configService.updateConfiguration(configId, newConfiguration);
    }

    @DeleteMapping("/{configId}")
    public ResponseEntity<Object> deleteConfiguration(@PathVariable Long configId) {
       return configService.deleteConfiguration(configId);
    }

}