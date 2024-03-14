package com.energysolution.iot.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceRepository extends JpaRepository<IoTDeviceEntity, Long> {

    boolean existsByDeviceId(String deviceId);
}