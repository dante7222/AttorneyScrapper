CREATE TABLE IF NOT EXISTS `firm`
(
    `id`      BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `firm`    VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `section`
(
    `id`   BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `category`
(
    `id`   BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `attorney`
(
    `id`                BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `full_name`         VARCHAR(255) NOT NULL,
    `phone`             INT(10)      NOT NULL,
    `email`             VARCHAR(255) NOT NULL,
    `education`         VARCHAR(255) NOT NULL,
    `firm_id`           BIGINT(20)   NOT NULL,
    `website`           VARCHAR(255),
    `profile_photo_url` VARCHAR(255) NOT NULL,
    `profile_url`       VARCHAR(255) NOT NULL,

    FOREIGN KEY (`id`) REFERENCES `firm` (`id`) ON DELETE CASCADE

)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `attorney_categories`
(
    `attorney_id` BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,

    PRIMARY KEY (`attorney_id`, `category_id`),
    FOREIGN KEY (`attorney_id`) REFERENCES `attorney` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE

)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `attorney_sections`
(
    `attorney_id` BIGINT NOT NULL,
    `section_id`  BIGINT NOT NULL,

    PRIMARY KEY (`attorney_id`, `section_id`),
    FOREIGN KEY (`attorney_id`) REFERENCES `attorney` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

