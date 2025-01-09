DROP TABLE IF EXISTS `singer`;

CREATE TABLE `singer`
(
    `id`           BIGINT NOT NULL,
    `name`         varchar(45) NOT NULL,
    `sex`          tinyint(4) DEFAULT NULL,
    `pic`          varchar(255) DEFAULT NULL,
    `birth`        datetime     DEFAULT NULL,
    `location`     varchar(45)  DEFAULT NULL,
    `introduction` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌手';