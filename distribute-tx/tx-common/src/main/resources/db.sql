CREATE TABLE `storage` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `commodity_id` int(11) NOT NULL,
                           `quantity` int(11) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `idx_commodity_id` (`commodity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
INSERT INTO `storage` (`id`, `commodity_id`, `quantity`) VALUES (1, 1, 10);

CREATE TABLE `t_order` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(255) DEFAULT NULL,
                           `commodity_id` int(11) NOT NULL,
                           `quantity` int(11) DEFAULT 0,
                           `price` decimal (10,2) DEFAULT NULL ,
                           `status` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;