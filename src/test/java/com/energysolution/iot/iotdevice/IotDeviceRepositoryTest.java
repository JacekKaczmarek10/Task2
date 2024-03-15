package com.energysolution.iot.iotdevice;

import static org.assertj.core.api.Assertions.assertThat;

import com.energysolution.iot.configuration.IoTDeviceEntityTestFactory;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class IotDeviceRepositoryTest {

    @Autowired
    private IotDeviceRepository iotDeviceRepository;

    @Nested
    class FindByDeviceIdTest {

        @Test
        void shouldFind() {
            final var iotDevice = IoTDeviceEntityTestFactory.create();
            iotDeviceRepository.saveAndFlush(iotDevice);

            final var result = iotDeviceRepository.findByDeviceId(IoTDeviceEntityTestFactory.DEVICE_ID);

            assertThat(result).usingRecursiveComparison().ignoringFields("value.modificationDate").isEqualTo(Optional.of(iotDevice));
        }

    }

}