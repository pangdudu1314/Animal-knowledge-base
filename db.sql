CREATE TABLE `animal_visits` (
  `animal_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '动物名称',
  `animal_visits` decimal(10,0) DEFAULT NULL COMMENT '动物访问量',
  `week_day` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '周几'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='动物访问量';



CREATE TABLE `animal_check` (
  `id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `animal_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `data_from` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `animal_image` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `animal_intro` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `admin_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `kemu` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `news` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `theme` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `news` varchar(8000) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;