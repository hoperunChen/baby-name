

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_names`;
CREATE TABLE `t_names` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `like_count` int(11) DEFAULT '0' COMMENT '喜欢次数',
  `descr` varchar(40) COLLATE utf8mb4_unicode_ci COMMENT '名字详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='姓名库';

DROP TABLE IF EXISTS `t_like_names`;
CREATE TABLE `t_like_names` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name_id` int(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL  COMMENT '姓名id',
  `user_id` int(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT'用户',
  `like_time` datetime COMMENT '喜欢时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='喜欢姓名库';


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL  COMMENT '用户姓名',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT'手机号',
  `schedule` int(32) DEFAULT '0' COMMENT'进度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='用户';

SET FOREIGN_KEY_CHECKS = 1;
