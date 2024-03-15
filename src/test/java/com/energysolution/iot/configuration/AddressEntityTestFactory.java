package com.energysolution.iot.configuration;

import com.energysolution.iot.iotdevice.AddressEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressEntityTestFactory {

    public static final String NAME = "Test Address";
    public static final String STREET = "Test Street";
    public static final String BUILDING_NUMBER = "123";
    public static final String APARTMENT_NUMBER = "456";
    public static final String CITY = "Test City";
    public static final String POSTAL_CODE = "12345";
    public static final String COUNTRY = "Test Country";

    public static AddressEntity create() {
        final var addressEntity = new AddressEntity();
        addressEntity.setName(NAME);
        addressEntity.setStreet(STREET);
        addressEntity.setBuildingNumber(BUILDING_NUMBER);
        addressEntity.setApartmentNumber(APARTMENT_NUMBER);
        addressEntity.setCity(CITY);
        addressEntity.setPostalCode(POSTAL_CODE);
        addressEntity.setCountry(COUNTRY);
        return addressEntity;
    }

}