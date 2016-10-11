/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : graduate_management

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-11 20:19:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company_employing_unit
-- ----------------------------
DROP TABLE IF EXISTS `company_employing_unit`;
CREATE TABLE `company_employing_unit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECRUITMENT_TYPE` int(11) DEFAULT NULL,
  `UNIT_CODE` varchar(255) DEFAULT NULL,
  `UNIT_NAME` varchar(255) DEFAULT NULL,
  `UNIT_ADDRESS` varchar(255) DEFAULT NULL,
  `UNIT_TYPE` int(11) DEFAULT NULL,
  `UNIT_CONTANT_PERSON` varchar(255) DEFAULT NULL,
  `UNIT_CONTANT_PHONE` varchar(255) DEFAULT NULL,
  `UNIT_DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_employing_unit
-- ----------------------------
INSERT INTO `company_employing_unit` VALUES ('1', '9001', '001', 'xxx有限公司1', '广东省江门市新会区仁义', '8001', '华晨宇', '15088132345', '我也觉得');
INSERT INTO `company_employing_unit` VALUES ('2', '9002', '001', 'xxx有限公司2', '广东省江门市新会区仁义', '8002', '宁桓宇', '15088132345', '好公司');
INSERT INTO `company_employing_unit` VALUES ('3', '9003', '001', 'xxx有限公司3', '广东省江门市新会区仁义', '8003', '胡歌', '15088132345', '好公司');
INSERT INTO `company_employing_unit` VALUES ('4', '9004', '001', 'xxx有限公司4', '广东省江门市新会区仁义', '8004', '谢霆锋', '15088132345', '好公司');
INSERT INTO `company_employing_unit` VALUES ('8', '9005', '007', 'xxxx有限公司8', '广东省江门市新会区', '8003', '郑伊健', '15088132331', '他超级厉害\r\n然而并没有什么用');
INSERT INTO `company_employing_unit` VALUES ('10', '9002', '005', 'xxxx有限公司10', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('12', '9002', '005', 'xxxx有限公12', '广东省江门市', '8003', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('13', '9002', '005', 'xxxx有限公司13', '广东省江门市', '8001', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('14', '9006', '005', 'xxxx有限公司14', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('15', '9002', '005', 'xxxx有限公司15', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('16', '9001', '005', 'xxxx有限公司16', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('17', '9002', '005', 'xxxx有限公司17', '广东省江门市', '8001', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('18', '9006', '005', 'xxxx有限公司18', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('19', '9002', '005', 'xxxx有限公司19', '广东省江门市', '8003', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('20', '9003', '005', 'xxxx有限公司20', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('21', '9002', '005', 'xxxx有限公司21', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('22', '9001', '005', 'xxxx有限公司22', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('23', '9002', '005', 'xxxx有限公司23', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('24', '9002', '005', 'xxxx有限公司24', '广东省江门市', '8002', '老表', '15088132331', '他超级厉害');
INSERT INTO `company_employing_unit` VALUES ('25', '9002', '002', '公司2号25', '新会', '8002', '那英', '15088132347', '一个比较好的公司，\r\n欢迎加入');
INSERT INTO `company_employing_unit` VALUES ('26', '9001', '001', 'asd26', 'asd', '8001', 'asd', 'asd', 'asd');
INSERT INTO `company_employing_unit` VALUES ('27', '9001', '001', '111_27', '11', '8001', '11', '111', '11');
INSERT INTO `company_employing_unit` VALUES ('28', '9002', '005', 'xxxx有限公司28', '广东省江门市', '8004', '老表', '15088132331', '他超级厉害\r\n然而并没有什么用');
INSERT INTO `company_employing_unit` VALUES ('29', '9001', '0011111', '测试', '', '8001', '', '', '');

-- ----------------------------
-- Table structure for company_recruitment_info
-- ----------------------------
DROP TABLE IF EXISTS `company_recruitment_info`;
CREATE TABLE `company_recruitment_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POSITION` varchar(255) DEFAULT NULL,
  `MONTHLY_SALARY` int(11) DEFAULT NULL,
  `WORK_TYPE` int(11) DEFAULT NULL,
  `EDUCATION_TYPE` int(11) DEFAULT NULL,
  `HIRE_COUNT` int(11) DEFAULT NULL,
  `RELEASE_TIME` date DEFAULT NULL,
  `END_TIME` date DEFAULT NULL,
  `POSITION_DESCRIPTION` text,
  `RECRUITMENTUNIT_ID` int(11) DEFAULT NULL,
  `INDUSTRY_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_5vfa57vnnv4stgsi5yrkynpdg` (`RECRUITMENTUNIT_ID`),
  CONSTRAINT `FK_5vfa57vnnv4stgsi5yrkynpdg` FOREIGN KEY (`RECRUITMENTUNIT_ID`) REFERENCES `company_employing_unit` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_recruitment_info
-- ----------------------------
INSERT INTO `company_recruitment_info` VALUES ('1', '软件工程师', '11001', '12001', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13003');
INSERT INTO `company_recruitment_info` VALUES ('3', '软件工程师', '11002', '12002', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '2', '13008');
INSERT INTO `company_recruitment_info` VALUES ('4', '游戏策划', '11002', '12001', '7005', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '12', '13001');
INSERT INTO `company_recruitment_info` VALUES ('5', '软件工程师', '11002', '12001', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '3', '13010');
INSERT INTO `company_recruitment_info` VALUES ('6', '游戏策划', '11001', '12003', '7004', '12', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13021');
INSERT INTO `company_recruitment_info` VALUES ('8', '技术经理', '11001', '12004', '7005', '1', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '3', '13008');
INSERT INTO `company_recruitment_info` VALUES ('9', '游戏策划', '11003', '12001', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13024');
INSERT INTO `company_recruitment_info` VALUES ('11', '技术经理', '11001', '12002', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13017');
INSERT INTO `company_recruitment_info` VALUES ('12', '软件工程师', '11003', '12001', '7005', '52', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '2', '13018');
INSERT INTO `company_recruitment_info` VALUES ('13', '软件工程师', '11001', '12001', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '3', '13011');
INSERT INTO `company_recruitment_info` VALUES ('14', '游戏策划', '11001', '12001', '7005', '53', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13015');
INSERT INTO `company_recruitment_info` VALUES ('15', '技术经理', '11001', '12002', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '2', '13012');
INSERT INTO `company_recruitment_info` VALUES ('16', '游戏策划', '11004', '12004', '7005', '51', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '3', '13012');
INSERT INTO `company_recruitment_info` VALUES ('17', '产品总监', '11002', '12002', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '4', '13002');
INSERT INTO `company_recruitment_info` VALUES ('18', '软件工程师', '11004', '12001', '7004', '53', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '4', '13012');
INSERT INTO `company_recruitment_info` VALUES ('19', '技术经理', '11001', '12002', '7005', '51', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13002');
INSERT INTO `company_recruitment_info` VALUES ('20', '软件工程师', '11001', '12001', '7005', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '4', '13003');
INSERT INTO `company_recruitment_info` VALUES ('21', '软件工程师', '11001', '12004', '7004', '2', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13003');
INSERT INTO `company_recruitment_info` VALUES ('22', '产品总监', '11001', '12003', '7005', '3', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13022');
INSERT INTO `company_recruitment_info` VALUES ('23', '产品总监', '11005', '12001', '7004', '5', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13020');
INSERT INTO `company_recruitment_info` VALUES ('24', '软件工程师', '11001', '12001', '7004', '52', '2016-10-01', '2016-12-31', '岗位描述：\\n1.负责新业务模块开发\\n 2.负责java中间件的设计与开发\\n3.负责系统设计、优化、重构、拆分，参与技术方案制定与实施\\n 4.参与新人培养与团队管理 \\n\\n 职位要求：\r\n1.java、算法等基本功扎实，有良好的面向对象设计思想，对多线程有深刻理解，熟悉设计模式，拥有良好的编程习惯\r\n2.有分布式系统开发、优化、重构、拆分经验\r\n3.熟悉缓存技术，在生产环境中使用过至少一种noSql产品\r\n4.阅读并分析过开源框架源码\r\n5.自学能力强，有责任心，上进心，有团队管理经验更佳\r\n\r\n注：简历中请注明项目经历及个人擅长的相关技术', '1', '13010');
INSERT INTO `company_recruitment_info` VALUES ('25', '小二货', '11005', '12003', '7005', '2', '2016-10-01', '2016-12-31', '不知道干什么的\r\n先找个人', '25', '13004');

-- ----------------------------
-- Table structure for news_nn
-- ----------------------------
DROP TABLE IF EXISTS `news_nn`;
CREATE TABLE `news_nn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` longtext,
  `content` longtext,
  `kew_words` varchar(255) DEFAULT NULL,
  `cover_img` varchar(255) DEFAULT NULL,
  `plate_id` int(11) DEFAULT NULL,
  `news_type` int(11) DEFAULT NULL,
  `plate_pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_nn
-- ----------------------------
INSERT INTO `news_nn` VALUES ('18', '梁饼啊', '2016-04-20 22:24:33', '1', '测试1', '测试一下', '123', '哈哈', '', '0', '15002', '9');
INSERT INTO `news_nn` VALUES ('20', '测试', '2016-10-11 20:14:34', '0', '测试', '测试', '', '', 'cef711a7-7725-4827-b3ef-c5476cf831dd.jpg', '0', '15001', '26');
INSERT INTO `news_nn` VALUES ('21', 'Benson', '2016-10-11 20:14:53', '0', '图片呀', '还尅', '', '图片', '80c40a94-f2c0-45b8-bcb9-5debe5e04c64.jpg', '0', '15001', '30');
INSERT INTO `news_nn` VALUES ('22', '新闻1', '2016-10-11 20:14:17', '0', '新闻1', '新闻1', '', '新闻1', '3b651e0b-e808-46eb-9264-d49d7b737c53.jpg', '0', '15002', '26');
INSERT INTO `news_nn` VALUES ('23', '新闻2', '2016-10-11 20:15:08', '0', '新闻2', '新闻2', '', '新闻2', 'c0f714e5-99f8-4757-8a08-dac407eeab09.jpg', '0', '15002', '26');
INSERT INTO `news_nn` VALUES ('24', '新闻3', '2016-10-11 20:15:18', '0', '新闻3', '新闻3', '', '', '53ecdcea-a805-4a46-b1be-089e45c6ce61.jpg', '0', '15002', '26');
INSERT INTO `news_nn` VALUES ('25', '新闻4', '2016-10-11 20:15:29', '0', '新闻4', '新闻4', '', '新闻4', 'cffcca93-c958-4d0d-aa35-72bdcb747dbc.jpg', '0', '15002', '26');
INSERT INTO `news_nn` VALUES ('26', '新闻5', '2016-10-11 20:15:58', '0', '新闻5', '新闻5', '', '', '898b008c-30e1-427b-9e08-a22b2d0edbac.JPG', '0', '15002', '26');
INSERT INTO `news_nn` VALUES ('27', 'Benson', '2016-10-11 20:16:16', '0', '最新动态1', '最新动态1', '', '最新动态1', 'b6467fcf-15cd-4ab1-a946-72145a5f0cf9.JPG', '0', '15005', '30');
INSERT INTO `news_nn` VALUES ('28', '梁饼', '2016-10-11 20:16:25', '0', '最新动态2', '最新动态2', '', '', 'feeb694f-8a44-484e-a71a-98c2028545ca.JPG', '0', '15005', '30');
INSERT INTO `news_nn` VALUES ('29', 'Benson', '2016-04-20 22:24:33', '0', '毕业咯', 'biyele,youdianbushede', '', '', '', '0', '15002', '30');
INSERT INTO `news_nn` VALUES ('30', '梁饼', '2016-04-24 15:14:10', '1', '通知公告1', '通知公告1', '通知公告', '', '', '0', '15003', '30');
INSERT INTO `news_nn` VALUES ('31', 'Benson', '2016-10-11 20:16:41', '0', '通知公告2', '通知公告2', '通知公告2', '宣讲会2', '61fa061e-6dc2-4e15-b3db-a729bee29de3.jpg', '0', '15003', '30');
INSERT INTO `news_nn` VALUES ('32', 'Benson', '2016-10-11 20:14:02', '0', '长新闻', '比较长', '信息进村入户是农业部为贯彻落实党的十八届三中全会精神和2014年中央一号文件的有关要求，为加快完善农业信息服务体系，满足农民群众和新型农业经营主体信息需求而开展的一项重大工程。\r\n \r\n信息进村入户是一件大大的好事，其目的是让农村跟城市一样大步跨入信息时代，享受丰富的互联网产品，享受从网上购物的实惠，网上缴费、买票、挂号的便捷。这么件大好事自然应当是农业部一推动，各地政府各企业就纷纷响应，把事情风风火火的办起来。然而我们看到信息进村入户目前推广的并不理想，反而有些步履蹒跚，这就是说项目是好项目，市场也有很大的需求，然而实施的并不理想，我认为关键在于没有找到最佳结合点，那么结合点在哪？今天我们就来唠一唠。\r\n \r\n一、信息进村入户工程的实施体系\r\n \r\n在信息进村入户的宏伟蓝图中，政府、服务商、运营商构成“铁三角”，他们各司其职、各尽其能：\r\n \r\n政府，负责公益资源整合，提供公益服务，协调建好信息高速公路；\r\n \r\n服务商，包括电信运营商、生活服务商、平台电商、金融服务商、系统集成商、信息服务商等，负责提供各类商业服务和通道，通过扩大市场规模获得收益；\r\n \r\n运营商，综合利用通道和信息高速公路整合各类公益和商业服务，为农民提供免费和低价服务。\r\n \r\n简单的说就是政府“搭台”，服务商提供“原材料”，然后运营商“做菜”给农民。最后三方合力，推进信息进村入户，做到共建、共赢、共享，形成收益共享、风险共担的合作模式，最终实现让农民不花钱或少花钱就能得到实惠。\r\n \r\n信息进村入户工程落地的主体是“村级信息服务站”，其有三种类型：标准型、简易型、专业型。\r\n \r\n标准型提供公益服务、便民生活服务、电子商务服务和培训体验服务，一般选址村综合服务中心、大农资超市、村科技服务站；\r\n简易型提供便民、电子商务服务，从便民商店、农资商店发展而来；\r\n专业型则依托新型农业经营主体，由农业带头人围绕生产经营活动为成员提供专业服务。\r\n \r\n村级信息服务站要按照有场所、有人员、有设备、有宽带、有网页、有持续运营能力的“六有”标准建设，其在全国统一使用“益农信息社”品牌，整齐划一，正是同一个名称，同一个梦想。', '长长长的新闻', '0058ebe0-a1dc-4a75-b8cc-645125fe1105.png', '0', '15005', '30');

