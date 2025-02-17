DROP TABLE IF EXISTS `management_admin`;
-- 创建管理员表
CREATE TABLE `management_admin`
(
    -- 管理员ID，使用BIGINT类型，确保足够大的范围
    `id`       BIGINT      NOT NULL AUTO_INCREMENT COMMENT '管理员ID，主键，自增',

    -- 管理员名，使用varchar(45)类型，确保名字长度适中
    `name`     varchar(255) NOT NULL COMMENT '管理员名，唯一',

    -- 管理员密码，使用varchar(45)类型，确保密码长度适中
    `password` varchar(255) NOT NULL COMMENT '管理员密码',

    -- 设置id为主键
    PRIMARY KEY (`id`),

    -- 设置name为唯一键，确保管理员名唯一
    UNIQUE KEY `name_UNIQUE` (`name`)
)
-- 使用InnoDB引擎，支持事务和外键
    ENGINE=InnoDB

-- 设置默认字符集为utf8
DEFAULT CHARSET=utf8

-- 表注释
COMMENT='管理员信息表';