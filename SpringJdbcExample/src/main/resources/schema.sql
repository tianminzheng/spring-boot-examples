DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `authority`;
DROP TABLE IF EXISTS `account_authority`;

create table `account` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`account_number` varchar(50) not null,
	`account_name` varchar(100) not null,
    `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

create table `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_code` varchar(50) not null,
  `authority_name` varchar(50) not null,
  `description` varchar(100) not null,
  `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

create table `account_authority` (
	`account_id` bigint(20) not null,
	`authority_id` bigint(20) not null,
	foreign key(`account_id`) references `account`(`id`),	
	foreign key(`authority_id`) references `authority`(`id`)
);