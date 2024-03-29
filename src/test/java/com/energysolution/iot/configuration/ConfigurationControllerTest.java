package com.energysolution.iot.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ConfigurationController.class)
class ConfigurationControllerTest extends ControllerTest {

    @MockBean
    private ConfigurationService service;

    @Nested
    class CreateConfigurationTest {

        private static final ConfigurationRequest configurationRequest = ConfigurationRequestTestFactory.create();

        @BeforeEach
        void setUp() {
            when(service.createConfiguration(configurationRequest.deviceId(), configurationRequest.configuration())).thenReturn(ResponseEntity.ok(
                ConfigurationResponseTestFactory.create()));
        }

        @Test
        void shouldCallsService() throws Exception {
            doRequest();

            verify(service).createConfiguration(configurationRequest.deviceId(), configurationRequest.configuration());
        }

        @Test
        void shouldReturnOk() throws Exception {
            doRequest().andExpect(status().isOk());
        }

        @Test
        void shouldReturnResponseBody() throws Exception {
            doRequest().andExpect(isEqualToJsonOf(ConfigurationResponseTestFactory.create()));
        }

        private ResultActions doRequest() throws Exception {
            return mockMvc.perform(post(buildUri("/configurations"))
                                       .contentType(MediaType.APPLICATION_JSON)
                                       .content(toJsonString(configurationRequest)));
        }

    }

    @Nested
    class GetConfigurationTest {

        private static final Long configId = 10L;

        @BeforeEach
        void setUp() {
            when(service.getConfiguration(configId)).thenReturn(ResponseEntity.ok(ConfigurationResponseTestFactory.create()));
        }

        @Test
        void shouldCallsService() throws Exception {
            doRequest();

            verify(service).getConfiguration(configId);
        }

        @Test
        void shouldReturnOk() throws Exception {
            doRequest().andExpect(status().isOk());
        }

        @Test
        void shouldReturnResponseBody() throws Exception {
            doRequest().andExpect(isEqualToJsonOf(ConfigurationResponseTestFactory.create()));
        }

        private ResultActions doRequest() throws Exception {
            return mockMvc.perform(get(buildUri("/configurations/{configId}", configId.toString())));
        }

    }

    @Nested
    class UpdateConfigurationTest {

        private static final UpdateConfigurationRequest updateConfigurationRequest = UpdateConfigurationRequestTestFactory.create();
        private static final Long configId = 10L;

        @BeforeEach
        void setUp() {
            when(service.updateConfiguration(configId, updateConfigurationRequest)).thenReturn(ResponseEntity.ok(
                ConfigurationResponseTestFactory.create()));
        }

        @Test
        void shouldCallsService() throws Exception {
            doRequest();

            verify(service).updateConfiguration(configId, updateConfigurationRequest);
        }

        @Test
        void shouldReturnOk() throws Exception {
            doRequest().andExpect(status().isOk());
        }

        @Test
        void shouldReturnResponseBody() throws Exception {
            doRequest().andExpect(isEqualToJsonOf(ConfigurationResponseTestFactory.create()));
        }

        private ResultActions doRequest() throws Exception {
            return mockMvc.perform(put(buildUri("/configurations/{configId}", configId.toString()))
                                       .contentType(MediaType.APPLICATION_JSON)
                                       .content(toJsonString(updateConfigurationRequest)));
        }

    }

    @Nested
    class DeleteConfigurationTest {

        private static final Long configId = 10L;

        @BeforeEach
        void setUp() {
            when(service.deleteConfiguration(configId)).thenReturn(ResponseEntity.ok().build());
        }

        @Test
        void shouldCallsService() throws Exception {
            doRequest();

            verify(service).deleteConfiguration(configId);
        }

        @Test
        void shouldReturnOk() throws Exception {
            doRequest().andExpect(status().isOk());
        }

        @Test
        void shouldNotReturnResponseBody() throws Exception {
            doRequest().andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEmpty());
        }

        private ResultActions doRequest() throws Exception {
            return mockMvc.perform(delete(buildUri("/configurations/{configId}", configId.toString())));
        }
    }
}