-- ----------------------------
-- Table structure for news_plate
-- ----------------------------
DROP TABLE IF EXISTS `news_plate`;
CREATE TABLE `news_plate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_name` varchar(255) DEFAULT NULL,
  `news_type` int(11) DEFAULT NULL,
  `plate_pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_82d8juyd7yy2sl2kpt7c4mutw` (`plate_pid`),
  CONSTRAINT `FK_82d8juyd7yy2sl2kpt7c4mutw` FOREIGN KEY (`plate_pid`) REFERENCES `news_plate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_plate
-- ----------------------------
INSERT INTO `news_plate` VALUES ('26', '新生导航', '1', null);
INSERT INTO `news_plate` VALUES ('29', '招生计划', '1', null);
INSERT INTO `news_plate` VALUES ('30', '新闻动态', '1', null);
INSERT INTO `news_plate` VALUES ('31', '学校特色', '1', null);
INSERT INTO `news_plate` VALUES ('32', '学校校风', '1', null);

-- ----------------------------
-- Table structure for stu_class
-- ----------------------------
DROP TABLE IF EXISTS `stu_class`;
CREATE TABLE `stu_class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `GRADE_ID` int(11) DEFAULT NULL,
  `MAJOR_FIELD_ID` int(11) DEFAULT NULL,
  `CLASS_QQ_NUMBER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_7nmuy6lsuii306ajlkoo59bhi` (`GRADE_ID`),
  KEY `FK_sjcs4bd5uoo0yr1o5yj3qky3x` (`MAJOR_FIELD_ID`),
  CONSTRAINT `FK_7nmuy6lsuii306ajlkoo59bhi` FOREIGN KEY (`GRADE_ID`) REFERENCES `stu_grade` (`ID`),
  CONSTRAINT `FK_sjcs4bd5uoo0yr1o5yj3qky3x` FOREIGN KEY (`MAJOR_FIELD_ID`) REFERENCES `stu_major_field` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_class
-- ----------------------------
INSERT INTO `stu_class` VALUES ('1', '120801', '4', '1', '75050616');
INSERT INTO `stu_class` VALUES ('2', '140802', '4', '5', '75050616');
INSERT INTO `stu_class` VALUES ('3', '120911', '4', '8', '75050616');
INSERT INTO `stu_class` VALUES ('4', '131101', '4', '7', '75050616');
INSERT INTO `stu_class` VALUES ('5', '131102', '4', '7', null);
INSERT INTO `stu_class` VALUES ('6', '131104', '4', '7', null);
INSERT INTO `stu_class` VALUES ('7', '131105', '4', '7', null);
INSERT INTO `stu_class` VALUES ('8', '120802', '4', '2', null);
INSERT INTO `stu_class` VALUES ('9', '120803', '4', '2', null);
INSERT INTO `stu_class` VALUES ('10', '120804', '4', '2', null);
INSERT INTO `stu_class` VALUES ('11', '140808', '4', '3', null);
INSERT INTO `stu_class` VALUES ('12', '140809', '4', '6', null);
INSERT INTO `stu_class` VALUES ('13', '140810', '4', '4', null);
INSERT INTO `stu_class` VALUES ('14', '140811', '4', '9', null);
INSERT INTO `stu_class` VALUES ('15', '140812', '4', '10', null);
INSERT INTO `stu_class` VALUES ('16', '140813', '4', '11', '75050616');
INSERT INTO `stu_class` VALUES ('17', '140814', '4', '17', '75050616');
INSERT INTO `stu_class` VALUES ('18', '140815', '4', '12', '75050616');
INSERT INTO `stu_class` VALUES ('19', '140816', '4', '13', '75050616');
INSERT INTO `stu_class` VALUES ('20', '140817', '4', '14', '75050616');
INSERT INTO `stu_class` VALUES ('21', '140818', '4', '15', '75050616');
INSERT INTO `stu_class` VALUES ('22', '140819', '4', '16', '75050616');
INSERT INTO `stu_class` VALUES ('23', '130801', '1', '19', null);
INSERT INTO `stu_class` VALUES ('26', '120807', '1', '1', '');

-- ----------------------------
-- Table structure for stu_department
-- ----------------------------
DROP TABLE IF EXISTS `stu_department`;
CREATE TABLE `stu_department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_department
-- ----------------------------
INSERT INTO `stu_department` VALUES ('1', '计算机学院', '计算机学院');
INSERT INTO `stu_department` VALUES ('2', '土木建设学院', '土木建设学院');
INSERT INTO `stu_department` VALUES ('3', '艺术学院', '艺术学院');
INSERT INTO `stu_department` VALUES ('4', '信息学院', '信息学院');
INSERT INTO `stu_department` VALUES ('5', '化学与环境学院', '化学与环境学院');
INSERT INTO `stu_department` VALUES ('6', '文学院', '文学院');
INSERT INTO `stu_department` VALUES ('7', '外国语学院', '外国语学院');
INSERT INTO `stu_department` VALUES ('8', '经济管理学院', '经济管理学院');

-- ----------------------------
-- Table structure for stu_grade
-- ----------------------------
DROP TABLE IF EXISTS `stu_grade`;
CREATE TABLE `stu_grade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `IS_GREADUATION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_grade
-- ----------------------------
INSERT INTO `stu_grade` VALUES ('1', '大一', '0', '2002');
INSERT INTO `stu_grade` VALUES ('2', '大二', '1', '2002');
INSERT INTO `stu_grade` VALUES ('3', '大三', '2', '2002');
INSERT INTO `stu_grade` VALUES ('4', '大四', '3', '2001');
INSERT INTO `stu_grade` VALUES ('5', '大五', '4', '2001');

-- ----------------------------
-- Table structure for stu_graduate_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_graduate_info`;
CREATE TABLE `stu_graduate_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `UNIT_NAME` varchar(255) DEFAULT NULL,
  `UNIT_LOCATION` varchar(255) DEFAULT NULL,
  `UNIT_TYPE` int(11) DEFAULT NULL,
  `UNIT_INDUSTRY` varchar(255) DEFAULT NULL,
  `UNIT_CONTANT_PERSON` varchar(255) DEFAULT NULL,
  `UNIT_CONTANT_PHONE` varchar(255) DEFAULT NULL,
  `UNIT_ADDRESS` varchar(255) DEFAULT NULL,
  `GRADUATE_PHONE` varchar(255) DEFAULT NULL,
  `APPLY_TYPE` int(11) DEFAULT NULL,
  `UNIT_POSTEN_CODING` varchar(255) DEFAULT NULL,
  `STUDENT_ID` int(11) DEFAULT NULL,
  `DEPARTMENT_ADUIT_STATUS` int(11) DEFAULT NULL,
  `SCHOOL_ADUIT_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_c0co3yoafcel7qmjmcipjeis5` (`STUDENT_ID`),
  CONSTRAINT `FK_c0co3yoafcel7qmjmcipjeis5` FOREIGN KEY (`STUDENT_ID`) REFERENCES `stu_student` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_graduate_info
-- ----------------------------
INSERT INTO `stu_graduate_info` VALUES ('2', '梁炳坚', '有限公司哥斯拉', '广东深圳', '8003', '你来问我啊笨', '谢霆锋', '15088132324', '广东省深圳市科技园aaaaaaa\r\nbbbbbbb', '15088132332', '9005', '529100', '4', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('5', '邝静文', '美国玫瑰公司', '广东省江门市', '8001', '销售行业', '周杰伦', '15088132331', '阿斯顿', '15088132311', '9003', '529100', '7', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('8', '小i', 'jhgh公司', '广东省江门市', '8002', '你猜', '陈奕迅', '15088132331', '阿斯顿', '15088134444', '9003', '529100', '6', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('9', '梁炳坚', 'oooo有限公司', '湖北省宜昌市', '8002', '销售类', '周笔畅', '15088134545', '你好', '15088134785', '9006', '529020', '9', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('10', '梁炳坚', '你好也公司', '广东新会', '8003', '不知道', '周星驰', '15088132358', '你猜', '15088135974', '9001', '529100', '11', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('11', '小j', '阿斯顿', '阿斯顿', '8002', '阿斯顿', '阿斯顿', '15088131524', '阿斯顿', '15088132344', '9004', '529100', '2', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('12', '邝静文', '阿斯顿', '阿斯顿', '8003', '阿斯顿', '厉害', '15088134444', '阿斯顿', '15088131111', '9005', '529000', '13', '10001', '10001');
INSERT INTO `stu_graduate_info` VALUES ('13', '哈哈', '阿斯顿', '阿斯顿', '8001', '阿斯顿', '阿斯顿', '15088132323', '阿斯顿', '15088137474', '9006', '529100', '10', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('14', '梁炳坚', '阿斯顿', '阿斯顿', '8002', '阿斯顿', '阿斯顿', '15088132365', '阿斯顿', '15088132457', '9005', '529100', '12', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('15', '梁炳坚', '腾讯', '广东深深圳市', '8001', '计算机', '周董', '15088132365', 'asd', '15088132331', '9002', '529100', '18', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('16', '梁炳坚', '不知道', '不知道', '8003', '不知道', '不知道', '15088132331', '不知道', '15081323311', '9004', '529100', '17', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('17', '梁炳坚', 'asd', 'asd', '8001', 'asd', 'asd', '1508132311', 'asd', '15088132331', '9002', '529100', '19', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('18', '梁炳坚', 'asd', 'asd', '8001', 'asd', 'asd', '1508132311', 'asd', '15088132331', '9002', '529100', '19', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('19', '梁炳坚', 'asd', 'asd', '8004', 'asd', 'asd', '15088132331', 'asd', '15088132331', '9006', '529100', '16', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('22', '梁炳坚', 'xxx', 'xxx', '8002', 'xxx', 'xx', '15088132331', 'asd', '15088132331', '9002', '529100', '14', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('23', '梁炳坚', 'ccc', 'ccc', '8002', 'ccc', 'ccc', '15088132331', 'asd', '15008131211', '9004', '529100', '5', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('24', '去4', '梁启超附近', '广东省江门市', '8004', '唔day除', '周柏豪', '15088132331', '广东省江门市唔day除边之', '15088132331', '9003', '529100', '23', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('25', '去6', '你猜', '广东省江门市场', '8003', '你猜', '许廷铿', '15088132547', '阿斯顿', '15088135478', '9002', '529100', '25', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('26', '去7', '阿斯顿', '阿斯顿', '8003', '阿斯顿', '阿斯顿', '15088132354', '阿斯顿', '15088132354', '9002', '529100', '26', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('27', '去8', '阿斯顿', '请问', '8002', '武器恩', '武器恩', '15088132354', '阿斯顿', '15088132354', '9003', '529100', '27', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('28', '去11', '阿斯顿', '阿斯顿', '8002', '自行车', '在线', '15088132354', '覆盖', '15088132354', '9003', '529100', '30', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('29', '去20', '广泛大概', '幅度高达', '8003', '飞抵股', '飞抵股', '15088132354', 'wqr奋斗', '15088132354', '9003', '529100', '32', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('30', '去19', '千万人电饭锅', '鬼地方', '8001', '低速复苏的', '斯蒂芬', '15088132354', '打发似的', '15088132354', '9004', '529100', '33', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('31', '去21', '宽和高', '华国锋的', '8003', '幅度高达', '电饭锅', '15088132354', '阿斯顿', '15088132354', '9004', '521011', '31', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('32', '去17', '阿斯顿', '阿斯顿', '8002', '第三方', '风驰电逝', '15088132354', '离开', '15088132354', '9003', '524874', '35', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('33', '去14', '请问', '特务让他', '8003', '鄂武认为', '认为', '15088132354', '的', '15088132354', '9002', '521447', '38', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('34', '去13', '撒旦', '阿斯顿', '8003', '撒旦', '请问', '15088132354', '15088132354', '15088132354', '9003', '524847', '39', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('35', '去31', '请问', '发送', '8004', '发', '三国时代', '15088132354', '可i结核病', '15088132354', '9004', '524874', '41', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('36', '去29', '阿萨德千万', '我去恶趣味', '8002', '热舞认为', '该方法', '15088132354', '请问', '15088132354', '9002', '529100', '43', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('37', '去25', '请问', '请问', '8001', '韩国的风格', '电饭锅的', '15088132354', '15088132354', '15088132354', '9006', '524874', '47', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('38', '去40', '厕所塞', '测试', '8001', '韩国进口', '阿斯顿', '15088132354', '15088132354', '15088132354', '9004', '529100', '52', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('39', '去34', '阿斯顿', '阿斯顿', '8002', '阿斯顿', '阿斯顿', '15088132354', '15088132354', '15088132354', '9002', '529100', '58', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('40', '去51', '阿斯顿', '阿斯顿', '8002', '阿斯顿', '阿斯顿', '15088132354', '15088132354', '15088132354', '9001', '529100', '61', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('41', '去47', '阿斯顿', '阿斯顿', '8003', '阿斯顿', '阿斯顿', '15088132354', '15088132354', '15088132354', '9003', '529100', '64', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('42', '去50', '阿斯顿', '阿斯顿', '8003', '请问额', '请问', '15088132354', '请问', '15088132354', '9002', '529100', '66', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('43', '去46', '在线', '在线', '8002', '在线', '在线', '15088132354', '安装', '15088132354', '9003', '529100', '65', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('44', '去48', '电饭锅', '大范甘迪', '8003', '地方', '斯蒂芬', '15088132354', '请问 完全 期望度去', '15088132354', '9003', '529100', '63', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('45', '去42', '阿斯顿', '阿斯顿', '8002', '阿斯顿', '阿斯顿', '15088132354', '阿斯顿', '15088132354', '9004', '529100', '70', '10003', '10003');
INSERT INTO `stu_graduate_info` VALUES ('46', '梁炳坚', '阿斯顿', '阿斯顿', '8002', '阿斯顿', '撒旦', '15088132331', '撒旦', '15088132331', '9002', '529100', '20', '10001', '10001');
INSERT INTO `stu_graduate_info` VALUES ('48', '去44', '123', '123', '8002', '123', '123', '15088132331', 'asd', '15088132331', '9001', '529100', '68', '10001', '10001');
INSERT INTO `stu_graduate_info` VALUES ('50', '去45', '测试', '测试', '8001', '测试', '测试', '15088132331', '阿斯顿', '15088132331', '9004', '529100', '67', '10001', '10001');
INSERT INTO `stu_graduate_info` VALUES ('51', '梁炳坚', '测试2', '测试2', '8002', '测试2', '测试2', '15088132331', '阿斯顿', '15088132331', '9002', '529100', '71', '10001', '10001');
INSERT INTO `stu_graduate_info` VALUES ('52', '梁炳坚', '政府', '', '8002', '', '', '', '', '', '9001', '', '72', '10001', '10001');

-- ----------------------------
-- Table structure for stu_major_field
-- ----------------------------
DROP TABLE IF EXISTS `stu_major_field`;
CREATE TABLE `stu_major_field` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `DEPARTMENT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ib58aod0ssslbydugoetd3y59` (`DEPARTMENT_ID`),
  CONSTRAINT `FK_ib58aod0ssslbydugoetd3y59` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `stu_department` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_major_field
-- ----------------------------
INSERT INTO `stu_major_field` VALUES ('1', '计算机科学与技术', '计算机学院的一个专业', '1');
INSERT INTO `stu_major_field` VALUES ('2', '软件工程', '计算机学院的一个专业', '1');
INSERT INTO `stu_major_field` VALUES ('3', '土木工程', '土木工程', '2');
INSERT INTO `stu_major_field` VALUES ('4', '唱歌', '唱歌', '3');
INSERT INTO `stu_major_field` VALUES ('5', '舞蹈', '舞蹈', '3');
INSERT INTO `stu_major_field` VALUES ('6', '嵌入式', '嵌入式', '4');
INSERT INTO `stu_major_field` VALUES ('7', '机器人', '机器人', '4');
INSERT INTO `stu_major_field` VALUES ('8', '水泥测试', '水泥测试', '5');
INSERT INTO `stu_major_field` VALUES ('9', '环境评估', '环境评估', '5');
INSERT INTO `stu_major_field` VALUES ('10', '语言学', '语言学', '6');
INSERT INTO `stu_major_field` VALUES ('11', '文绉绉', '文绉绉', '6');
INSERT INTO `stu_major_field` VALUES ('12', '日语', '日语', '7');
INSERT INTO `stu_major_field` VALUES ('13', '英语', '英语', '7');
INSERT INTO `stu_major_field` VALUES ('14', '韩语', '韩语', '7');
INSERT INTO `stu_major_field` VALUES ('15', '法语', '法语', '7');
INSERT INTO `stu_major_field` VALUES ('16', '金融', '金融', '8');
INSERT INTO `stu_major_field` VALUES ('17', '国际贸易', '国贸', '8');
INSERT INTO `stu_major_field` VALUES ('19', '网络工程', '计算机新添加的专业', '1');

-- ----------------------------
-- Table structure for stu_student
-- ----------------------------
DROP TABLE IF EXISTS `stu_student`;
CREATE TABLE `stu_student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `CODE` varchar(255) NOT NULL,
  `SCHOOL_STATUS` int(11) DEFAULT NULL,
  `DEPARTMENT_ID` int(11) DEFAULT NULL,
  `CLAZZ_ID` int(11) DEFAULT NULL,
  `STUDENTINFO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_e6g68j9ym8d15ghyhpl2q15ny` (`STUDENTINFO`),
  KEY `FK_651v643ei7dfhb71cmcy3vb9e` (`DEPARTMENT_ID`),
  KEY `FK_dih0rmld1l3fmhey82b6d8uh4` (`CLAZZ_ID`),
  CONSTRAINT `FK_651v643ei7dfhb71cmcy3vb9e` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `stu_department` (`ID`),
  CONSTRAINT `FK_dih0rmld1l3fmhey82b6d8uh4` FOREIGN KEY (`CLAZZ_ID`) REFERENCES `stu_class` (`ID`),
  CONSTRAINT `FK_e6g68j9ym8d15ghyhpl2q15ny` FOREIGN KEY (`STUDENTINFO`) REFERENCES `stu_student_info` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_student
