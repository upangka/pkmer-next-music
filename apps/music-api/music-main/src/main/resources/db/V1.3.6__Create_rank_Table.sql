DROP TABLE IF EXISTS `rank_list`;
CREATE TABLE `rank_list`
(
    `id`           BIGINT unsigned NOT NULL,
    `song_list_id` BIGINT unsigned NOT NULL,
    `consumer_id`  BIGINT unsigned NOT NULL,
    `score`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `consumerId` (`consumer_id`,`song_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌曲排行榜';