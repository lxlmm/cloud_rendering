
DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
                          `user_id` int(11) NOT NULL AUTO_INCREMENT,
                          `user_name` varchar(10) NOT NULL,
                          `user_password` char(20) NOT NULL,
                          `delete_flag` varchar(20) ,
                          `admin_flag` varchar(20) ,
                          `user_pic` varchar(1024) ,
                          `fans_num` int(5) ,
                          `follow_num` int(5) ,
                          `blog_num` int(5) ,
                          PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `t_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_blog` (
                          `blog_id` int(11) NOT NULL AUTO_INCREMENT,
                          `blog_title` varchar(10) NOT NULL,
                          `user_id` int(11),
                          `blog_content` varchar(1024) ,
                          `blog_pic` varchar(1024) ,
                          `delete_flag` int(1) ,
                          `blog_time` date DEFAULT NULL ,
                          PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_likeblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_likeblog` (
                          `likeblog_id` int(11) NOT NULL AUTO_INCREMENT,
                          `blog_id` int(11) ,
                          `user_id` int(11),
                          PRIMARY KEY (`likeblog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_inform` (
                              `inform_id` int(11) NOT NULL AUTO_INCREMENT,
                              `blog_id` int(11) ,
                              `user_id` int(11),
                              PRIMARY KEY (`inform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_commentlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_commentlike` (
                                 `commentLikeId` int(11) NOT NULL AUTO_INCREMENT,
                                 `commentId` int(10) NOT NULL,
                                 `userId` int(10) NOT NULL,
                                 PRIMARY KEY (`commentLikeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_follow` (
                            `follow_id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` varchar(11) NOT NULL,
                            `user_name` varchar(1024) NOT NULL,
                            `blog_num` int(5)  ,
                            `fans_num` int(5) ,
                            `follow_num` int(5) ,
                            `follower_id` int(11) ,
                            PRIMARY KEY (`follow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_message` (
                             `message_id` int(11) NOT NULL AUTO_INCREMENT,
                             `user_id` int(11) NOT NULL,
                             `sender_id` int(11) NOT NULL,
                             `sender_name` varchar(1024)  ,
                             `message_type` int(5) ,
                             `message_content` varchar(1024) ,
                             `blog_id` int(11) ,
                             `blog_name` varchar(1024) ,
                             PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_commentblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_commentblog` (
                                 `commentblog_id` int(11) NOT NULL AUTO_INCREMENT,
                                 `blog_id` int(11) NOT NULL,
                                 `user_id` int(11) NOT NULL,
                                 `comment_content` varchar(1024)  ,
                                 `comment_time` date DEFAULT NULL ,
                                 PRIMARY KEY (`commentblog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
