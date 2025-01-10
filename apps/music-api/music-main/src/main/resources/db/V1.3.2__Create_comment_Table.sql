CREATE TABLE `comment`
(
    `id`           BIGINT NOT NULL,
    `user_id`      BIGINT NOT NULL,
    `song_id`      BIGINT DEFAULT NULL,
    `song_list_id` BIGINT DEFAULT NULL,
    `content`      varchar(255) DEFAULT NULL,
    `create_time`  datetime     DEFAULT NULL,
    `type`         tinyint(4) NOT NULL,
    `up`           int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='评论表';