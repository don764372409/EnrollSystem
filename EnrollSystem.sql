DROP TABLE IF EXISTS t_enroll;
CREATE TABLE t_enroll
(
	`id` bigint NOT NULL AUTO_INCREMENT,
	`uId` bigint NOT NULL,
	`mId` bigint NOT NULL,
	`batch` varchar NOT NULL,
	`number` int NOT NULL,
	`maxNumber` int NOT NULL,
	`minNumber` int NOT NULL,
	`avgNumber` int NOT NULL,
	`maxRanking` int NOT NULL,
	`minRanking` int NOT NULL,
	`avgRanking` int NOT NULL,
	`time` datetime NOT NULL,
	`tuition` decimal NOT NULL,
	`studyYear` decimal NOT NULL,
	PRIMARY KEY (`id),
	KEY `fk_uId` (`uId`),
	KEY `fk_mId` (`mId`),
	CONSTRANT `fk_uID` FOREIGN KEY (`uId`) REFERENCES `t_University` (`id`),
	CONSTRANT `fk_mId` FOREIGN KEY (`mId`) REFERENCES `t_major` (`id`)
);
