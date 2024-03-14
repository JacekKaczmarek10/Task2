package com.energysolution.iot.iotdevice;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceRepository extends JpaRepository<IoTDeviceEntity, Long> {

    Optional<IoTDeviceEntity> findByDeviceId(String deviceId);
}