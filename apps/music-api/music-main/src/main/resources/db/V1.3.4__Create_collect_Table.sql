DROP TABLE IF EXISTS `collect`;

CREATE TABLE `collect`
(
    `id`           BIGINT NOT NULL,
    `user_id`      BIGINT NOT NULL,
    `type`         tinyint(4) NOT NULL,
    `song_id`      BIGINT DEFAULT NULL,
    `song_list_id` BIGINT DEFAULT NULL,
    `create_time`  datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='收藏表';