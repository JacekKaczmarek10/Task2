package com.energysolution.iot.configuration;

import static com.energysolution.iot.configuration.ConfigService.DOES_NOT_EXIST_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.energysolution.iot.iotdevice.IoTDeviceEntity;
import com.energysolution.iot.iotdevice.IotDeviceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
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
class ConfigServiceTest {

    @InjectMocks
    @Spy
    private ConfigService service;

    @Mock
    private IotDeviceRepository iotDeviceRepository;

    @Mock
    private ConfigRepository configRepository;

    @Nested
    class CreateConfigurationTest {

        private final String deviceId = "deviceId";
        private final String configuration = "configuration";
        private final IoTDeviceEntity deviceEntity = new IoTDeviceEntity();
        private final ConfigEntity configEntity = new ConfigEntity();

        @BeforeEach
        void setUp() {
            deviceEntity.setId(1L);
            configEntity.setDevice(deviceEntity);
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
        void shouldReturnBadRequest() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldSaveConfig() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.of(deviceEntity));
            when(service.saveConfig(deviceEntity, deviceId, configuration)).thenReturn(configEntity);

            callService();

            verify(service).saveConfig(deviceEntity, deviceId, configuration);
        }

        @Test
        void shouldReturnOk() {
            when(iotDeviceRepository.findByDeviceId(deviceId)).thenReturn(Optional.of(deviceEntity));
            when(service.saveConfig(deviceEntity, deviceId, configuration)).thenReturn(configEntity);

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

            verify(configRepository).save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)));
        }

        @Test
        void shouldReturnConfigEntity() {
            when(configRepository.save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)))).thenReturn(new ConfigEntity() {{
                setDeviceId(deviceId);
                setConfiguration(configuration);
            }});

            final var configEntity = callService();

            assertThat(configEntity.getDeviceId()).isEqualTo(deviceId);
            assertThat(configEntity.getConfiguration()).isEqualTo(configuration);
        }

        private ConfigEntity callService() {
            return service.saveConfig(deviceEntity, deviceId, configuration);
        }
    }

    @Nested
    class GetConfigurationTest {

        private final Long configId = 1L;
        private final ConfigEntity configEntity = new ConfigEntity();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configRepository).findById(configId);
        }

        @Test
        void shouldCallHandleCheckConfigIdException() {
            when(configRepository.findById(configId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(exception -> exception.getMessage()
                .equals("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE)));
        }


        @Test
        void shouldReturnBadRequest() {
            when(configRepository.findById(configId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }

        @Test
        void shouldReturnOk() {
            when(configRepository.findById(configId)).thenReturn(Optional.of(configEntity));

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        private ResponseEntity<Object> callService() {
            return service.getConfiguration(configId);
        }
    }

    @Nested
    class UpdateConfigurationTest {

        private final Long configId = 1L;
        private final String configuration = "configuration";
        private final ConfigEntity configEntity = new ConfigEntity();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configRepository).findById(configId);
        }

        @Test
        void shouldCallHandleCheckConfigIdException() {
            when(configRepository.findById(configId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(
                exception -> exception.getMessage().equals("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE)));
        }

        @Test
        void shouldReturnBadRequest() {
            when(configRepository.findById(configId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }

        @Test
        void shouldCallSaveUpdatedConfig() {
            ConfigEntity existingConfigEntity = new ConfigEntity();
            existingConfigEntity.setConfiguration("oldConfiguration");
            existingConfigEntity.setModifiedAt(LocalDateTime.now().minusDays(1)); // Simulating an existing configuration
            when(configRepository.findById(configId)).thenReturn(Optional.of(existingConfigEntity));
            when(service.saveUpdatedConfig(configuration, existingConfigEntity)).thenReturn(existingConfigEntity);

            callService();

            verify(service).saveUpdatedConfig(configuration, existingConfigEntity);
        }


        @Test
        void shouldReturnOk() {
            when(configRepository.findById(configId)).thenReturn(Optional.of(configEntity));

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        private ResponseEntity<Object> callService() {
            return service.updateConfiguration(configId, configuration);
        }
    }

    @Nested
    class SaveUpdatedConfigTest {

        private final String deviceId = "deviceId";
        private final String configuration = "configuration";
        private final ConfigEntity configEntity = new ConfigEntity();
        private final IoTDeviceEntity ioTDeviceEntity = new IoTDeviceEntity();

        @BeforeEach
        void setUp() {
            configEntity.setDevice(ioTDeviceEntity);
            configEntity.setDeviceId(deviceId);
        }

        @Test
        void shouldCallRepository() {
            callService();

            verify(configRepository).save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)));
        }

        @Test
        void shouldReturnUpdatedConfigEntity() {
            when(configRepository.save(argThat(config -> config.getDeviceId().equals(deviceId) && config.getConfiguration()
                .equals(configuration)))).thenReturn(new ConfigEntity() {{
                setDeviceId(deviceId);
                setConfiguration(configuration);
            }});

            final var configEntity = callService();

            assertThat(configEntity.getDeviceId()).isEqualTo(deviceId);
            assertThat(configEntity.getConfiguration()).isEqualTo(configuration);
        }

        private ConfigEntity callService() {
            return service.saveUpdatedConfig(configuration, configEntity);
        }
    }

    @Nested
    class DeleteConfigurationTest {

        private final Long configId = 1L;
        private final ConfigEntity configEntity = new ConfigEntity();

        @Test
        void shouldCallRepository() {
            callService();

            verify(configRepository).findById(configId);
        }

        @Test
        void shouldCallHandleCheckConfigIdException() {
            when(configRepository.findById(configId)).thenReturn(Optional.empty());

            callService();

            verify(service).handleCheckExistingObjectException(argThat(exception -> exception.getMessage()
                .equals("Configuration with id " + configId + DOES_NOT_EXIST_MESSAGE)));
        }


        @Test
        void shouldReturnBadRequest() {
            when(configRepository.findById(configId)).thenReturn(Optional.empty());

            final var responseEntity = callService();

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldReturnOk() {
            when(configRepository.findById(configId)).thenReturn(Optional.of(configEntity));

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