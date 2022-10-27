CREATE TABLE `region` (
                          `id` varchar(100) NOT NULL,
                          `region_id` varchar(100) NOT NULL,
                          `region_name` varchar(200) NOT NULL,
                          PRIMARY KEY (`id`)
);

INSERT INTO `region` (`id`, `region_id`, `region_name`) VALUES ("ap-melbourne-1", "ap-melbourne-1", "オーストラリア南東部(メルボルン)");
INSERT INTO `region` (`id`, `region_id`, `region_name`) VALUES ("ap-osaka-1", "ap-osaka-1", "日本中央部(大阪)");
INSERT INTO `region` (`id`, `region_id`, `region_name`) VALUES ("ap-sydney-1", "ap-sydney-1", "オーストラリア東部(シドニー)");
INSERT INTO `region` (`id`, `region_id`, `region_name`) VALUES ("ap-tokyo-1", "ap-tokyo-1", "日本東部(東京)");
INSERT INTO `region` (`id`, `region_id`, `region_name`) VALUES ("sa-saopaulo-1", "sa-saopaulo-1", "ブラジル東部(サンパウロ)");

COMMIT;