-- ----------------------------
INSERT INTO `stu_student` VALUES ('2', '小j', '3112002711', '5002', '1', '8', '10');
INSERT INTO `stu_student` VALUES ('4', '梁炳坚', '3112002712', '5002', '4', '12', '48');
INSERT INTO `stu_student` VALUES ('5', '梁炳坚', '3112002715', '5001', '1', '8', '47');
INSERT INTO `stu_student` VALUES ('6', '小i', '3112002713', '5002', '5', '3', '31');
INSERT INTO `stu_student` VALUES ('7', '邝静文', '3112002714', '5004', '1', '10', '42');
INSERT INTO `stu_student` VALUES ('8', '梁炳坚', '3112002716', '5002', '6', '15', '43');
INSERT INTO `stu_student` VALUES ('9', '梁炳坚', '3112002717', '5003', '1', '8', '45');
INSERT INTO `stu_student` VALUES ('10', '哈哈', '3112002718', '5002', '1', '8', '49');
INSERT INTO `stu_student` VALUES ('11', '梁炳坚', '3112002719', '5004', '1', '1', '76');
INSERT INTO `stu_student` VALUES ('12', '梁炳坚', '3112002709', '5004', '1', '1', '63');
INSERT INTO `stu_student` VALUES ('13', '邝静文', '3112002708', '5001', '1', '10', '41');
INSERT INTO `stu_student` VALUES ('14', '梁炳坚', '3112002707', '5002', '1', '1', '68');
INSERT INTO `stu_student` VALUES ('15', '梁炳坚', '3112002721', '5002', '1', '1', '62');
INSERT INTO `stu_student` VALUES ('16', '梁炳坚', '3112002722', '5002', '2', '11', '46');
INSERT INTO `stu_student` VALUES ('17', '梁炳坚', '3112002723', '5002', '1', '1', '64');
INSERT INTO `stu_student` VALUES ('18', '梁炳坚', '3112002724', '5007', '1', '1', '65');
INSERT INTO `stu_student` VALUES ('19', '梁炳坚', '3112002725', '5007', '1', '1', '66');
INSERT INTO `stu_student` VALUES ('20', '梁炳坚', '3112002729', '5002', '3', '2', '70');
INSERT INTO `stu_student` VALUES ('21', '梁炳坚', '3112002727', '5007', '1', '1', '72');
INSERT INTO `stu_student` VALUES ('22', '去3', '3112002728', '5002', '1', '1', '88');
INSERT INTO `stu_student` VALUES ('23', '去4', '3112002730', '5007', '1', '10', '89');
INSERT INTO `stu_student` VALUES ('24', '去5', '3112002731', '5002', '1', '9', '90');
INSERT INTO `stu_student` VALUES ('25', '去6', '3112002732', '5007', '1', '9', '91');
INSERT INTO `stu_student` VALUES ('26', '去7', '3112002733', '5007', '2', '11', '92');
INSERT INTO `stu_student` VALUES ('27', '去8', '3112002734', '5007', '2', '11', '93');
INSERT INTO `stu_student` VALUES ('28', '去9', '3112002735', '5002', '2', '11', '94');
INSERT INTO `stu_student` VALUES ('29', '去10', '3112002736', '5002', '3', '13', '95');
INSERT INTO `stu_student` VALUES ('30', '去11', '3112002737', '5007', '3', '13', '96');
INSERT INTO `stu_student` VALUES ('31', '去21', '3112002738', '5007', '4', '7', '106');
INSERT INTO `stu_student` VALUES ('32', '去20', '3112002739', '5007', '4', '6', '105');
INSERT INTO `stu_student` VALUES ('33', '去19', '3112002740', '5007', '4', '5', '104');
INSERT INTO `stu_student` VALUES ('34', '去18', '3112002741', '5002', '4', '12', '103');
INSERT INTO `stu_student` VALUES ('35', '去17', '3112002742', '5007', '4', '12', '102');
INSERT INTO `stu_student` VALUES ('36', '去16', '3112002743', '5002', '4', '12', '101');
INSERT INTO `stu_student` VALUES ('37', '去15', '3112002744', '5007', '3', '2', '100');
INSERT INTO `stu_student` VALUES ('38', '去14', '3112002745', '5007', '3', '2', '99');
INSERT INTO `stu_student` VALUES ('39', '去13', '3112002746', '5007', '3', '2', '98');
INSERT INTO `stu_student` VALUES ('40', '去12', '3112002747', '5002', '3', '13', '97');
INSERT INTO `stu_student` VALUES ('41', '去31', '3112002748', '5007', '6', '16', '116');
INSERT INTO `stu_student` VALUES ('42', '去30', '3112002749', '5007', '6', '15', '115');
INSERT INTO `stu_student` VALUES ('43', '去29', '3112002750', '5007', '6', '15', '114');
INSERT INTO `stu_student` VALUES ('44', '去28', '3112002751', '5002', '6', '15', '113');
INSERT INTO `stu_student` VALUES ('45', '去27', '3112002752', '5002', '5', '14', '112');
INSERT INTO `stu_student` VALUES ('46', '去26', '3112002753', '5007', '5', '14', '111');
INSERT INTO `stu_student` VALUES ('47', '去25', '3112002754', '5007', '5', '14', '110');
INSERT INTO `stu_student` VALUES ('48', '去24', '3112002755', '5007', '5', '3', '109');
INSERT INTO `stu_student` VALUES ('49', '去23', '3112002756', '5002', '5', '3', '108');
INSERT INTO `stu_student` VALUES ('50', '去22', '3112002757', '5007', '5', '3', '107');
INSERT INTO `stu_student` VALUES ('51', '去41', '3112002758', '5002', '7', '20', '126');
INSERT INTO `stu_student` VALUES ('52', '去40', '3112002759', '5007', '7', '20', '125');
INSERT INTO `stu_student` VALUES ('53', '去39', '3112002760', '5002', '7', '19', '124');
INSERT INTO `stu_student` VALUES ('54', '去38', '3112002761', '5007', '7', '19', '123');
INSERT INTO `stu_student` VALUES ('55', '去37', '3112002762', '5002', '7', '19', '122');
INSERT INTO `stu_student` VALUES ('56', '去36', '3112002763', '5007', '7', '18', '121');
INSERT INTO `stu_student` VALUES ('57', '去35', '3112002764', '5002', '7', '18', '120');
INSERT INTO `stu_student` VALUES ('58', '去34', '3112002765', '5007', '7', '18', '119');
INSERT INTO `stu_student` VALUES ('59', '去33', '3112002766', '5002', '6', '16', '118');
INSERT INTO `stu_student` VALUES ('60', '去32', '3112002767', '5007', '6', '16', '117');
INSERT INTO `stu_student` VALUES ('61', '去51', '3112002768', '5007', '8', '17', '136');
INSERT INTO `stu_student` VALUES ('62', '去49', '3112002769', '5002', '8', '17', '134');
INSERT INTO `stu_student` VALUES ('63', '去48', '3112002770', '5007', '8', '22', '133');
INSERT INTO `stu_student` VALUES ('64', '去47', '3112002771', '5007', '8', '22', '132');
INSERT INTO `stu_student` VALUES ('65', '去46', '3112002772', '5007', '8', '22', '131');
INSERT INTO `stu_student` VALUES ('66', '去50', '3112002773', '5007', '8', '17', '135');
INSERT INTO `stu_student` VALUES ('67', '去45', '3112002775', '5007', '7', '21', '130');
INSERT INTO `stu_student` VALUES ('68', '去44', '3112002776', '5002', '7', '21', '129');
INSERT INTO `stu_student` VALUES ('69', '去43', '3112002777', '5007', '7', '21', '128');
INSERT INTO `stu_student` VALUES ('70', '去42', '3112002778', '5007', '7', '20', '127');
INSERT INTO `stu_student` VALUES ('71', '梁炳坚', '3112002779', '5007', '1', '1', '77');
INSERT INTO `stu_student` VALUES ('72', '梁炳坚', '3112002888', '5002', '1', '1', '75');

