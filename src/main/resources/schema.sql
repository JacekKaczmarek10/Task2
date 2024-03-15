CREATE TABLE IOT_DEVICE (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            device_id VARCHAR(128) NOT NULL UNIQUE,
                            creation_date TIMESTAMP NOT NULL,
                            modification_date TIMESTAMP,
                            startup_date TIMESTAMP,
                            shutdown_date TIMESTAMP,
                            name VARCHAR(128) NOT NULL,
                            street VARCHAR(128) NOT NULL,
                            building_number VARCHAR(128) NOT NULL,
                            apartment_number VARCHAR(128),
                            city VARCHAR(128) NOT NULL,
                            postal_code VARCHAR(128) NOT NULL,
                            country VARCHAR(128) NOT NULL
);

CREATE TABLE CONFIGURATION (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        device_id VARCHAR(128) NOT NULL,
                        device_key BIGINT,
                        configuration VARCHAR(10000) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        modified_at TIMESTAMP NOT NULL,
                        FOREIGN KEY (device_key) REFERENCES IOT_DEVICE(id)
);