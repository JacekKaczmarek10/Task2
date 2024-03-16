package com.energysolution.iot.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ConfigurationControllerLoggingAspectTest {

    @InjectMocks
    private ConfigurationController configurationController;

    @Mock
    private ConfigurationService configurationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class LoggingCreateConfigurationTests {

        private final String deviceId = "testDeviceId";
        private String configuration = "testConfiguration";

        @Test
        public void shouldLogCreateConfiguration() {
            final var expectedResponse = ResponseEntity.ok().build();
            when(configurationService.createConfiguration(deviceId, configuration)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.createConfiguration(new ConfigurationRequest(deviceId, configuration));

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }

        @Test
        public void shouldLogCreateConfiguration_ExceedMaxConfigLength() {
            configuration = "a".repeat(10001);
            final var expectedResponse = ResponseEntity.ok().build();
            when(configurationService.createConfiguration(deviceId, configuration)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.createConfiguration(new ConfigurationRequest(deviceId, configuration));

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }
    }

    @Nested
    class LoggingGetConfigurationTests {

        private Long configId = 1L;

        @Test
        public void shouldLogGetConfiguration() {
            final var expectedResponse = ResponseEntity.ok().build();
            when(configurationService.getConfiguration(configId)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.getConfiguration(configId);

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }

        @Test
        public void shouldLogGetConfiguration_NonExistentConfigId() {
            final var expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            when(configurationService.getConfiguration(configId)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.getConfiguration(configId);

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }
    }

    @Nested
    class LoggingUpdateConfigurationTests {

        private Long configId = 1L;

        private String configuration = "newTestConfiguration";

        @Test
        public void shouldLogUpdateConfiguration() {
            final var expectedResponse = ResponseEntity.ok().build();
            when(configurationService.updateConfiguration(configId, configuration)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.updateConfiguration(configId, configuration);

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }

        @Test
        public void shouldLogUpdateConfiguration_NonExistentConfigId() {
            final var expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            when(configurationService.updateConfiguration(configId, configuration)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.updateConfiguration(configId, configuration);

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }
    }

    @Nested
    class LoggingDeleteConfigurationTests {

        private Long configId = 1L;

        @Test
        public void shouldLogDeleteConfiguration() {
            final var expectedResponse = ResponseEntity.ok().build();
            when(configurationService.deleteConfiguration(configId)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.deleteConfiguration(configId);

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }

        @Test
        public void shouldLogDeleteConfiguration_NonExistentConfigId() {
            final var expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            when(configurationService.deleteConfiguration(configId)).thenReturn(expectedResponse);

            final var actualResponse = configurationController.deleteConfiguration(configId);

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }
    }
}
