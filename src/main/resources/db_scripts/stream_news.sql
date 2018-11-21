-- -----------------------------------
-- Drop database if exists `stream_news`
-- -----------------------------------
DROP DATABASE IF EXISTS `stream_news`;  

-- -----------------------------------
-- Create database if not exists `stream_news`
-- -----------------------------------
CREATE DATABASE IF NOT EXISTS `stream_news`;
USE `stream_news`;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of `users`
-- ----------------------------
INSERT INTO `users` (`id`, `user_name`, `full_name`, `role`, `password_hash`) VALUES
	(1, 'admin1234', 'Admin1234 Demo', 'ADMIN', '$2a$10$XL2PbfdU2mCoD0G2.2gLkO/bdes5eNKOgFED30niY1wPaQ6SYd5Uu'), -- password = 12345
	(2, 'user1234', 'User1234 Demo', 'USER', '$2a$10$XL2PbfdU2mCoD0G2.2gLkO/bdes5eNKOgFED30niY1wPaQ6SYd5Uu'), -- password = 12345
	(3, 'user1111', 'User1111 Demo', 'USER', '$2a$10$XL2PbfdU2mCoD0G2.2gLkO/bdes5eNKOgFED30niY1wPaQ6SYd5Uu'); -- password = 12345

-- ----------------------------
-- Table structure for `posts`
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) NOT NULL,
  `content` longtext NOT NULL,
  `date` datetime NOT NULL,
  `link` longtext DEFAULT NULL,
  `author_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`author_id`) REFERENCES `users`(`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of `posts`
-- ----------------------------
INSERT INTO `posts` (`id`, `title`, `content`, `date`, `link`, `author_id`) VALUES
	(1, 'A $2 Billion Question: Did New York and Virginia Overpay for Amazon?', 'The states offered tax credits, rebates and other incentives to lure the online retail giant and 25,000 jobs. Neighboring states offered even more.' ,'2018-11-13 06:25:35', 'https://www.nytimes.com/2018/11/13/business/economy/amazon-hq2-va-long-island-city-incentives.html?action=click&module=Top%20Stories&pgtype=Homepage', 1),
	(2, 'Avoid Fake News and Verify the Truth With These 5 Sites and Apps', 'There are plenty of lies floating around on the internet. From extensions that flag notorious fake news outlets to websites that bust hoaxes and myths, here are the five resources you need.', '2016-11-29 09:45:12', 'https://www.makeuseof.com/tag/avoid-fake-news-verify-truth-sites-apps/', 2),
	(3, 'Nervousness descends in Dublin over Irish Sea border.', 'The Irish government has said it does not want to see any “hardening” of the border in the Irish Sea, ahead of a crunch cabinet meeting in Dublin on Brexit on Wednesday morning.', '2018-11-14 05:35:30', 'https://www.theguardian.com/world/2018/nov/14/ireland-hardening-irish-sea-border-brexit', 2),
	(4, 'Can we save the prized bluefin tuna by growing it in a lab?', 'If they can figure out the science from beginning to end and how to scale up the process into a viable commercial venture, the folks at Finless Foods hint at an almost utopian reversal of fortunes for humans, fish and the environment.','2018-11-13 04:15:22', 'https://www.washingtonpost.com/graphics/2018/lifestyle/cultured-bluefin-tuna/?utm_term=.fa73998a2c01', 1),
	(5, 'Azerbaijan GP: Can the old and new of Baku keep F1 fire alive?', '…The F1 history books were already open this season, the pen poised to write Vettel or Hamiltons name as a five-time world champion. Then the Chinese Grand Prix happened and what could have become a predictable title fight has now become a…', '2018-04-24 04:52:25', 'https://www.bbc.co.uk/sport/formula1/43800027', 1),
	(6, 'Why there’s no such thing as a perfect vegan.', 'Almost everything we eat involves some kind of animal suffering – but we can do our best to minimise it.', '2018-11-15 08:00:00', 'https://www.theguardian.com/lifeandstyle/2018/nov/15/why-theres-no-such-thing-as-a-perfect-vegan', 3),	
    (7, 'Veterans Affairs official to be demoted over delayed GI Bill benefits.', 'NBC News reported Sunday that IT glitches have caused payments to be delayed for months or never be delivered, potentially affecting hundred of thousands of veterans.', '2016-11-16 10:35:42', 'https://www.nbcnews.com/news/us-news/veterans-affairs-official-depart-office-after-house-hearing-over-delayed-n936441', 2),
	(8, 'Delay, Deny and Deflect: How Facebook’s Leaders Fought Through Crisis.', 'A Times investigation revealed how the social network responded as it faced one scandal after another — Russian meddling, data sharing, hate speech. The executives Mark Zuckerberg and Sheryl Sandberg stumbled. Bent on growth, the pair ignored warning signs and then sought to conceal them.', '2018-11-17 04:05:00', 'https://www.nytimes.com/2018/11/14/technology/facebook-data-russia-election-racism.html?action=click&module=Top%20Stories&pgtype=Homepage', 1),
	(9, 'Being bionic: how technology transformed my life.', 'Prosthetics have made amazing advances in recent years – and are slowly changing people’s attitudes to disability. By Patrick Kane.','2018-11-18 07:22:10', 'https://www.theguardian.com/technology/2018/nov/15/being-bionic-how-technology-transformed-my-life-prosthetic-limbs', 1),
	(10, 'Frozen "super-Earth" could support life, experts say.', 'The rocky planet, at least 3.2 times the size of Earth, would be the second-closest world known beyond our solar system.', '2018-11-03 05:12:35', 'https://news.sky.com/story/barnards-star-b-frozen-super-earth-found-six-light-years-away-could-support-life-experts-say-11554317', 3);

-- ----------------------------
-- Table structure for `access`
-- ----------------------------
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `date_time` datetime NOT NULL,
  `ip` longtext DEFAULT NULL,
  `author_id` int(20) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `ip_location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`author_id`) REFERENCES `users`(`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

