package com.energysolution.iot.configuration;

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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "IOT_DEVICE")
@NoArgsConstructor
@Getter
@Setter
public class IoTDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128, unique = true)
    private String deviceId;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime creationDate;

    @Column
    @LastModifiedDate
    private LocalDateTime modificationDate;

    @Column
    private LocalDateTime startupDate;

    @Column
    private LocalDateTime shutdownDate;

    @Embedded
    private AddressEntity address;

}