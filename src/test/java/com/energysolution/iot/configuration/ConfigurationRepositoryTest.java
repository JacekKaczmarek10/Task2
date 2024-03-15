package com.energysolution.iot.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ConfigurationRepositoryTest {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Nested
    class FindByDeviceIdTest {

        @Test
        void shouldFind() {
            final var config = ConfigEntityTestFactory.create();
            configurationRepository.saveAndFlush(config);

            final var result = configurationRepository.findById(ConfigEntityTestFactory.ID);

            assertThat(result).usingRecursiveComparison().ignoringFields("value.createdAt", "value.modifiedAt").isEqualTo(Optional.of(config));
        }

    }

}