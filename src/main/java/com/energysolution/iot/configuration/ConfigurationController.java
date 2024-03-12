package com.energysolution.iot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping
    public ConfigurationResponse createConfiguration(@RequestBody ConfigurationRequest request) {
        return configurationService.createConfiguration(request.deviceId(), request.configuration());
    }

    @GetMapping("/{id}")
    public ConfigurationResponse readConfiguration(@PathVariable Long id) {
        return configurationService.readConfiguration(id);
    }

    @PutMapping("/{id}")
    public ConfigurationResponse updateConfiguration(@PathVariable Long id, @RequestBody ConfigurationRequest request) {
        return configurationService.updateConfiguration(id, request.configuration());
    }

    @DeleteMapping("/{id}")
    public void deleteConfiguration(@PathVariable Long id) {
        configurationService.deleteConfiguration(id);
    }
}