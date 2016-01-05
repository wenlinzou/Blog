-- MySQL dump 10.13  Distrib 5.6.19, for Win64 (x86_64)
--
-- Host: 192.168.1.19    Database: bbs
-- ------------------------------------------------------
-- Server version	5.6.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '类别idcategory',
  `rootid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `cont` text,
  `pdate` datetime DEFAULT NULL,
  `isleaf` int(11) DEFAULT '0' COMMENT '1可见0不可见',
  `click` int(11) DEFAULT '0' COMMENT '点击',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,1,1,'蚂蚁大战大象','<p>nr蚂蚁大战大象</p>','2015-12-24 19:59:11',1,0),(2,1,1,'大象被打趴下了','大象被打趴下了','2015-12-24 19:59:11',1,0),(3,2,1,'蚂蚁也不好过','蚂蚁也不好过','2015-12-24 19:59:11',1,0),(4,2,1,'瞎说','瞎说','2015-12-24 19:59:11',1,0),(5,4,1,'没有瞎说','没有瞎说','2015-12-24 19:59:11',1,0),(6,1,1,'怎么可能','怎么可能','2015-12-24 19:59:11',1,0),(7,6,1,'怎么没有可能','怎么没有可能','2015-12-24 19:59:11',0,0),(8,6,1,'可能性是很大的','可能性是很大的','2015-12-24 19:59:11',0,0),(9,2,1,'大象进医院了','大象进医院了','2015-12-24 19:59:11',1,0),(10,9,1,'护士是蚂蚁','护士是蚂蚁','2015-12-24 19:59:19',0,0),(11,0,1,'2015-12-24新建的主题','<p>内容就是平安夜</p>','2015-11-04 20:01:43',1,0),(12,1,1,'我的新标题','<p>这</p><p>是</p><p>我</p><p>的</p><p>高</p><p>度</p><p>测</p><p>试</p><p><span style=\"font-size: 20px;\">这是我的测试,这个新文章,倾尽了我的所有心血,大家一定要珍惜和好好地看,最后能给予我,一些自己内心对于此的真实想法!万分感谢...:-)</span></p><p><img src=\"../ueditor/jsp/upload/image/20151230/1451442581909078166.jpg\" title=\"1451442581909078166.jpg\" alt=\"6b6e567cgw1euah94mfdpj20go0p040e.jpg\"/></p><p style=\"text-align: center;\"><strong>内容哦</strong></p><p><strong><img src=\"../ueditor/jsp/upload/image/20151230/1451472517674025413.jpg\" title=\"1451472517674025413.jpg\" alt=\"6b6e567cgw1euah94ul9ij20gy0bvdgm.jpg\"/></strong></p><pre class=\"brush:java;toolbar:false\">class&nbsp;Test{\r\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;static&nbsp;void&nbsp;main(String[]&nbsp;args){\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(&quot;Hello&nbsp;my&nbsp;friend&quot;);\r\n&nbsp;&nbsp;&nbsp;&nbsp;}\r\n}</pre><p><br/></p>','2015-12-29 16:55:12',1,5),(13,1,1,'Google 将不会在未来的 Android 版本中使用 Oracle 的 Java API','<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\"></span></p><p><span style=\"font-size: 18px;\">&nbsp; 就在不久前 Google 向 VentureBeat 证实，在未来的 Android 版本中，将不会再出现 Oracle Java API 的影子。取而代之的，将是 Oracle Java Development Kit 的开源版本 OpenJDK，按照官方说法，此举（利用统一的代码库）将有助于简化应用的开发过程。</span></p><p><span style=\"font-size: 18px;\">&nbsp; 只是，这一变化将影响到多达 8,902 个文件，要说跟 Google 和 Oracle 的官司无关，怕是没多少人会真的相信（鉴于官司仍在进行中，没人出来表态也是情理之中）。所以说到底，Android 开发和应用编程未来发展的走向，还是跟最终的裁决结果有着莫大的关系。但不管怎么说，新工具应该能为 Android N 的开发者带来更好的触控体验，但愿这能转化成更多优质的应用作品啰。</span></p><p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\"></span><br/></p><p><img src=\"../ueditor/jsp/upload/image/20151231/1451533183462068438.gif\" title=\"1451533183462068438.gif\" alt=\"61e61e8cjw1evnskws21jg20b406f0zp.gif\"/></p>','2015-12-31 11:39:51',1,5),(14,1,0,'​专为小朋友而设的搜索引擎 Thinga 上线，还有家长控制功能呢','<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\"><img src=\"../ueditor/jsp/upload/image/20160104/1451875670350005557.jpg\" title=\"1451875670350005557.jpg\" alt=\"thinga.jpg\"/></span></p><p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\">在这信息发达的年代，</span>小朋友<span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\">都会在网上汲取知识，但为人父母的都会担心小朋友会在网上见到不适合他们年纪的东西。一个为小朋友而设计的搜索引擎 Thinga 就应运而生，它的搜索结果来自该公司的资料库、白名单网站和 DuckDuckGo。</span><br style=\"white-space: normal; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\"/><br style=\"white-space: normal; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\"/><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\">Thinga 由已被停运的&nbsp;</span>Yahoo Kids<span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Nimbus Sans L&#39;, Arial, &#39;Liberation Sans&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;Wenquanyi Micro Hei&#39;, &#39;WenQuanYi Zen Hei&#39;, &#39;ST Heiti&#39;, SimHei, &#39;WenQuanYi Zen Hei Sharp&#39;, sans-serif; font-size: 15px; letter-spacing: 1px; line-height: 22.5px; background-color: rgb(255, 255, 255);\">&nbsp;前员工 BJ Heinley 创立，目标是让小学阶段的小朋友在不同的搜索分类中（包含视频、Cool、动物）找到有趣的资料。网站应用了儿童网上隐私保护条例和家长控制的功能，确保小朋友能安全地使用搜索引擎。要注意的是 Thinga 是个英语为主的搜索引擎，或许也是个好机会来让小朋友学外语？</span></p>','2016-01-04 10:47:41',1,8);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'java1'),(2,'javascript'),(3,'html');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salt`
--

DROP TABLE IF EXISTS `salt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salt` varchar(255) DEFAULT NULL COMMENT 'name+salt',
  `userid` int(11) DEFAULT NULL COMMENT '用户的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salt`
--

LOCK TABLES `salt` WRITE;
/*!40000 ALTER TABLE `salt` DISABLE KEYS */;
INSERT INTO `salt` VALUES (1,'1234',12),(2,'[B@21220964',15),(3,'[B@5186069c',16);
/*!40000 ALTER TABLE `salt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (12,'root','AABB2100033F0352FE7458E412495148','牛逼的人生是吧!'),(15,'test','091F1B302000D524F5982F6BDC227EF7','你好啊'),(16,'admin','F3EC0239679F5033F0B641F6D4CDFF2A','管理员');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-05 12:51:06
