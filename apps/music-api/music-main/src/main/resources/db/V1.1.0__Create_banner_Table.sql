DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`
(
    `id`  BIGINT NOT NULL,
    `pic` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;