-- ----------------------------
-- Table structure for stu_student_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_student_info`;
CREATE TABLE `stu_student_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXAM_NUM` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `GRADUATION_SCHOOL` varchar(255) DEFAULT NULL,
  `SEX_TYPE` int(11) DEFAULT NULL,
  `MAJOR_FIELD_ID` int(11) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `ID_CARD` varchar(255) DEFAULT NULL,
  `NATION_TYPE` int(11) DEFAULT NULL,
  `POLITICAL_FEATURE_TYPE` int(11) DEFAULT NULL,
  `NATIVE_PLACE` varchar(255) DEFAULT NULL,
  `HOUSEHOLD_TYPE` int(11) DEFAULT NULL,
  `HOUSEHOLD_PLACE` varchar(255) DEFAULT NULL,
  `SCHOOL_LENGTH_TYPE` int(11) DEFAULT NULL,
  `EDUCATION_TYPE` int(11) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `ENTER_COLLEGE_TIME` date DEFAULT NULL,
  `POST_ENCODING` varchar(255) DEFAULT NULL,
  `GRADUATE_TIME` date DEFAULT NULL,
  `DEPARTMENT_ADUIT_STATUS` int(11) DEFAULT NULL,
  `SCHOOL_ADUIT_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_qdtgc45nccwcsc2q76o2iiesm` (`MAJOR_FIELD_ID`),
  CONSTRAINT `FK_qdtgc45nccwcsc2q76o2iiesm` FOREIGN KEY (`MAJOR_FIELD_ID`) REFERENCES `stu_major_field` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_student_info
-- ----------------------------
INSERT INTO `stu_student_info` VALUES ('1', '100001', '小i', '梁启超纪念中学', '1002', '8', '2015-07-15', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('2', '100002', '小b', '梁启超纪念中学', '1002', '2', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxxxxxxxxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('3', '100003', '小c', '梁启超纪念中学', '1001', '13', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('4', '100004', '小d', '梁启超纪念中学', '1002', '5', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('5', '100005', '小e', '梁启超纪念中学', '1001', '4', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('7', '100007', '小g', '梁启超纪念中学', '1002', '4', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('8', '100008', '小h', '梁启超纪念中学', '1001', '7', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132332', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('9', '100009', '小i', '梁启超纪念中学', '1002', '3', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxxasd', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('10', '100010', '小j', '梁启超纪念中学', '1001', '2', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('11', '100011', '小i', '梁启超纪念中学', '1002', '8', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('12', '100012', '小i', '梁启超纪念中学', '1002', '8', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('16', '100013', '小i', '梁启超纪念中学', '1002', '8', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('31', '100019', '小i', '梁启超纪念中学', '1002', '8', '2015-07-14', '123456789011233333', '18001', '3001', '广东江门', '4001', '新会会城仁义xxx', '6001', '7001', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('33', '100020', '梁炳坚', '梁启超纪念中学', '1001', '1', '2015-07-22', '123456789011233333', '18008', '3002', '江门新会', '4002', 'asd', '6002', '7007', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('35', '100021', '梁炳坚', '梁启超纪念中学', '1002', '5', '2015-07-05', '123456789011233333', '18007', '3003', '江门', '4003', 'ASD', '6003', '7008', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('36', '100022', '李向宇一哥', '逗逼学校一哥学校', '1001', '4', '2015-07-29', '123456789011233333', '18004', '3002', '肇庆怀集', '4002', '请问', '6002', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10002');
INSERT INTO `stu_student_info` VALUES ('37', '100023', '梁炳坚', '梁启超纪念中学', '1001', '11', '2015-07-29', '123456789011233333', '18007', '3001', '江门新会啊啊啊啊', '4001', 'asd', '6002', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10002');
INSERT INTO `stu_student_info` VALUES ('38', '100024', '梁炳坚123', '梁启超纪念中学111', '1001', '13', '2015-07-31', '123456789011233333', '18007', '3002', '江门', '4002', '123', '6003', '7003', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('39', '100025', '李向宇', '怀集一中（牛吧）', '1002', '3', '2015-07-29', '123456789011233333', '18001', '3001', '广东肇庆', '4002', '肇庆怀集', '6001', '7004', '15088132324', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('40', '100026', '邝静文', '江门一中', '1002', '2', '2015-07-31', '123456789011233333', '18005', '3002', '飞香港', '4003', '美国洛杉矶\r\n的确厉害', '6003', '7008', '15088125478', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('41', '100027', '邝静文', '江门一中', '1002', '2', '2015-07-31', '123456789011233333', '18005', '3002', '飞香港', '4003', '美国洛杉矶\r\n的确厉害', '6003', '7008', '15088125478', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('42', '100028', '邝静文', '江门一中', '1002', '2', '2015-07-31', '123456789011233333', '18005', '3002', '飞香港', '4003', '美国洛杉矶\r\n的确厉害', '6003', '7008', '15088125478', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('43', '100029', '梁炳坚', '梁启超纪念中学', '1001', '10', '2015-08-20', '123456789011233333', '18003', '3002', '江门新会', '4002', '123\r\nqwe', '6002', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('45', '10086', '梁炳坚', '梁启超纪念中学', '1001', '13', '2015-08-01', '123456789011233333', '18005', '3001', '江门新会啊啊啊啊', '4002', 'xxx', '6002', '7006', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('46', '10087', '梁炳坚', '梁启超纪念中学', '1001', '3', '2015-08-05', '123456789011233333', '18006', '3003', '江门', '4001', 'xxx', '6001', '7006', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('47', '10086s', '梁炳坚', '梁启超纪念中学', '1001', '2', '2015-08-20', '123456789011233333', '18002', '3002', '江门新会啊啊啊啊', '4001', 'xxx', '6003', '7006', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('48', '100867', '梁炳坚', '梁启超纪念中学', '1002', '6', '2015-08-26', '123456789011233333', '18008', '3002', '江门新会啊啊啊啊', '4002', '测试', '6002', '7006', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('49', '1008671', '哈哈', '梁启超纪念中学的小傻逼', '1001', '17', '2015-08-26', '123456789011233333', '18003', '3003', '江门新会啊啊啊啊', '4002', '侧四2', '6002', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('50', '100862', '梁炳坚', '梁启超纪念中学', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10004');
INSERT INTO `stu_student_info` VALUES ('51', '100863', '梁炳坚', '梁启超纪念中学', '1001', '5', '2015-08-25', '123456789011233333', '18006', '3003', '江门', '4003', 'asd', '6003', '7007', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('52', '100864', '梁炳坚', '梁启超纪念中学', '1002', '11', '2015-08-26', '123456789011233333', '18007', '3002', '江门新会啊啊啊啊', '4003', 'asd', '6003', '7006', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('53', '100865', '梁炳坚', '梁启超纪念中学', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('54', '100866', '梁炳坚', '梁启超纪念中学', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10004');
INSERT INTO `stu_student_info` VALUES ('55', '100867', '梁炳坚', '梁启超纪念中学', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10002');
INSERT INTO `stu_student_info` VALUES ('56', '100868', '梁炳坚', '梁启超纪念中学', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10002');
INSERT INTO `stu_student_info` VALUES ('57', '100869', '梁炳坚', '梁启超纪念中学阿斯顿', '1001', '4', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('58', '100870', '梁炳坚', '', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '', '4003', '', '6003', '7005', '', '2012-09-14', '', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('60', '100872', '梁炳坚', '梁启超纪念中学', '1001', '9', '2015-08-26', '123456789011233333', '18001', '3003', '江门新会啊啊啊啊', '4003', '厕所股', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('61', '1008611', '梁炳坚', '梁启超纪念中学', '1001', '14', '2015-08-25', '123456789011233333', '18007', '3003', '江门', '4003', 'asd ', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('62', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('63', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('64', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('65', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('66', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('67', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10004');
INSERT INTO `stu_student_info` VALUES ('68', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('69', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('70', '10086', '梁炳坚', '新会一中', '1001', '5', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('71', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('72', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('73', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('74', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('75', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('76', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('77', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('78', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('79', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('80', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('81', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('82', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10001');
INSERT INTO `stu_student_info` VALUES ('83', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('84', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('85', '10086', '梁炳坚', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('86', '10001', '去1', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('87', '10002', '去2', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10002', '10001');
INSERT INTO `stu_student_info` VALUES ('88', '10003', '去3', '新会一中', '1001', '1', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('89', '10004', '去4', '新会一中', '1001', '2', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('90', '10005', '去5', '新会一中', '1001', '2', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('91', '10006', '去6', '新会一中', '1001', '2', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('92', '10007', '去7', '新会一中', '1001', '3', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('93', '10008', '去8', '新会一中', '1001', '3', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('94', '10009', '去9', '新会一中', '1001', '3', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('95', '10010', '去10', '新会一中', '1001', '4', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('96', '10011', '去11', '新会一中', '1001', '4', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('97', '10012', '去12', '新会一中', '1001', '4', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('98', '10013', '去13', '新会一中', '1001', '5', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('99', '10014', '去14', '新会一中', '1001', '5', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('100', '10015', '去15', '新会一中', '1001', '5', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('101', '10016', '去16', '新会一中', '1001', '6', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('102', '10017', '去17', '新会一中', '1001', '6', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('103', '10018', '去18', '新会一中', '1001', '6', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('104', '10019', '去19', '新会一中', '1001', '7', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('105', '10020', '去20', '新会一中', '1001', '7', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('106', '10021', '去21', '新会一中', '1001', '7', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('107', '10022', '去22', '新会一中', '1001', '8', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('108', '10023', '去23', '新会一中', '1001', '8', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('109', '10024', '去24', '新会一中', '1001', '8', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('110', '10025', '去25', '新会一中', '1001', '9', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('111', '10026', '去26', '新会一中', '1001', '9', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('112', '10027', '去27', '新会一中', '1001', '9', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('113', '10028', '去28', '新会一中', '1001', '10', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('114', '10029', '去29', '新会一中', '1001', '10', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('115', '10030', '去30', '新会一中', '1001', '10', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('116', '10031', '去31', '新会一中', '1001', '11', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('117', '10032', '去32', '新会一中', '1001', '11', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('118', '10033', '去33', '新会一中', '1001', '11', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('119', '10034', '去34', '新会一中', '1001', '12', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('120', '10035', '去35', '新会一中', '1001', '12', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('121', '10036', '去36', '新会一中', '1001', '12', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('122', '10037', '去37', '新会一中', '1001', '13', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('123', '10038', '去38', '新会一中', '1001', '13', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('124', '10039', '去39', '新会一中', '1001', '13', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('125', '10040', '去40', '新会一中', '1001', '14', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('126', '10041', '去41', '新会一中', '1001', '14', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('127', '10042', '去42', '新会一中', '1001', '14', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('128', '10043', '去43', '新会一中', '1001', '15', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('129', '10044', '去44', '新会一中', '1001', '15', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('130', '10045', '去45', '新会一中', '1001', '15', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('131', '10046', '去46', '新会一中', '1001', '16', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('132', '10047', '去47', '新会一中', '1001', '16', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('133', '10048', '去48', '新会一中', '1001', '16', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('134', '10049', '去49', '新会一中', '1001', '17', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('135', '10050', '去50', '新会一中', '1001', '17', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('136', '10051', '去51', '新会一中', '1001', '17', '2015-09-02', '123456789011233333', '18007', '3002', '新会', '4003', '阿斯顿', '6003', '7005', '15088132331', '2012-09-14', '529100', '2016-06-23', '10003', '10003');
INSERT INTO `stu_student_info` VALUES ('137', '311200277777', '阿斯顿', '请问', '1001', '3', '2015-08-27', '123456789011233333', '18002', '3002', '阿斯顿', '4002', '阿斯顿', '6002', '7003', '15088132331', '2012-09-14', '529100', '2016-06-23', '10001', '10001');
INSERT INTO `stu_student_info` VALUES ('138', '10086ss', '高科技', '梁启超', '1001', '1', '2015-09-04', '123456789011233333', '18001', '3001', '', '4001', '', '6001', '7001', '', '2012-09-14', '529100', '2016-06-23', '10001', '10001');

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL,
  `CREATE_TIME` date NOT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `AUTH_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_cblauufug9uwne4cigpirfeac` (`PARENT_ID`),
  KEY `FK_iqn65fmc6mp1q1047990obkc6` (`AUTH_ID`),
  CONSTRAINT `FK_cblauufug9uwne4cigpirfeac` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_auth` (`ID`),
  CONSTRAINT `FK_iqn65fmc6mp1q1047990obkc6` FOREIGN KEY (`AUTH_ID`) REFERENCES `sys_auth` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES ('1', '生源信息管理', '', 'icon-man', '2015-08-29', '47', '学生生源信息管理', null);
