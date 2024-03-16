package com.energysolution.iot.configuration;

import com.energysolution.iot.iotdevice.IoTDeviceEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IoTDeviceEntityTestFactory {

    public static final Long ID = 1L;
    public static final String DEVICE_ID = "TestDevice";
    public static final LocalDateTime CREATION_DATE = LocalDateTime.now();
    public static final LocalDateTime MODIFICATION_DATE = LocalDateTime.now();
    public static final LocalDateTime STARTUP_DATE = LocalDateTime.of(2024, 10, 10, 10, 10);
    public static final LocalDateTime SHUTDOWN_DATE = LocalDateTime.of(2024, 10, 10, 10, 10);

    public static IoTDeviceEntity create() {
        IoTDeviceEntity device = new IoTDeviceEntity();
        device.setId(ID);
        device.setDeviceId(DEVICE_ID);
        device.setCreationDate(CREATION_DATE);
        device.setModificationDate(MODIFICATION_DATE);
        device.setStartupDate(STARTUP_DATE);
        device.setShutdownDate(SHUTDOWN_DATE);
        device.setAddress(AddressEntityTestFactory.create());
        return device;
    }


}
