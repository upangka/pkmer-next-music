DROP TABLE IF EXISTS `song`;

CREATE TABLE `song`
(
    `id`           BIGINT NOT NULL,
    `singer_id`    int(10) unsigned NOT NULL,
    `name`         varchar(45)  NOT NULL,
    `introduction` varchar(255) DEFAULT NULL,
    `create_time`  datetime     NOT NULL COMMENT '发行时间',
    `update_time`  datetime     NOT NULL,
    `pic`          varchar(255) DEFAULT NULL,
    `lyric`        text,
    `url`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='歌曲';