DROP TABLE IF EXISTS `list_song`;
CREATE TABLE `list_song`
(
    `id`           BIGINT  NOT NULL,
    `song_id`      BIGINT  NOT NULL,
    `song_list_id` BIGINT  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `song_list_idx` (song_id, song_list_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌单歌曲关联表';