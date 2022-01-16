CREATE TABLE `qun_member_t` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `qun_id` bigint(20) DEFAULT NULL,
                                `user_id` varchar(50) DEFAULT NULL,
                                `version` bigint(20) DEFAULT NULL,
                                `update_transaction` varchar(50) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `qunid_userid` (`qun_id`,`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4



CREATE TABLE `qun_t` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `name` varchar(50) NOT NULL,
                         `member_count` int(11) DEFAULT NULL,
                         `version` bigint(20) DEFAULT NULL,
                         `update_transaction` varchar(50) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4