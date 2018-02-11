# 如何使用Maven命令启动和打包项目
	
	1. 快速启动 - clean org.springframework.boot:spring-boot-maven-plugin:run -q
	2. 打包 - clean package
	
# 开发阶段所用的数据库设置

	1. url: jdbc:mysql://192.168.1.99:3306/cloud_class?characterEncoding=utf-8&useSSL=true
       username: root
       password: zhangxin
       
# 正式上线之前可能会被替换的组件与中间件

	1. Druid 1.0.28 (截止到2017/02/25,最新的发布版本) -> c3p0 0.9.5.2 (截止到2017/02/25,最新的发布版本)

# 正式上线之前需要删除的项目文件：

	/** 弃用的自建文件 **/
	1. src/main/webapp/WEB-INF/views/include_list_required.html
	2. src/mian/java/com.beamofsoul.springboot.entity.Review.java (暂时弃用,无业务需求,原业务已经由实体类Comment代替)

	/** 弃用的UI框架与第三方前端组件**/
	1. src/main/resources/static/BOOTSTRAP-DATE-PICKER/...
	2. src/main/resources/static/BOOTSTRAP-DIALOG/...
	3. src/main/resources/static/BOOTSTRAP-TOGGLE/...
	
	/** 需要清除的后台Demo事例 **/
	5. src/main/java/com/beamofsoul/springboot/entity/TreeNode.java
	6. src/main/java/com/beamofsoul/springboot/service/TreeNodeService.java
	7. src/main/java/com/beamofsoul/springboot/service/impl/TreeNodeServiceImpl.java
	8. src/main/java/com/beamofsoul/springboot/repository/TreeNodeRepository.java
	
# 发布到Tomcat解压后修改CKFinder配置文件里的baseDir至Tomcat Web目录