DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`
(
    `id`           int(10) unsigned NOT NULL AUTO_INCREMENT,
    `username`     varchar(255) NOT NULL,
    `password`     varchar(100) NOT NULL,
    `sex`          tinyint(4) DEFAULT NULL,
    `phone_num`    char(15)     DEFAULT NULL,
    `email`        char(30)     DEFAULT NULL,
    `birth`        datetime     DEFAULT NULL,
    `introduction` varchar(255) DEFAULT NULL,
    `location`     varchar(45)  DEFAULT NULL,
    `avator`       varchar(255) DEFAULT NULL,
    `create_time`  datetime     NOT NULL,
    `update_time`  datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`),
    UNIQUE KEY `phone_num_UNIQUE` (`phone_num`),
    UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;