INSERT INTO `sys_auth` VALUES ('2', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', 'icon-sum', '2015-08-29', '1', '学生源信息列表', null);
INSERT INTO `sys_auth` VALUES ('3', '学院审核', '/StudentInfo_List_toDepartmentAduitPage.action', 'icon-edit', '2015-08-29', '1', '学院审核学生源信息', null);
INSERT INTO `sys_auth` VALUES ('4', '学校审核', '/StudentInfo_List_toSchoolAduitPage.action', 'icon-edit', '2015-08-29', '1', '学校审核学生源信息', null);
INSERT INTO `sys_auth` VALUES ('5', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '', '2015-08-29', '2', '获取学生源信息列表', null);
INSERT INTO `sys_auth` VALUES ('6', '添加学生源信息页面', '/StudentInfo_AddNewInfo_toAddStudentInfoPage.action', '', '2015-08-29', '2', '进入添加学生源信息的页面', null);
INSERT INTO `sys_auth` VALUES ('7', '编辑学生源信息页面', '/StudentInfo_EditStuInfo_toEditStudentInfoPage.action', '', '2015-08-29', '2', '进入编辑学生源信息的页面', null);
INSERT INTO `sys_auth` VALUES ('8', '添加学生源信息', '/StudentInfo_AddNewInfo_addStudentInfo.action', '', '2015-08-29', '2', '添加学生源信息', null);
INSERT INTO `sys_auth` VALUES ('9', '编辑学生源信息', '/StudentInfo_EditStuInfo_editStudentInfo.action', '', '2015-08-29', '2', '编辑学生源信息', null);
INSERT INTO `sys_auth` VALUES ('10', '删除学生源信息', '/StudentInfo_DelStuInfo_toDelStuInfo.action', '', '2015-08-29', '2', '删除学生源信息', null);
INSERT INTO `sys_auth` VALUES ('11', '申请院系审核', '/StudentInfo_AduitStuInfo_toDoApplyDepartmentAduit.action', '', '2015-08-29', '2', '（学生源信息）申请学院审核', null);
INSERT INTO `sys_auth` VALUES ('12', '取消申请院系审核', '/StudentInfo_AduitStuInfo_toDoCacelApplyDepartmentAduit.action', '', '2015-08-29', '2', '（学生源信息）取消申请学院审核', null);
INSERT INTO `sys_auth` VALUES ('13', '获取学院审核学生源信息列表', '/StudentInfo_List_getDepartmentAduitList.action', '', '2015-08-29', '3', '（学生源信息）获取学院审核列表', null);
INSERT INTO `sys_auth` VALUES ('14', '院系审核通过', '/StudentInfo_AduitStuInfo_toDoDepartmentAduit.action', '', '2015-08-29', '3', '（学生源信息）学院批准审核', null);
INSERT INTO `sys_auth` VALUES ('15', '院系审核不通过', '/StudentInfo_AduitStuInfo_toDoCancelDepartmentAduit.action', '', '2015-08-30', '3', '（学生源信息）学院不批准审核', null);
INSERT INTO `sys_auth` VALUES ('16', '申请学校审核', '/StudentInfo_AduitStuInfo_toDoApplySchoolAduit.action', '', '2015-08-30', '3', '（学生源信息）申请学校审核', null);
INSERT INTO `sys_auth` VALUES ('17', '取消申请学校审核', '/StudentInfo_AduitStuInfo_toDoCacelApplySchoolAduit.action', '', '2015-08-30', '3', '（学生源信息）取消申请学校审核', null);
INSERT INTO `sys_auth` VALUES ('18', '获取学校审核学生源信息列表', '/StudentInfo_List_getSchoolAduitList.action', '', '2015-08-30', '4', '（学生源信息）获取学校审核列表', null);
INSERT INTO `sys_auth` VALUES ('19', '学校审核通过', '/StudentInfo_AduitStuInfo_toDoSchoolAduit.action', '', '2015-08-30', '4', '（学生源信息）学校批准审核', null);
INSERT INTO `sys_auth` VALUES ('20', '学校审核不通过', '/StudentInfo_AduitStuInfo_toDoCancelSchoolAduit.action', '', '2015-08-30', '4', '（学生源信息）学校不批准审核', null);
INSERT INTO `sys_auth` VALUES ('21', '学生信息管理', '', 'icon-man', '2015-08-30', '47', '学生信息管理', null);
INSERT INTO `sys_auth` VALUES ('22', '学生信息列表', '/Student_toStudentListPage.action', 'icon-sum', '2015-08-30', '21', '进入学生信息列表', null);
INSERT INTO `sys_auth` VALUES ('23', '获取学生信息列表', '/Student_getStudentList.action', null, '2015-08-30', '22', '获取学生信息列表', null);
INSERT INTO `sys_auth` VALUES ('24', '查看学生的生源信息', '/StudentDetials_toStudentInfoDetials.action', null, '2015-08-30', '22', '（单个）查看学生的生源信息', null);
INSERT INTO `sys_auth` VALUES ('25', '编辑学生信息页面', '/Student_toEditStudentPage.action', null, '2015-08-30', '22', '进入编辑学生信息的页面', null);
INSERT INTO `sys_auth` VALUES ('26', '编辑学生信息', '/Student_editStudent.action', null, '2015-08-30', '22', '编辑学生信息', null);
INSERT INTO `sys_auth` VALUES ('27', '学生就业信息管理', '', 'icon-theme', '2015-08-30', '47', '学生就业信息管理', null);
INSERT INTO `sys_auth` VALUES ('28', '学生就业信息列表', '/GraduateInfo_List_toGraduateInfoListPage.action', 'icon-theme', '2015-08-30', '27', '进入学生就业信息列表', null);
INSERT INTO `sys_auth` VALUES ('29', '学院审核', '/GraduateInfo_List_toDepartmentAduitPage.action', 'icon-edit', '2015-08-30', '27', '学院审核学生就业信息', null);
INSERT INTO `sys_auth` VALUES ('30', '学校审核', '/GraduateInfo_List_toSchoolAduitPage.action', 'icon-edit', '2015-08-30', '27', '学校审核学生就业信息', null);
INSERT INTO `sys_auth` VALUES ('31', '获取学生就业信息列表', '/GraduateInfo_List_getGraduateInfoList.action', null, '2015-08-30', '28', '获取学生就业信息列表', null);
INSERT INTO `sys_auth` VALUES ('32', '添加学生就业信息页面', '/GraduateInfo_Add_toAddGraduateInfoPage.action', null, '2015-08-30', '28', '添加学生就业信息页面', null);
INSERT INTO `sys_auth` VALUES ('33', '编辑学生就业信息页面', '/GraduateInfo_Edit_toEditGraduateInfoPage.action', null, '2015-08-30', '28', '编辑学生就业信息页面', null);
INSERT INTO `sys_auth` VALUES ('34', '添加学生就业信息', '/GraduateInfo_Add_addGraudateInfo.action', null, '2015-08-30', '28', '添加学生就业信息', null);
INSERT INTO `sys_auth` VALUES ('35', '编辑学生就业信息', '/GraduateInfo_Edit_editGraduateInfo.action', null, '2015-08-30', '28', '编辑学生就业信息', null);
INSERT INTO `sys_auth` VALUES ('36', '删除学生就业信息', '/GraduateInfo_Del_toDelGraduateInfo.action', null, '2015-08-30', '28', '删除学生就业信息', null);
INSERT INTO `sys_auth` VALUES ('37', '申请院系审核', '/GraduateInfo_Aduit_toDoApplyDepartmentGAduit.action', null, '2015-08-30', '28', '（学生就业信息）申请学院审核', null);
INSERT INTO `sys_auth` VALUES ('38', '取消申请院系审核', '/GraduateInfo_Aduit_toDoCancelApplyDepartmentGAduit.action', null, '2015-08-30', '28', '（学生就业信息）取消申请学院审核', null);
INSERT INTO `sys_auth` VALUES ('39', '获取学院审核学生就业信息列表', '/GraduateInfo_List_getDepartmentAduitList.action', null, '2015-08-30', '29', '（学生就业信息）获取学院审核列表', null);
INSERT INTO `sys_auth` VALUES ('40', '学院审核通过', '/GraduateInfo_Aduit_toDoDepartmentGAduit.action', null, '2015-08-30', '29', '（学生就业信息）学院批准审核', null);
INSERT INTO `sys_auth` VALUES ('41', '学院审核不通过', '/GraduateInfo_Aduit_toDoCancelDepartmentGAduit.action', null, '2015-08-30', '29', '（学生就业信息）学院不批准审核', null);
INSERT INTO `sys_auth` VALUES ('42', '申请学校审核', '/GraduateInfo_Aduit_toDoApplySchoolGAduit.action', null, '2015-08-30', '29', '（学生就业信息）申请学校审核', null);
INSERT INTO `sys_auth` VALUES ('43', '取消申请学校审核', '/GraduateInfo_Aduit_toDoCancelApplySchoolGAduit.action', null, '2015-08-30', '29', '（学生就业信息）取消申请学校审核', null);
INSERT INTO `sys_auth` VALUES ('44', '获取学校审核学生就业信息列表', '/GraduateInfo_List_getSchoolAduitList.action', null, '2015-08-30', '30', '（学生就业信息）获取学校审核列表', null);
INSERT INTO `sys_auth` VALUES ('45', '学校审核通过', '/GraduateInfo_Aduit_toDoSchoolGAduit.action', null, '2015-08-30', '30', '（学生就业信息）学校批准审核', null);
INSERT INTO `sys_auth` VALUES ('46', '学校审核不通过', '/GraduateInfo_Aduit_toDoCancelSchoolGAduit.action', null, '2015-08-30', '30', '（学生就业信息）学校不批准审核', null);
INSERT INTO `sys_auth` VALUES ('47', '毕业生信息管理', '', 'icon-group', '2015-08-30', null, '毕业生信息管理模块', null);
INSERT INTO `sys_auth` VALUES ('49', '用人单位信息管理', '', 'icon-company', '2015-08-30', null, '用人单位信息管理模块', null);
INSERT INTO `sys_auth` VALUES ('50', '单位基本信息', '', 'icon-org', '2015-08-30', '49', '招聘单位的基本信息', null);
INSERT INTO `sys_auth` VALUES ('52', '用人单位基本信息列表', '/EmployingUnit_List_toEmpUnitListPage.action', 'icon-company', '2015-08-30', '50', '进入用人单位基本信息列表页面', null);
INSERT INTO `sys_auth` VALUES ('53', '招聘信息列表', '/RecruitmentInfo_List_toRecruitmentInfoListPage.action', '', '2015-08-30', '50', '进入招聘信息列表页面', null);
INSERT INTO `sys_auth` VALUES ('54', '获取用人单位基本信息列表', '/EmployingUnit_List_getEmpUnitList.action', null, '2015-08-30', '52', '获取用人单位基本信息列表', null);
INSERT INTO `sys_auth` VALUES ('55', '添加用人单位基本信息页面', '/EmployingUnit_Add_toAddEmpUnitPage.action', null, '2015-08-30', '52', '进入添加用人单位基本信息页面', null);
INSERT INTO `sys_auth` VALUES ('56', '编辑用人单位基本信息页面', '/EmployingUnit_Edit_toEditEmpUnitPage.action', null, '2015-08-30', '52', '进入编辑用人单位基本信息页面', null);
INSERT INTO `sys_auth` VALUES ('57', '添加用人单位基本信息', '/EmployingUnit_Add_addEmpUnit.action', null, '2015-08-30', '52', '添加用人单位基本信息', null);
INSERT INTO `sys_auth` VALUES ('58', '编辑用人单位基本信息', '/EmployingUnit_Edit_editEmpUnit.action', null, '2015-08-30', '52', '编辑用人单位基本信息', null);
INSERT INTO `sys_auth` VALUES ('59', '删除用人单位基本信息', '/EmployingUnit_Del_toDelEmpUnit.action', null, '2015-08-30', '52', '删除用人单位基本信息', null);
INSERT INTO `sys_auth` VALUES ('60', '查看所有招聘信息', '/EmployingUnit_InfoList_toRecruitmentInfosPage', null, '2015-08-30', '52', '查看所有招聘信息', null);
INSERT INTO `sys_auth` VALUES ('61', '获取招聘信息列表', '/RecruitmentInfo_List_getRecruitmentInfoList.action', null, '2015-08-30', '53', '获取招聘信息列表', null);
INSERT INTO `sys_auth` VALUES ('62', '添加招聘信息页面', '/RecruitmentInfo_Add_toAddRecruitmentInfoPage.action', null, '2015-08-30', '53', '进入添加招聘信息页面', null);
INSERT INTO `sys_auth` VALUES ('63', '编辑招聘信息页面', '/RecruitmentInfo_Eidt_toEditRecruitmentInfoPage.action', null, '2015-08-30', '53', '进入编辑招聘信息页面', null);
INSERT INTO `sys_auth` VALUES ('64', '添加招聘信息', '/EmployingUnit_Add_addEmpUnit.action', null, '2015-08-30', '53', '添加招聘信息', null);
INSERT INTO `sys_auth` VALUES ('65', '编辑招聘信息', '/EmployingUnit_Edit_editEmpUnit.action', null, '2015-08-30', '53', '编辑招聘信息', null);
INSERT INTO `sys_auth` VALUES ('66', '删除招聘信息', '/RecruitmentInfo_Del_toDelRecruitmentInfo.action', null, '2015-08-30', '53', '删除招聘信息', null);
INSERT INTO `sys_auth` VALUES ('67', '数据管理', '', 'icon-blank', '2015-08-30', null, '数据管理模块', null);
INSERT INTO `sys_auth` VALUES ('68', '数据导入导出页面', '/DataManagement_Show_toDataManagementPage.action', 'icon-panel', '2015-08-30', '67', '数据的导入以及导出（页面）', null);
INSERT INTO `sys_auth` VALUES ('69', '报表统计', '', 'icon-large-chart', '2015-08-30', '67', '报表统计', null);
INSERT INTO `sys_auth` VALUES ('70', '选择毕业时间', '/DataManagement_toChooseTimePage.action', null, '2015-08-30', '69', '选择毕业时间页面', null);
INSERT INTO `sys_auth` VALUES ('71', '毕业率就业率表格统计页面', '/DataManagement_toWorkAndGraduateListPage.action', null, '2015-08-30', '69', '进入就业率毕业率表格统计页面', null);
INSERT INTO `sys_auth` VALUES ('72', '毕业率报表统计', '/DataManagement_getGraduateRate.action', null, '2015-08-30', '69', '毕业率报表统计', null);
INSERT INTO `sys_auth` VALUES ('73', '就业率报表统计', '/DataManagement_getWorkRate.action', null, '2015-08-30', '69', '就业率报表统计', null);
INSERT INTO `sys_auth` VALUES ('74', '导入学生源信息页面', '/DataManagement_Import_toImportStuInfoPage.action', null, '2015-08-31', '68', '进入导入学生源信息(excell)的页面', null);
INSERT INTO `sys_auth` VALUES ('75', '导出学生生源信息页面', '/DataManagement_ExportStuInfos_toExportStuInfosPage.action', null, '2015-08-31', '68', '进入导出学生生源信息(excell)的页面', null);
INSERT INTO `sys_auth` VALUES ('76', '导出就业信息页面', '/DataManagement_ExportGraInfos_toExportGraInfosPage.action', null, '2015-08-31', '68', '进入导出就业信息(excell)的页面', null);
INSERT INTO `sys_auth` VALUES ('77', '导出用人单位的招聘信息页面', '/DataManagement_ExportRecInfos_toExportRecInfosPage.action', null, '2015-08-31', '68', '进入导出用人单位的招聘信息(word)的页面', null);
INSERT INTO `sys_auth` VALUES ('78', '上传学生生源信息（批量添加）', '/DataManagement_Import_uploadStuInfo.action', null, '2015-08-31', '68', '通过excel添加学生生源信息', null);
INSERT INTO `sys_auth` VALUES ('79', '下载学生生源信息excel表格', '/DataManagement_DownloadStuInfosFile.action', null, '2015-08-31', '68', '下载学生生源信息excel表格', null);
INSERT INTO `sys_auth` VALUES ('80', '导出学生生源信息', '/DataManagement_ExportStuInfos_exportStuInfos.action', null, '2015-08-31', '68', '导出学生生源信息(excell)', null);
INSERT INTO `sys_auth` VALUES ('81', '导出就业信息', '/DataManagement_ExportGraInfos_exportGraInfos.action', null, '2015-08-31', '68', '导出就业信息(excell)', null);
INSERT INTO `sys_auth` VALUES ('82', '导出用人单位的招聘信息', '/DataManagement_ExportRecInfos_exportRecInfos.action', null, '2015-08-31', '68', '导出用人单位的招聘信息(word)', null);
INSERT INTO `sys_auth` VALUES ('83', '显示毕业率就业率图页面', '/DataManagement_Graph_toShowGraphPage.action', null, '2015-08-31', '69', '显示毕业率就业率图页面（折线图、饼图、柱状图）', null);
INSERT INTO `sys_auth` VALUES ('84', '显示毕业率就业率图', '/DataManagement_Graph_showGraph.action', null, '2015-08-31', '69', '显示毕业率就业率图（折线图、饼图、柱状图）', null);
INSERT INTO `sys_auth` VALUES ('85', '系统管理', '', 'icon-sys', '2015-08-31', null, '系统管理模块', null);
INSERT INTO `sys_auth` VALUES ('86', '用户管理列表', '/User_toUserListPage.action', 'icon-role', '2015-08-31', '85', '进入用户管理列表页面', null);
INSERT INTO `sys_auth` VALUES ('87', '权限管理列表', '/System_Auth_toAuthListPage.action', 'icon-resource', '2015-08-31', '85', '进入权限管理列表页面', null);
INSERT INTO `sys_auth` VALUES ('88', '获取用户列表数据', '/User_getUserList.action', null, '2015-08-31', '86', '获取用户列表数据', null);
INSERT INTO `sys_auth` VALUES ('89', '添加用户页面', '/System_AddUser_toAddUserPage.action', null, '2015-08-31', '86', '进入添加用户页面', null);
INSERT INTO `sys_auth` VALUES ('90', '编辑用户页面', '/System_EditUser_toEditUserPage.action', null, '2015-08-31', '86', '进入编辑用户页面', null);
INSERT INTO `sys_auth` VALUES ('91', '添加用户', '/System_AddUser_doAddUser.action', null, '2015-08-31', '86', '添加用户', null);
INSERT INTO `sys_auth` VALUES ('92', '编辑用户', '/System_EditUser_doEditUser.action', null, '2015-08-31', '86', '编辑用户', null);
INSERT INTO `sys_auth` VALUES ('93', '删除用户', '/User_toDeleteUser.action', null, '2015-08-31', '86', '删除用户', null);
INSERT INTO `sys_auth` VALUES ('94', '获取组织部门数据', '/Organization_getTree.action', null, '2015-08-31', '86', '获取组织部门数据（树）', null);
INSERT INTO `sys_auth` VALUES ('95', '获取权限列表数据', '/System_Auth_getAuthList.action', '', '2015-08-31', '87', '获取权限列表数据（树网格）', null);
INSERT INTO `sys_auth` VALUES ('96', '添加权限页面', '/System_Auth_toAddAuthPage.action', null, '2015-08-31', '87', '进入添加权限页面', null);
INSERT INTO `sys_auth` VALUES ('97', '编辑权限页面', '/System_Auth_toEditAuthPage.action', null, '2015-08-31', '87', '进入编辑权限页面', null);
INSERT INTO `sys_auth` VALUES ('98', '添加权限', '/System_Auth_doAddAuth.action', null, '2015-08-31', '87', '添加权限', null);
INSERT INTO `sys_auth` VALUES ('99', '编辑权限', '/System_Auth_doEditAuth.action', null, '2015-08-31', '87', '编辑权限', null);
INSERT INTO `sys_auth` VALUES ('100', '删除权限', '/System_Auth_delAuth.action', null, '2015-08-31', '87', '删除权限', null);
INSERT INTO `sys_auth` VALUES ('101', '梁饼测试', '/test', 'icon-add', '2015-08-31', null, '测试', null);
INSERT INTO `sys_auth` VALUES ('102', '修改用户密码页面', '/User_doNotNeedAuth_toEditUserPwd.action', null, '2015-08-31', '86', '主界面的修改用户密码对话框', null);
INSERT INTO `sys_auth` VALUES ('103', '修改用户密码', '/User_doNotNeedAuth_editUserPwd.action', null, '2015-08-31', '86', '修改用户密码', null);
INSERT INTO `sys_auth` VALUES ('104', '角色管理列表', '/System_Role_toRoleListPage.action', 'icon-role', '2015-09-03', '85', '进入角色列表页面', null);
INSERT INTO `sys_auth` VALUES ('105', '获取角色列表数据', '/System_Role_getRoleList.action', null, '2015-09-03', '104', '获取角色列表数据', null);
INSERT INTO `sys_auth` VALUES ('106', '用户授权页面', '/System_Role_toGrentAuthToRolePage.action', null, '2015-09-03', '104', '进入用户授权页面', null);
INSERT INTO `sys_auth` VALUES ('107', '用户授权', '/System_Role_doGrentAuthToRole.action', null, '2015-09-03', '104', '给选中的用户授权', null);
INSERT INTO `sys_auth` VALUES ('108', '添加角色页面', '/System_Role_toAddRolePage.action', null, '2015-09-03', '104', '进入添加角色页面', null);
INSERT INTO `sys_auth` VALUES ('109', '编辑角色页面', '/System_Role_toEditRolePage.action', null, '2015-09-03', '104', '进入编辑角色页面', null);
INSERT INTO `sys_auth` VALUES ('110', '添加角色', '/System_Role_doAddRole.action', null, '2015-09-03', '104', '添加角色', null);
INSERT INTO `sys_auth` VALUES ('111', '编辑角色', '/System_Role_doEditRole.action', null, '2015-09-03', '104', '编辑角色', null);
INSERT INTO `sys_auth` VALUES ('112', '删除角色', '/System_Role_doDeleteRole.action', null, '2015-09-03', '104', '批量删除角色', null);
INSERT INTO `sys_auth` VALUES ('113', '组织部门管理页面', '/Organization_toOrgListPage.action', 'icon-org', '2015-09-03', '85', '进入组织部门列表页面', null);
INSERT INTO `sys_auth` VALUES ('114', '获取组织部门数据', '/Organization_getOrganizationList.action', null, '2015-09-03', '113', '获取组织部门数据', null);
INSERT INTO `sys_auth` VALUES ('115', '添加组织部门页面', '/Organization_addOrganizationPage.action', null, '2015-09-03', '113', '进入添加组织部门页面', null);
INSERT INTO `sys_auth` VALUES ('116', '编辑组织部门页面', '/Organization_toEditOrganizationPage.action', null, '2015-09-03', '113', '进入编辑组织部门页面', null);
INSERT INTO `sys_auth` VALUES ('117', '添加组织部门', '/Organization_doAddOrganization.action', null, '2015-09-03', '113', '添加组织部门', null);
INSERT INTO `sys_auth` VALUES ('118', '编辑组织部门', '/Organization_doEditOrganization.action', null, '2015-09-03', '113', '编辑组织部门', null);
INSERT INTO `sys_auth` VALUES ('119', '删除组织部门', '/Organization_delOrganization.action', null, '2015-09-03', '113', '删除组织部门', null);
INSERT INTO `sys_auth` VALUES ('120', '哇哈哈', '', '', '2015-09-06', null, '阿斯顿', null);

-- ----------------------------
-- Table structure for sys_enumeration
-- ----------------------------
DROP TABLE IF EXISTS `sys_enumeration`;
CREATE TABLE `sys_enumeration` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_enumeration
-- ----------------------------
INSERT INTO `sys_enumeration` VALUES ('1000', '性别类型', 'SEX_TYPE', '性别类型');
INSERT INTO `sys_enumeration` VALUES ('2000', '是否类型', 'BOOLEAN_TYPE', '是否类型');
INSERT INTO `sys_enumeration` VALUES ('3000', '政治面貌类型', 'POLITICAL_FEATURE_TYPE', '政治面貌类型');
INSERT INTO `sys_enumeration` VALUES ('4000', '户籍类型', 'HOUSEHOLD_TYPE', '户籍类型');
INSERT INTO `sys_enumeration` VALUES ('5000', '学籍状态', 'STU_STATUS', '学籍状态');
INSERT INTO `sys_enumeration` VALUES ('6000', '学制类型', 'SCHOOL_LENGTH_TYPE', '学制类型');
INSERT INTO `sys_enumeration` VALUES ('7000', '学历类型', 'EDUCATION_TYPE', '学历类型');
INSERT INTO `sys_enumeration` VALUES ('8000', '单位性质', 'UNIT_TYPE', '单位性质');
INSERT INTO `sys_enumeration` VALUES ('9000', '应聘_招聘类型', 'APPLY_RECRUITMENT_TYPE', '应聘_招聘类型');
INSERT INTO `sys_enumeration` VALUES ('10000', '审核状态', 'ADUIT_STATUS', '审核状态');
INSERT INTO `sys_enumeration` VALUES ('11000', '月薪', 'MONTHLY_SALARY', '月薪');
INSERT INTO `sys_enumeration` VALUES ('12000', '工作性质', 'WORK_TYPE', '工作性质');
INSERT INTO `sys_enumeration` VALUES ('13000', '行业领域', 'INDUSTRY_TYPE', '行业领域');
INSERT INTO `sys_enumeration` VALUES ('14000', '用户状态', 'USER_STATE', '用户状态');
INSERT INTO `sys_enumeration` VALUES ('15000', '新闻类型', 'NEWS_TYPE', '新闻类型');
INSERT INTO `sys_enumeration` VALUES ('18000', '民族类型', 'NATION_TYPE', '民族类型');

-- ----------------------------
-- Table structure for sys_enumeration_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_enumeration_value`;
CREATE TABLE `sys_enumeration_value` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `ENUMERATION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ef0y2xo3pr2mdcygu6kq68qxw` (`ENUMERATION_ID`),
  CONSTRAINT `FK_ef0y2xo3pr2mdcygu6kq68qxw` FOREIGN KEY (`ENUMERATION_ID`) REFERENCES `sys_enumeration` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_enumeration_value
-- ----------------------------
INSERT INTO `sys_enumeration_value` VALUES ('1001', '男', '0', '男', '1000');
INSERT INTO `sys_enumeration_value` VALUES ('1002', '女', '1', '女', '1000');
INSERT INTO `sys_enumeration_value` VALUES ('2001', '是', '0', '是', '2000');
INSERT INTO `sys_enumeration_value` VALUES ('2002', '否', '1', '否', '2000');
INSERT INTO `sys_enumeration_value` VALUES ('3001', '群众', '0', '群众', '3000');
INSERT INTO `sys_enumeration_value` VALUES ('3002', '共青团员', '1', '共青团员', '3000');
INSERT INTO `sys_enumeration_value` VALUES ('3003', '中共党员', '2', '中共党员', '3000');
INSERT INTO `sys_enumeration_value` VALUES ('4001', '农村', '0', '农村', '4000');
INSERT INTO `sys_enumeration_value` VALUES ('4002', '城市', '1', '城市', '4000');
INSERT INTO `sys_enumeration_value` VALUES ('4003', '县镇非农', '2', '县镇非农', '4000');
INSERT INTO `sys_enumeration_value` VALUES ('5001', '未注册', '0', '未注册', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('5002', '正常', '1', '正常', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('5003', '休学', '2', '休学', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('5004', '辍学', '3', '辍学', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('5005', '退学', '4', '退学', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('5006', '参军', '5', '参军', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('5007', '已毕业', '6', '已毕业', '5000');
INSERT INTO `sys_enumeration_value` VALUES ('6001', '三年制', '0', '三年制', '6000');
INSERT INTO `sys_enumeration_value` VALUES ('6002', '四年制', '1', '四年制', '6000');
INSERT INTO `sys_enumeration_value` VALUES ('6003', '五年制', '2', '五年制', '6000');
INSERT INTO `sys_enumeration_value` VALUES ('7001', '小学', '0', '小学', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7002', '初中', '1', '初中', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7003', '高中', '2', '高中', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7004', '专科', '3', '专科', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7005', '本科', '4', '本科', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7006', '成人本科', '5', '成人本科', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7007', '研究生', '6', '研究生', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('7008', '博士', '7', '博士', '7000');
INSERT INTO `sys_enumeration_value` VALUES ('8001', '企业', '0', '企业', '8000');
INSERT INTO `sys_enumeration_value` VALUES ('8002', '事业单位', '1', '事业单位', '8000');
INSERT INTO `sys_enumeration_value` VALUES ('8003', '国家行政机关', '2', '国家行政机关', '8000');
INSERT INTO `sys_enumeration_value` VALUES ('8004', '政府', '3', '政府', '8000');
INSERT INTO `sys_enumeration_value` VALUES ('9001', '现场招聘会', '0', '现场招聘会', '9000');
INSERT INTO `sys_enumeration_value` VALUES ('9002', '网络招聘', '1', '网络招聘', '9000');
INSERT INTO `sys_enumeration_value` VALUES ('9003', '媒体招聘', '2', '媒体招聘', '9000');
INSERT INTO `sys_enumeration_value` VALUES ('9004', '猎头公司', '3', '猎头公司', '9000');
INSERT INTO `sys_enumeration_value` VALUES ('9005', '校园招聘', '5', '校园招聘', '9000');
INSERT INTO `sys_enumeration_value` VALUES ('9006', '宣讲会', '6', '宣讲会', '9000');
INSERT INTO `sys_enumeration_value` VALUES ('10001', '未审核', '0', '未审核', '10000');
INSERT INTO `sys_enumeration_value` VALUES ('10002', '审核中', '1', '审核中', '10000');
INSERT INTO `sys_enumeration_value` VALUES ('10003', '通过', '2', '通过', '10000');
INSERT INTO `sys_enumeration_value` VALUES ('10004', '不通过', '3', '不通过', '10000');
INSERT INTO `sys_enumeration_value` VALUES ('11001', '面议', '0', '面议', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11002', '2k以下', '1', '2k以下', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11003', '2k-5k', '2', '2k-5k', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11004', '5k-10k', '3', '5k-10k', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11005', '10k-15k', '4', '10k-15k', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11006', '15k-25k', '5', '15k-25k', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11007', '25k-50k', '6', '25k-50k', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('11008', '50k以上', '7', '50k以上', '11000');
INSERT INTO `sys_enumeration_value` VALUES ('12001', '不限', '0', '不限', '12000');
INSERT INTO `sys_enumeration_value` VALUES ('12002', '全职', '1', '全职', '12000');
INSERT INTO `sys_enumeration_value` VALUES ('12003', '兼职', '2', '兼职', '12000');
INSERT INTO `sys_enumeration_value` VALUES ('12004', '实习', '3', '实习', '12000');
INSERT INTO `sys_enumeration_value` VALUES ('13001', '机构组织', '0', '机构组织', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13002', '医药卫生', '1', '医药卫生', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13003', '信息产业', '2', '信息产业', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13004', '农林牧渔', '3', '农林牧渔', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13005', '建筑建材', '4', '建筑建材', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13006', '冶金矿产', '5', '冶金矿产', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13007', '石油化工', '6', '石油化工', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13008', '水利水电', '7', '水利水电', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13009', '交通运输', '8', '交通运输', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13010', '机械机电', '9', '机械机电', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13011', '轻工食品', '10', '轻工食品', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13012', '服装纺织', '11', '服装纺织', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13013', '专业服务', '12', '专业服务', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13014', '安全防护', '13', '安全防护', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13015', '环保绿化', '14', '环保绿化', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13016', '旅游休闲', '15', '旅游休闲', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13017', '办公文教', '16', '办公文教', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13018', '电子电工', '17', '电子电工', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13019', '玩具礼品', '18', '玩具礼品', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13020', '家居用品', '19', '家居用品', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13021', '物资', '20', '物资', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13022', '包装', '21', '包装', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13023', '体育', '22', '体育', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('13024', '办公', '23', '办公', '13000');
INSERT INTO `sys_enumeration_value` VALUES ('14001', '正常', '0', '正常', '14000');
INSERT INTO `sys_enumeration_value` VALUES ('14002', '停用', '1', '停用', '14000');
INSERT INTO `sys_enumeration_value` VALUES ('15001', '图片新闻', '1', '图片新闻', '15000');
INSERT INTO `sys_enumeration_value` VALUES ('15002', '宣讲会', '2', '宣讲会', '15000');
INSERT INTO `sys_enumeration_value` VALUES ('15003', '通知公告', '3', '通知公告', '15000');
INSERT INTO `sys_enumeration_value` VALUES ('15004', '政策法规', '4', '政策法规', '15000');
INSERT INTO `sys_enumeration_value` VALUES ('15005', '最新动态', '5', '最新动态', '15000');
INSERT INTO `sys_enumeration_value` VALUES ('18001', '汉族', '0', '汉族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18002', '满族', '1', '满族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18003', '回族', '2', '回族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18004', '藏族', '3', '藏族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18005', '蒙古族', '4', '蒙古族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18006', '维吾尔族', '5', '维吾尔族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18007', '壮族', '6', '壮族', '18000');
INSERT INTO `sys_enumeration_value` VALUES ('18008', '苗族', '7', '苗族', '18000');

-- ----------------------------
-- Table structure for sys_operation_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_record`;
CREATE TABLE `sys_operation_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATION_PERSON` varchar(20) NOT NULL,
  `OPERATION_NAME` varchar(20) NOT NULL,
  `OPERATION_URL` varchar(100) DEFAULT NULL,
  `OPERATION_DESCRIPTION` varchar(100) DEFAULT NULL,
  `OPERATION_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operation_record
-- ----------------------------
INSERT INTO `sys_operation_record` VALUES ('3', '梁炳坚', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2015-06-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('4', '梁炳坚', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2015-06-17 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('5', '梁炳坚', '添加学生源信息页面', '/StudentInfo_AddNewInfo_toAddStudentInfoPage.action', '进入添加学生源信息的页面', '2015-09-01 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('6', '梁炳坚', '编辑学生源信息页面', '/StudentInfo_EditStuInfo_toEditStudentInfoPage.action', '进入编辑学生源信息的页面', '2015-09-01 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('7', '梁炳坚', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('8', '梁炳坚', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('9', '梁炳坚', '学院审核', '/StudentInfo_List_toDepartmentAduitPage.action', '学院审核学生源信息', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('10', '梁炳坚', '获取学院审核学生源信息列表', '/StudentInfo_List_getDepartmentAduitList.action', '（学生源信息）获取学院审核列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('11', '去3', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('12', '去3', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2015-09-01 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('13', '去3', '用人单位基本信息列表', '/EmployingUnit_List_toEmpUnitListPage.action', '进入用人单位基本信息列表页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('14', '去3', '获取用人单位基本信息列表', '/EmployingUnit_List_getEmpUnitList.action', '获取用人单位基本信息列表', '2015-08-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('15', '去3', '添加用人单位基本信息页面', '/EmployingUnit_Add_toAddEmpUnitPage.action', '进入添加用人单位基本信息页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('16', '去3', '编辑用人单位基本信息页面', '/EmployingUnit_Edit_toEditEmpUnitPage.action', '进入编辑用人单位基本信息页面', '2015-08-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('17', '去3', '查看所有招聘信息', '/EmployingUnit_InfoList_toRecruitmentInfosPage', '查看所有招聘信息', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('18', '去3', '招聘信息列表', '/RecruitmentInfo_List_toRecruitmentInfoListPage.action', '进入招聘信息列表页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('19', '去3', '获取招聘信息列表', '/RecruitmentInfo_List_getRecruitmentInfoList.action', '获取招聘信息列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('20', '去3', '添加招聘信息页面', '/RecruitmentInfo_Add_toAddRecruitmentInfoPage.action', '进入添加招聘信息页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('21', '去3', '用人单位基本信息列表', '/EmployingUnit_List_toEmpUnitListPage.action', '进入用人单位基本信息列表页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('22', '去3', '获取用人单位基本信息列表', '/EmployingUnit_List_getEmpUnitList.action', '获取用人单位基本信息列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('23', '去3', '招聘信息列表', '/RecruitmentInfo_List_toRecruitmentInfoListPage.action', '进入招聘信息列表页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('24', '去3', '获取招聘信息列表', '/RecruitmentInfo_List_getRecruitmentInfoList.action', '获取招聘信息列表', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('25', '去3', '编辑招聘信息页面', '/RecruitmentInfo_Eidt_toEditRecruitmentInfoPage.action', '进入编辑招聘信息页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('26', '去3', '添加招聘信息页面', '/RecruitmentInfo_Add_toAddRecruitmentInfoPage.action', '进入添加招聘信息页面', '2015-09-08 00:00:00');
INSERT INTO `sys_operation_record` VALUES ('27', '梁炳坚', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2015-09-14 20:29:35');
INSERT INTO `sys_operation_record` VALUES ('28', '梁炳坚', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2015-09-14 20:29:36');
INSERT INTO `sys_operation_record` VALUES ('29', '梁炳坚', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2015-09-14 20:33:56');
INSERT INTO `sys_operation_record` VALUES ('30', '梁炳坚', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2015-09-14 20:33:57');
INSERT INTO `sys_operation_record` VALUES ('31', '梁炳坚', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2016-01-15 01:18:18');
INSERT INTO `sys_operation_record` VALUES ('32', '梁炳坚', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2016-01-15 01:18:19');
INSERT INTO `sys_operation_record` VALUES ('33', '梁炳坚', '学生生源信息列表', '/StudentInfo_List_toStudentInfoListPage.action', '学生源信息列表', '2016-01-15 20:29:38');
INSERT INTO `sys_operation_record` VALUES ('34', '梁炳坚', '获取学生源信息列表', '/StudentInfo_List_getStudentList.action', '获取学生源信息列表', '2016-01-15 20:29:39');

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ORGANIZATION_ID` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ecaxn8sbnnid1j7xya8k858g2` (`PARENT_ID`),
  KEY `FK_29a5qkvof6g63ebkv8xb2ju7a` (`ORGANIZATION_ID`),
  KEY `FK_7fssu67fw54bf6fbo1iwr756b` (`pid`),
  CONSTRAINT `FK_29a5qkvof6g63ebkv8xb2ju7a` FOREIGN KEY (`ORGANIZATION_ID`) REFERENCES `sys_organization` (`ID`),
  CONSTRAINT `FK_7fssu67fw54bf6fbo1iwr756b` FOREIGN KEY (`pid`) REFERENCES `sys_organization` (`ID`),
  CONSTRAINT `FK_ecaxn8sbnnid1j7xya8k858g2` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_organization` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', '学院办公室', 'icon-company', null, '学院办公室', null, null);
INSERT INTO `sys_organization` VALUES ('2', '学院学生办公室', 'icon-auth', '1', '学院学生办公室', null, null);
INSERT INTO `sys_organization` VALUES ('3', '学校就业办公室', 'icon-auth', '1', '就业办公室', null, null);
INSERT INTO `sys_organization` VALUES ('4', '学院', '', '1', '各学院', null, null);
INSERT INTO `sys_organization` VALUES ('5', '测试1', '', '3', '测试1', null, null);
INSERT INTO `sys_organization` VALUES ('6', '测试2', '', '3', '测试2', null, null);
INSERT INTO `sys_organization` VALUES ('7', '测试3', '', '2', '测试3', null, null);
INSERT INTO `sys_organization` VALUES ('8', '测试4', '', '1', '测试4', null, null);
INSERT INTO `sys_organization` VALUES ('9', '根节点', 'icon-company', null, '根节点', null, null);
INSERT INTO `sys_organization` VALUES ('10', '根节点的孩子', '', '9', '根节点的孩子', null, null);
INSERT INTO `sys_organization` VALUES ('12', '测试费', 'icon-auth', '9', '阿斯顿', null, null);
INSERT INTO `sys_organization` VALUES ('14', 'sad', 'asd', '12', 'asd', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `ISDEFAULT` int(11) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '2001', '超级管理员，拥有操作所用功能的权限');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '2002', '普通使用用户');
INSERT INTO `sys_role` VALUES ('3', '学院辅导员', '2002', '各个学院辅导员，拥有...功能');
INSERT INTO `sys_role` VALUES ('5', '测试1212', '2002', '测试1212');
INSERT INTO `sys_role` VALUES ('8', '请问', '2002', '请问');
INSERT INTO `sys_role` VALUES ('9', '驱蚊器', '2002', '请问');
INSERT INTO `sys_role` VALUES ('10', '完全颠覆', '2002', '武器恩');
INSERT INTO `sys_role` VALUES ('11', '请问', '2002', '测试权限2');

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth` (
  `AUTH_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`AUTH_ID`),
  KEY `FK_g020vg4jkllbyyipmg39n6psp` (`AUTH_ID`),
  CONSTRAINT `FK_7vtvlhghkxny153bb9c5f4u7o` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`),
  CONSTRAINT `FK_g020vg4jkllbyyipmg39n6psp` FOREIGN KEY (`AUTH_ID`) REFERENCES `sys_auth` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES ('1', '1');
INSERT INTO `sys_role_auth` VALUES ('1', '3');
INSERT INTO `sys_role_auth` VALUES ('2', '1');
INSERT INTO `sys_role_auth` VALUES ('2', '2');
INSERT INTO `sys_role_auth` VALUES ('2', '3');
INSERT INTO `sys_role_auth` VALUES ('2', '11');
INSERT INTO `sys_role_auth` VALUES ('3', '1');
INSERT INTO `sys_role_auth` VALUES ('3', '2');
INSERT INTO `sys_role_auth` VALUES ('4', '1');
INSERT INTO `sys_role_auth` VALUES ('5', '1');
INSERT INTO `sys_role_auth` VALUES ('5', '2');
INSERT INTO `sys_role_auth` VALUES ('5', '3');
INSERT INTO `sys_role_auth` VALUES ('6', '1');
INSERT INTO `sys_role_auth` VALUES ('6', '2');
INSERT INTO `sys_role_auth` VALUES ('6', '3');
INSERT INTO `sys_role_auth` VALUES ('6', '5');
INSERT INTO `sys_role_auth` VALUES ('6', '11');
INSERT INTO `sys_role_auth` VALUES ('7', '1');
INSERT INTO `sys_role_auth` VALUES ('7', '2');
INSERT INTO `sys_role_auth` VALUES ('7', '3');
INSERT INTO `sys_role_auth` VALUES ('7', '5');
INSERT INTO `sys_role_auth` VALUES ('7', '11');
INSERT INTO `sys_role_auth` VALUES ('8', '1');
INSERT INTO `sys_role_auth` VALUES ('8', '2');
INSERT INTO `sys_role_auth` VALUES ('8', '3');
INSERT INTO `sys_role_auth` VALUES ('8', '5');
INSERT INTO `sys_role_auth` VALUES ('8', '11');
INSERT INTO `sys_role_auth` VALUES ('9', '1');
INSERT INTO `sys_role_auth` VALUES ('9', '3');
INSERT INTO `sys_role_auth` VALUES ('9', '11');
INSERT INTO `sys_role_auth` VALUES ('10', '1');
INSERT INTO `sys_role_auth` VALUES ('10', '3');
INSERT INTO `sys_role_auth` VALUES ('10', '11');
INSERT INTO `sys_role_auth` VALUES ('11', '1');
INSERT INTO `sys_role_auth` VALUES ('11', '3');
INSERT INTO `sys_role_auth` VALUES ('11', '11');
INSERT INTO `sys_role_auth` VALUES ('12', '1');
INSERT INTO `sys_role_auth` VALUES ('12', '3');
INSERT INTO `sys_role_auth` VALUES ('13', '1');
INSERT INTO `sys_role_auth` VALUES ('13', '2');
INSERT INTO `sys_role_auth` VALUES ('14', '1');
INSERT INTO `sys_role_auth` VALUES ('14', '2');
INSERT INTO `sys_role_auth` VALUES ('15', '1');
INSERT INTO `sys_role_auth` VALUES ('15', '2');
INSERT INTO `sys_role_auth` VALUES ('16', '1');
INSERT INTO `sys_role_auth` VALUES ('17', '1');
INSERT INTO `sys_role_auth` VALUES ('18', '1');
INSERT INTO `sys_role_auth` VALUES ('19', '1');
INSERT INTO `sys_role_auth` VALUES ('20', '1');
INSERT INTO `sys_role_auth` VALUES ('21', '1');
INSERT INTO `sys_role_auth` VALUES ('22', '1');
INSERT INTO `sys_role_auth` VALUES ('22', '3');
INSERT INTO `sys_role_auth` VALUES ('23', '1');
INSERT INTO `sys_role_auth` VALUES ('23', '3');
INSERT INTO `sys_role_auth` VALUES ('24', '1');
INSERT INTO `sys_role_auth` VALUES ('24', '3');
INSERT INTO `sys_role_auth` VALUES ('25', '1');
INSERT INTO `sys_role_auth` VALUES ('25', '3');
INSERT INTO `sys_role_auth` VALUES ('26', '1');
INSERT INTO `sys_role_auth` VALUES ('26', '3');
INSERT INTO `sys_role_auth` VALUES ('27', '1');
INSERT INTO `sys_role_auth` VALUES ('28', '1');
INSERT INTO `sys_role_auth` VALUES ('28', '3');
INSERT INTO `sys_role_auth` VALUES ('29', '1');
INSERT INTO `sys_role_auth` VALUES ('30', '1');
INSERT INTO `sys_role_auth` VALUES ('31', '1');
INSERT INTO `sys_role_auth` VALUES ('31', '3');
INSERT INTO `sys_role_auth` VALUES ('32', '1');
INSERT INTO `sys_role_auth` VALUES ('32', '3');
INSERT INTO `sys_role_auth` VALUES ('33', '1');
INSERT INTO `sys_role_auth` VALUES ('33', '3');
INSERT INTO `sys_role_auth` VALUES ('34', '1');
INSERT INTO `sys_role_auth` VALUES ('34', '3');
INSERT INTO `sys_role_auth` VALUES ('35', '1');
INSERT INTO `sys_role_auth` VALUES ('35', '3');
INSERT INTO `sys_role_auth` VALUES ('36', '1');
INSERT INTO `sys_role_auth` VALUES ('36', '3');
INSERT INTO `sys_role_auth` VALUES ('37', '1');
INSERT INTO `sys_role_auth` VALUES ('37', '3');
INSERT INTO `sys_role_auth` VALUES ('38', '1');
INSERT INTO `sys_role_auth` VALUES ('38', '3');
INSERT INTO `sys_role_auth` VALUES ('39', '1');
INSERT INTO `sys_role_auth` VALUES ('40', '1');
INSERT INTO `sys_role_auth` VALUES ('41', '1');
INSERT INTO `sys_role_auth` VALUES ('42', '1');
INSERT INTO `sys_role_auth` VALUES ('43', '1');
INSERT INTO `sys_role_auth` VALUES ('44', '1');
INSERT INTO `sys_role_auth` VALUES ('44', '9');
INSERT INTO `sys_role_auth` VALUES ('45', '1');
INSERT INTO `sys_role_auth` VALUES ('45', '9');
INSERT INTO `sys_role_auth` VALUES ('46', '1');
INSERT INTO `sys_role_auth` VALUES ('47', '1');
INSERT INTO `sys_role_auth` VALUES ('49', '1');
INSERT INTO `sys_role_auth` VALUES ('50', '1');
INSERT INTO `sys_role_auth` VALUES ('50', '3');
INSERT INTO `sys_role_auth` VALUES ('52', '1');
INSERT INTO `sys_role_auth` VALUES ('52', '3');
INSERT INTO `sys_role_auth` VALUES ('53', '1');
INSERT INTO `sys_role_auth` VALUES ('53', '3');
INSERT INTO `sys_role_auth` VALUES ('54', '1');
INSERT INTO `sys_role_auth` VALUES ('54', '3');
INSERT INTO `sys_role_auth` VALUES ('55', '1');
INSERT INTO `sys_role_auth` VALUES ('55', '3');
INSERT INTO `sys_role_auth` VALUES ('56', '1');
INSERT INTO `sys_role_auth` VALUES ('56', '3');
INSERT INTO `sys_role_auth` VALUES ('57', '1');
INSERT INTO `sys_role_auth` VALUES ('57', '3');
INSERT INTO `sys_role_auth` VALUES ('58', '1');
INSERT INTO `sys_role_auth` VALUES ('58', '3');
INSERT INTO `sys_role_auth` VALUES ('59', '1');
INSERT INTO `sys_role_auth` VALUES ('59', '3');
INSERT INTO `sys_role_auth` VALUES ('60', '1');
INSERT INTO `sys_role_auth` VALUES ('60', '3');
INSERT INTO `sys_role_auth` VALUES ('61', '1');
INSERT INTO `sys_role_auth` VALUES ('61', '3');
INSERT INTO `sys_role_auth` VALUES ('62', '1');
INSERT INTO `sys_role_auth` VALUES ('62', '3');
INSERT INTO `sys_role_auth` VALUES ('63', '1');
INSERT INTO `sys_role_auth` VALUES ('63', '3');
INSERT INTO `sys_role_auth` VALUES ('64', '1');
INSERT INTO `sys_role_auth` VALUES ('64', '3');
INSERT INTO `sys_role_auth` VALUES ('65', '1');
INSERT INTO `sys_role_auth` VALUES ('65', '3');
INSERT INTO `sys_role_auth` VALUES ('66', '1');
INSERT INTO `sys_role_auth` VALUES ('66', '3');
INSERT INTO `sys_role_auth` VALUES ('67', '1');
INSERT INTO `sys_role_auth` VALUES ('68', '1');
INSERT INTO `sys_role_auth` VALUES ('69', '1');
INSERT INTO `sys_role_auth` VALUES ('70', '1');
INSERT INTO `sys_role_auth` VALUES ('71', '1');
INSERT INTO `sys_role_auth` VALUES ('72', '1');
INSERT INTO `sys_role_auth` VALUES ('73', '1');
INSERT INTO `sys_role_auth` VALUES ('74', '1');
INSERT INTO `sys_role_auth` VALUES ('75', '1');
INSERT INTO `sys_role_auth` VALUES ('76', '1');
INSERT INTO `sys_role_auth` VALUES ('77', '1');
INSERT INTO `sys_role_auth` VALUES ('78', '1');
INSERT INTO `sys_role_auth` VALUES ('79', '1');
INSERT INTO `sys_role_auth` VALUES ('80', '1');
INSERT INTO `sys_role_auth` VALUES ('81', '1');
INSERT INTO `sys_role_auth` VALUES ('82', '1');
INSERT INTO `sys_role_auth` VALUES ('83', '1');
INSERT INTO `sys_role_auth` VALUES ('84', '1');
INSERT INTO `sys_role_auth` VALUES ('85', '1');
INSERT INTO `sys_role_auth` VALUES ('86', '1');
INSERT INTO `sys_role_auth` VALUES ('87', '1');
INSERT INTO `sys_role_auth` VALUES ('88', '1');
INSERT INTO `sys_role_auth` VALUES ('89', '1');
INSERT INTO `sys_role_auth` VALUES ('90', '1');
INSERT INTO `sys_role_auth` VALUES ('91', '1');
INSERT INTO `sys_role_auth` VALUES ('92', '1');
INSERT INTO `sys_role_auth` VALUES ('93', '1');
INSERT INTO `sys_role_auth` VALUES ('94', '1');
INSERT INTO `sys_role_auth` VALUES ('95', '1');
INSERT INTO `sys_role_auth` VALUES ('96', '1');
INSERT INTO `sys_role_auth` VALUES ('97', '1');
INSERT INTO `sys_role_auth` VALUES ('98', '1');
INSERT INTO `sys_role_auth` VALUES ('99', '1');
INSERT INTO `sys_role_auth` VALUES ('100', '1');
INSERT INTO `sys_role_auth` VALUES ('101', '1');
INSERT INTO `sys_role_auth` VALUES ('102', '1');
INSERT INTO `sys_role_auth` VALUES ('103', '1');
INSERT INTO `sys_role_auth` VALUES ('104', '1');
INSERT INTO `sys_role_auth` VALUES ('105', '1');
INSERT INTO `sys_role_auth` VALUES ('106', '1');
INSERT INTO `sys_role_auth` VALUES ('107', '1');
INSERT INTO `sys_role_auth` VALUES ('108', '1');
INSERT INTO `sys_role_auth` VALUES ('109', '1');
INSERT INTO `sys_role_auth` VALUES ('110', '1');
INSERT INTO `sys_role_auth` VALUES ('111', '1');
INSERT INTO `sys_role_auth` VALUES ('112', '1');
INSERT INTO `sys_role_auth` VALUES ('113', '1');
INSERT INTO `sys_role_auth` VALUES ('114', '1');
INSERT INTO `sys_role_auth` VALUES ('115', '1');
INSERT INTO `sys_role_auth` VALUES ('116', '1');
INSERT INTO `sys_role_auth` VALUES ('117', '1');
INSERT INTO `sys_role_auth` VALUES ('118', '1');
INSERT INTO `sys_role_auth` VALUES ('119', '1');
INSERT INTO `sys_role_auth` VALUES ('120', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGINNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(33) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `ISDEFAULT` int(11) NOT NULL,
  `STATE` int(11) NOT NULL,
  `ORGANIZATION_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_k8qfb7ktnehcsnin81lv0rhjo` (`ORGANIZATION_ID`),
  CONSTRAINT `FK_k8qfb7ktnehcsnin81lv0rhjo` FOREIGN KEY (`ORGANIZATION_ID`) REFERENCES `sys_organization` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '2001', '14001', '1', '2015-08-04 14:30:06', '2015-08-21 14:31:40');
INSERT INTO `sys_user` VALUES ('2', '3112002711', 'e10adc3949ba59abbe56e057f20f883e', '小j', '2002', '14001', '1', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('3', '3112002712', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '1', '2015-08-20 14:32:38', '2015-09-08 13:21:09');
INSERT INTO `sys_user` VALUES ('4', '3112002715', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '1', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('5', '3112002713', 'e10adc3949ba59abbe56e057f20f883e', '小i', '2002', '14001', '1', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('6', '3112002714', 'e10adc3949ba59abbe56e057f20f883e', '邝静文', '2002', '14001', '1', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('7', '3112002716', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('8', '3112002717', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('9', '3112002718', 'e10adc3949ba59abbe56e057f20f883e', '哈哈', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('10', '3112002719', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '10', '2015-08-20 14:32:38', '2015-09-19 15:50:02');
INSERT INTO `sys_user` VALUES ('11', '3112002709', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('12', '3112002708', 'e10adc3949ba59abbe56e057f20f883e', '邝静文', '2002', '14001', '2', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('13', '3112002707', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '2', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('14', '3112002721', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '3', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('15', '3112002721', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '2', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('16', '3112002722', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('17', '3112002723', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('18', '3112002724', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('19', '3112002725', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('20', '3112002729', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '3', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('21', '3112002727', 'e10adc3949ba59abbe56e057f20f883e', '梁炳坚', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('22', '3112002728', 'e10adc3949ba59abbe56e057f20f883e', '去3', '2002', '14001', '4', '2015-08-20 14:32:38', '2015-09-08 22:22:47');
INSERT INTO `sys_user` VALUES ('23', '3112002730', 'e10adc3949ba59abbe56e057f20f883e', '去4', '2002', '14001', '3', '2015-08-20 14:32:38', '2015-08-20 14:32:41');
INSERT INTO `sys_user` VALUES ('25', '3112002732', 'e10adc3949ba59abbe56e057f20f883e', '去6', '2002', '14001', '3', '2015-08-20 14:32:38', '2015-08-28 23:09:59');
INSERT INTO `sys_user` VALUES ('27', 'admins', '202cb962ac59075b964b07152d234b70', '管理员', '2001', '14001', '4', '2015-08-28 12:26:35', '2015-08-28 22:52:40');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_nrlam7owa61ooca7psta3wq9q` (`ROLE_ID`),
  CONSTRAINT `FK_2ap46j4kbhpneb9j4jexnsw6c` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`ID`),
  CONSTRAINT `FK_nrlam7owa61ooca7psta3wq9q` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('27', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2');
INSERT INTO `sys_user_role` VALUES ('4', '2');
INSERT INTO `sys_user_role` VALUES ('5', '2');
INSERT INTO `sys_user_role` VALUES ('6', '2');
INSERT INTO `sys_user_role` VALUES ('7', '2');
INSERT INTO `sys_user_role` VALUES ('8', '2');
INSERT INTO `sys_user_role` VALUES ('9', '2');
INSERT INTO `sys_user_role` VALUES ('10', '2');
INSERT INTO `sys_user_role` VALUES ('25', '2');
INSERT INTO `sys_user_role` VALUES ('22', '3');
