DROP TABLE IF EXISTS `mybatis_plus_test_user`;

CREATE TABLE `mybatis_plus_test_user`
(
    `id`    BIGINT NOT NULL COMMENT '主键ID',
    `name`  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    `age`   INT NULL DEFAULT NULL COMMENT '年龄',
    `email` VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
