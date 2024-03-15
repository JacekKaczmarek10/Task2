package com.energysolution.iot.iotdevice;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity class for IoT device. Each device has an address.
 */
@Entity
@Table(name = "IOT_DEVICE")
@NoArgsConstructor
@Getter
@Setter
public class IoTDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false, length = 128, unique = true)
    private String deviceId;

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @Column(name = "startup_date")
    private LocalDateTime startupDate;

    @Column(name = "shutdown_date")
    private LocalDateTime shutdownDate;

    @Embedded
    private AddressEntity address;

}