INSERT INTO IOT_DEVICE (device_id, creation_date, modification_date, startup_date, shutdown_date, name, street, building_number, apartment_number, city, postal_code, country)
VALUES
    ('device1', '2024-03-15T10:00:00', '2024-03-15T10:00:00', '2024-03-15T10:00:00', '2024-03-15T10:00:00', 'Name1', 'Street1', '1', NULL, 'City1', '12345', 'Country1'),
    ('device2', '2024-03-15T11:00:00', '2024-03-15T11:00:00', '2024-03-15T11:00:00', '2024-03-15T11:00:00', 'Name2', 'Street2', '2', NULL, 'City2', '23456', 'Country2'),
    ('device3', '2024-03-15T12:00:00', '2024-03-15T12:00:00', '2024-03-15T12:00:00', '2024-03-15T12:00:00', 'Name3', 'Street3', '3', NULL, 'City3', '34567', 'Country3');


INSERT INTO CONFIG (device_id, configuration, created_at, modified_at)
VALUES ('device1', 'Your_configuration_data_here', '2024-03-14T10:00:00', '2024-03-14T10:00:00');