CREATE TABLE IF NOT EXISTS `attorney`
(
    `id`                BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `full_name`         VARCHAR(255) NOT NULL,
    `phone`             VARCHAR(30)  NOT NULL,
    `firm_name`         VARCHAR(255),
    `address`           VARCHAR(255),
    `website`           VARCHAR(255),
    `profile_photo_url` VARCHAR(255) NOT NULL,
    `profile_url`       VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `section`
(
    `id`          BIGINT(20) ,
    `attorney_id` BIGINT(20),
    `name`        VARCHAR(255) NOT NULL,

    FOREIGN KEY (`attorney_id`) REFERENCES `attorney` (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`, `attorney_id`)
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `category`
(
    `id`          BIGINT(20) ,
    `attorney_id` BIGINT(20),
    `name`        VARCHAR(255) NOT NULL,

    FOREIGN KEY (`attorney_id`) REFERENCES `attorney` (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`, `attorney_id`)
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `education`
(
    `id`          BIGINT(20) ,
    `attorney_id` BIGINT(20),
    `name`        VARCHAR(255) NOT NULL,

    FOREIGN KEY (`attorney_id`) REFERENCES `attorney` (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`, `attorney_id`)
)
    ENGINE = InnoDB;
