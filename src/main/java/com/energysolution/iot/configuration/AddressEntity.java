package com.energysolution.iot.configuration;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AddressEntity {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "street", nullable = false, length = 128)
    private String street;

    @Column(name = "building_number", nullable = false, length = 128)
    private String buildingNumber;

    @Column(name = "apartment_number", length = 128)
    private String apartmentNumber;

    @Column(name = "city", nullable = false, length = 128)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 128)
    private String postalCode;

    @Column(name = "country", nullable = false, length = 128)
    private String country;
}
