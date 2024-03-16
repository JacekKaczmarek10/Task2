package com.energysolution.iot.configuration;

import static com.energysolution.iot.configuration.ConfigurationService.DOES_NOT_EXIST_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.energysolution.iot.iotdevice.IoTDeviceEntity;
import com.energysolution.iot.iotdevice.IotDeviceRepository;
import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ConfigurationServiceTest {

    @InjectMocks
    @Spy
    private ConfigurationService service;

    @Mock
    private IotDeviceRepository iotDeviceRepository;

    @Mock
    private ConfigurationRepository configurationRepository;

    @Nested
    class CreateConfigurationTest {

        private final String deviceId = "deviceId";
        private String configuration = "configuration";
        private final IoTDeviceEntity deviceEntity = new IoTDeviceEntity();
        private final ConfigurationEntity configurationEntity = new ConfigurationEntity();

        @BeforeEach
        void setUp() {
            deviceEntity.setId(1L);
            configurationEntity.setDeviceKey(deviceEntity);
        }

        @Test
        void shouldCallRepository() {
            callService();

            verify(iotDeviceRepository).findByDeviceId(deviceId);
        }

        @Test
        void shouldCallHandleCheckDeviceIdException() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(exception -> exception.getMessage()
                .equals("Device with id " + deviceId + DOES_NOT_EXIST_MESSAGE)));
        }

        @Test
        void shouldReturnBadRequestOnEmptyDevice() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldReturnBadRequestOnLimitExceeded() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.of(deviceEntity));
            configuration = "a".repeat(10001);

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldSaveConfig() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.of(deviceEntity));
            when(service.saveConfig(deviceEntity, deviceId, configuration)).thenReturn(configurationEntity);

            callService();

            verify(service).saveConfig(deviceEntity, deviceId, configuration);
        }

        @Test
        void shouldReturnOk() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.of(deviceEntity));
            when(service.saveConfig(deviceEntity, deviceId, configuration)).thenReturn(configurationEntity);

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        private ResponseEntity<Object> callService() {
            return service.createConfiguration(deviceId, configuration);
        }
    }

    @Nested
    class SaveConfigTest {

        private final String deviceId = "deviceId";
        private final String configuration = "configuration";
        private final IoTDeviceEntity deviceEntity = new IoTDeviceEntity();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configurationRepository).save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)));
        }

        @Test
        void shouldReturnConfigEntity() {
            when(configurationRepository.save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)))).thenReturn(new ConfigurationEntity() {{
                setDeviceId(deviceId);
                setConfiguration(configuration);
            }});

            final var configEntity = callService();

            assertThat(configEntity.getDeviceId()).isEqualTo(deviceId);
            assertThat(configEntity.getConfiguration()).isEqualTo(configuration);
        }

        private ConfigurationEntity callService() {
            return service.saveConfig(deviceEntity, deviceId, configuration);
        }
    }

    @Nested
    class GetConfigurationTest {

        private final Long configId = 1L;
        private final ConfigurationEntity configurationEntity = ConfigurationEntityTestFactory.create();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configurationRepository).findById(configId);
        }

        @Test
        void shouldCallHandleCheckConfigIdException() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(exception -> exception.getMessage()
                .equals("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE)));
        }

        @Test
        void shouldReturnBadRequest() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldReturnOk() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.of(configurationEntity));

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void shouldReturnResponse() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.of(configurationEntity));

            final var responseEntity = callService();

            assertThat(responseEntity.getBody()).isEqualTo(GetConfigurationResponseTestFactory.create());
        }

        private ResponseEntity<Object> callService() {
            return service.getConfiguration(configId);
        }
    }

    @Nested
    class UpdateConfigurationTest {

        private final Long configId = 1L;
        private final String configuration = "configuration";
        private ConfigurationEntity configurationEntity = ConfigurationEntityTestFactory.create();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configurationRepository).findById(configId);
        }

        @Test
        void shouldCallHandleCheckConfigIdException() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(
                exception -> exception.getMessage().equals("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE)));
        }

        @Test
        void shouldReturnBadRequest() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldCallSaveUpdatedConfig() {
            configurationEntity.setConfiguration("oldConfiguration");
            configurationEntity.setModifiedAt(LocalDateTime.now().minusDays(1));
            when(configurationRepository.findById(configId)).thenReturn(Optional.of(configurationEntity));
            when(service.saveUpdatedConfig(configuration, configurationEntity)).thenReturn(configurationEntity);

            callService();

            verify(service).saveUpdatedConfig(configuration, configurationEntity);
        }

        @Test
        void shouldReturnOk() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.of(configurationEntity));
            when(service.saveUpdatedConfig(configuration, configurationEntity)).thenReturn(configurationEntity);

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void shouldReturnResponse() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.of(configurationEntity));
            when(service.saveUpdatedConfig(configuration, configurationEntity)).thenReturn(configurationEntity);

            final var responseEntity = callService();

            assertThat(responseEntity.getBody()).usingRecursiveComparison().ignoringFields("modifiedAt").isEqualTo(ConfigurationResponseTestFactory.create());
        }

        private ResponseEntity<Object> callService() {
            return service.updateConfiguration(configId, configuration);
        }
    }

    @Nested
    class SaveUpdatedConfigTest {

        private final String deviceId = "deviceId";
        private final String configuration = "configuration";
        private final ConfigurationEntity configurationEntity = new ConfigurationEntity();
        private final IoTDeviceEntity ioTDeviceEntity = new IoTDeviceEntity();

        @BeforeEach
        void setUp() {
            configurationEntity.setDeviceKey(ioTDeviceEntity);
            configurationEntity.setDeviceId(deviceId);
        }

        @Test
        void shouldCallRepository() {
            callService();

            verify(configurationRepository).save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)));
        }

        @Test
        void shouldReturnUpdatedConfigEntity() {
            when(configurationRepository.save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)))).thenReturn(new ConfigurationEntity() {{
                setDeviceId(deviceId);
                setConfiguration(configuration);
            }});

            final var configEntity = callService();

            assertThat(configEntity.getDeviceId()).isEqualTo(deviceId);
            assertThat(configEntity.getConfiguration()).isEqualTo(configuration);
        }

        private ConfigurationEntity callService() {
            return service.saveUpdatedConfig(configuration, configurationEntity);
        }
    }

    @Nested
    class DeleteConfigurationTest {

        private final Long configId = 1L;
        private final ConfigurationEntity configurationEntity = new ConfigurationEntity();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configurationRepository).findById(configId);
        }

        @Test
        void shouldCallHandleCheckConfigIdException() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(exception -> exception.getMessage()
                .equals("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE)));
        }


        @Test
        void shouldReturnBadRequest() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldReturnOk() {
            when(configurationRepository.findById(configId)).thenReturn(Optional.of(configurationEntity));

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        private ResponseEntity<Object> callService() {
            return service.deleteConfiguration(configId);
        }

    }

    @Nested
    class HandleCheckExistingObjectExceptionTest {

        @Test
        void shouldReturnResponseEntity() {
            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        private ResponseEntity<Object> callService() {
            return service.handleCheckExistingObjectException(new IllegalArgumentException());
        }
    }
}
