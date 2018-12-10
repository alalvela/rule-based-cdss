CREATE TABLE user (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    username VARCHAR(255) NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    role VARCHAR(255) NOT NULL,
                    is_deleted BIT(1) NOT NULL,
                    PRIMARY KEY (id)
);