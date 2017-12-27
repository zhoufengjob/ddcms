/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50542
 Source Host           : localhost:3306
 Source Schema         : daimengshi_cms

 Target Server Type    : MySQL
 Target Server Version : 50542
 File Encoding         : 65001

 Date: 27/12/2017 12:04:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dms_article
-- ----------------------------
DROP TABLE IF EXISTS `dms_article`;
CREATE TABLE `dms_article` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `title` varchar(30) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `uid` varchar(36) DEFAULT NULL COMMENT '创建用户id',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否启用',
  `desc` varchar(255) DEFAULT NULL COMMENT '备注',
  `url` varchar(36) NOT NULL DEFAULT '' COMMENT '访问地址',
  `type` int(10) DEFAULT '0' COMMENT '文章类型',
  `status` int(10) DEFAULT '0' COMMENT '文章状态(默认:0)',
  `is_quintessence` varchar(4) DEFAULT 'off' COMMENT '是否精华帖(默认:0)',
  `is_top` varchar(4) DEFAULT 'off' COMMENT '是否置顶(默认:0)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of dms_article
-- ----------------------------
BEGIN;
INSERT INTO `dms_article` VALUES ('32ec43d677c24079b0feb8594becc3fb', 'Jboot 使用说明', '### 使用@RquestMapping\n使用@RquestMapping非常简单。只需要在Controller类添加上@RquestMapping注解即可。\n\n例如：\n\n```java\n@RequestMapping(\"/\")\npublic class HelloController extend JbootController{\n   public void index(){\n        renderText(\"hello jboot\");\n   }\n}\n```\n我们在HelloController控制器上，添加了@RequestMapping(\"/\")配置，也就是让当访问 `http://127.0.0.1/`的时候让HelloController控制的index()这个方法（action）来处理。\n\n**[注意]：** \n\n* 访问`http://127.0.0.1`等同于`http://127.0.0.1/`。\n* `@RquestMapping` 可以使用在任何的 Controller，并 **不需要** 这个Controller继承至JbootController。\n\n## render\n渲染器，负责把内容输出到浏览器，在Controller中，提供了如下一些列render方法。\n\n| 指令         |  描述  |\n| ------------- | -----|\n| render(”test.html”)  |渲染名为 test.html 的视图，该视图的全路径为”/path/test.html” |\n| render(”/other_path/test.html”)   |渲染名为 test.html 的视图，该视图的全路径为”/other_path/test.html”，即当参数以”/”开头时将采用绝对路径。|\n| renderTemplate(”test.html”)  |渲染名为 test.html 的视图，且视图类型为 JFinalTemplate。|\n| renderFreeMarker(”test.html”)  |渲 染 名 为 test.html 的视图 ， 且 视图类型为FreeMarker。 |\n| renderJsp(”test.jsp”)  |渲染名为 test.jsp 的视图，且视图类型为 Jsp。 |\n| renderVelocity(“test.html”)   |渲染名为 test.html 的视图，且视图类型为 Velocity。 |\n| renderJson()  |将所有通过 Controller.setAttr(String, Object)设置的变量转换成 json 数据并渲染。|\n| renderJson(“users”, userList)   |以”users”为根，仅将 userList 中的数据转换成 json数据并渲染。 |\n| renderJson(user)  |将 user 对象转换成 json 数据并渲染。 |\n| renderJson(“{\\”age\\”:18}” )   |直接渲染 json 字符串。 |\n| renderJson(new String[]{“user”, “blog”})  |仅将 setAttr(“user”, user)与 setAttr(“blog”, blog)设置的属性转换成 json 并渲染。使用 setAttr 设置的其它属性并不转换为 json。 |\n| renderFile(“test.zip”);  |渲染名为 test.zip 的文件，一般用于文件下载 |\n| renderText(“Hello Jboot”)   |渲染纯文本内容”Hello Jboot”。 |\n| renderHtml(“Hello Html”)   |渲染 Html 内容”Hello Html”。 |\n| renderError (404 , “test.html”)  |渲染名为 test.html 的文件，且状态为 404。 |\n| renderError (500 , “test.html”)   |渲染名为 test.html 的文件，且状态为 500。 |\n| renderNull() |不渲染，即不向客户端返回数据。|\n| render(new MyRender()) |使用自定义渲染器 MyRender 来渲染。 |\n\n## session 与 分布式session\n\n使用session非常简单，直接在Controller里调用`getSessionAttr(key)` 或 `setSessionAttr(key,value)` 就可以。\n\n### 分布式session\n在Jboot的设计中，分布式的session是依赖分布式缓存的，jboot中，分布式缓存提供了3种方式：\n\n1. ehcache\n2. redis\n3. ehredis： 基于ehcache和redis实现的二级缓存框架。\n\n所以，在使用jboot的分布式session之前，需要在jboot.properties配置上jboot分布式的缓存。\n\n例如：\n\n```html\njboot.cache.type=redis\njboot.cache.redis.host = 127.0.0.1\njboot.cache.redis.password = 123456\njboot.cache.redis.database = 1\n```\n配置好缓存后，直接在Controller里调用`getSessionAttr(key)` 或 `setSessionAttr(key,value)` 即可。\n\n*注意：* session都是走缓存，如果jboot配置的缓存是ehcache（或者 ehredis）,请注意在ehcache.xml上添加名为 `SESSION` 的缓存节点。\n\n# 安全控制 \n## shiro简介\n\n略\n\n## shiro的配置\n在使用Jboot的shiro模块之前，我假定您已经学习并了解shiro的基础知识。在Jboot中使用shiro非常简单，只需要在resources目录下配置上您的shiro.ini文件即可。在shiro.ini文件里，需要在自行扩展realm等信息。\n\n\n## shiro的使用\nJboot的shiro模块为您提供了以下12个模板指令，同时支持shiro的5个Requires注解功能。方便您使用shiro。\n\n### 12个模板指令（用在html上）\n\n| 指令         |  描述  |\n| ------------- | -----|\n| shiroAuthenticated |用户已经身份验证通过，Subject.login登录成功 |\n| shiroGuest  |游客访问时。 但是，当用户登录成功了就不显示了|\n| shiroHasAllPermission  |拥有全部权限 |\n| shiroHasAllRoles  |拥有全部角色 |\n| shiroHasAnyPermission  |拥有任何一个权限 |\n| shiroHasAnyRoles  |拥有任何一个角色 |\n| shiroHasPermission  |有相应权限 |\n| shiroHasRole  |有相应角色 |\n| shiroNoAuthenticated  |未进行身份验证时，即没有调用Subject.login进行登录。 |\n| shiroNotHasPermission  |没有该权限 |\n| shiroNotHasRole  |没没有该角色 |\n| shiroPrincipal  |获取Subject Principal 身份信息 |\n\n\n\n\n\n#### shiroAuthenticated的使用\n\n```html\n#shiroAuthenticated()\n  登陆成功：您的用户名是：#(SESSION(\"username\"))\n#end\n\n```\n\n\n\n#### shiroGuest的使用\n\n```html\n#shiroGuest()\n  游客您好\n#end\n\n```\n\n#### shiroHasAllPermission的使用\n\n```html\n#shiroHasAllPermission(permissionName1,permissionName2)\n  您好，您拥有了权限 permissionName1和permissionName2\n#end\n\n```\n\n#### shiroHasAllRoles的使用\n\n```html\n#shiroHasAllRoles(role1, role2)\n  您好，您拥有了角色 role1和role2\n#end\n\n```\n#### shiroHasAnyPermission的使用\n\n```html\n#shiroHasAnyPermission(permissionName1,permissionName2)\n  您好，您拥有了权限 permissionName1 或 permissionName2 \n#end\n\n```\n#### shiroHasAnyRoles的使用\n\n```html\n#shiroHasAllRoles(role1, role2)\n  您好，您拥有了角色 role1 或 role2\n#end\n\n```\n#### shiroHasPermission的使用\n\n```html\n#shiroHasPermission(permissionName1)\n  您好，您拥有了权限 permissionName1 \n#end\n\n```\n#### shiroHasRole的使用\n\n```html\n#shiroHasRole(role1)\n  您好，您拥有了角色 role1 \n#end\n\n```\n#### shiroNoAuthenticated的使用\n\n```html\n#shiroNoAuthenticated()\n  您好，您还没有登陆\n#end\n\n```\n#### shiroNotHasPermission的使用\n\n```html\n#shiroNotHasPermission(permissionName1)\n  您好，您没有权限 permissionName1 \n#end\n\n```\n#### shiroNotHasRole的使用\n```html\n#shiroNotHasRole(role1)\n  您好，您没有角色role1\n#end\n\n```\n#### shiroPrincipal的使用\n```html\n#shiroPrincipal()\n  您好，您的登陆信息是：#(principal)\n#end\n\n```\n\n\n### 5个Requires注解功能（用在Controller上）\n\n| 指令         |  描述  |\n| ------------- | -----|\n| RequiresPermissions | 需要权限才能访问这个action |\n| RequiresRoles  | 需要角色才能访问这个action|\n| RequiresAuthentication  | 需要授权才能访问这个action，即：`SecurityUtils.getSubject().isAuthenticated()` |\n| RequiresUser  | 获取到用户信息才能访问这个action，即：`SecurityUtils.getSubject().getPrincipal() != null ` |\n| RequiresGuest  | 和RequiresUser相反 |\n\n\n#### RequiresPermissions的使用\n\n```java\npublic class MyController extends JbootController{\n\n      @RequiresPermissions(\"permission1\")\n      public void index(){\n\n	  }\n	  \n	  @RequiresPermissions(value={\"permission1\",\"permission2\"},logical=Logincal.AND)\n      public void index1(){\n\n	  }\n}\n```\n\n#### RequiresRoles的使用\n\n```java\npublic class MyController extends JbootController{\n\n      @RequiresRoles(\"role1\")\n      public void index(){\n\n	  }\n	  \n	  @RequiresRoles(value = {\"role1\",\"role2\"},logical=Logincal.AND)\n      public void userctener(){\n\n	  }\n}\n```\n\n#### RequiresUser、RequiresGuest、RequiresAuthentication的使用\n\n```java\npublic class MyController extends JbootController{\n\n      @RequiresUser\n      public void userCenter(){\n\n	  }\n	  \n	  @RequiresGuest\n      public void login(){\n\n	  }\n	  \n	  @RequiresAuthentication\n	  public void my(){\n	  \n	  }\n}\n```\n\n\n# ORM\n## 配置\n在使用数据库之前，需要给Jboot应用做一些配置，实际上，在任何的需要到数据库的应用中，都需要给应用程序做一些配置，让应用程序知道去哪里读取数据。\n\n由于Jboot的数据库读取是依赖于JFinal，所以实际上JFinal只是的数据库类型，Jboot都会支持，比如常用的数据库类型有：\n\n* Mysql\n* Oracle\n* SqlServer\n* postgresql\n* sqlite\n* 其他标准的数据库\n\n在Jboot应用连接数据库之前，我们需要在resources目录下创建一个jboot.properties配置文件，并在jboot.properties编写内容如下：\n\n```xml\njboot.datasource.type=mysql\njboot.datasource.url=jdbc:mysql://127.0.0.1:3306/jbootdemo\njboot.datasource.user=root\njboot.datasource.password=your_password\n```\n\n其中：\n\n* jboot.datasource.type 是配置数据库类型\n* jboot.datasource.url 是数据库请求URL地址\n* jboot.datasource.user 是数据库需要的账号\n* jboot.datasource.password 是数据库需要的密码\n\n### 高级配置\n除了 `type`，`url`，`user`，`password`四个配置以外，jbootdatasource 还支持以下配置：\n\n* jboot.datasource.name 数据源的名称\n* jboot.datasource.driverClassName 驱动类名\n* jboot.datasource.connectionInitSql 连接初始化Sql\n* jboot.datasource.poolName 线程池名称\n* jboot.datasource.cachePrepStmt 缓存启用\n* jboot.datasource.prepStmtCacheSize 缓存大小\n* jboot.datasource.prepStmtCacheSqlLimit 缓存限制\n* jboot.datasource.maximumPoolSize 线程池大小\n* jboot.datasource.sqlTemplatePath sql文件路径\n* jboot.datasource.sqlTemplate sql文件，多个用英文逗号隔开\n* jboot.datasource.table 该数据源对应的表名，多个表用英文逗号隔开\n\n更多的具体使用，特别是name、table等在分库分表章节会讲到。\n\n\n## Model\nmodel是MVC设计模式中的M，但同时每个model也会对应一个数据库表，它充当 MVC 模式中的 Model 部分。以下是Model 定义示例代码：\n\n```java\npublic class User extends JbootModel<User> {\n	public static final User dao = new User().dao();\n}\n```\n\n以上代码中的 User 通过继承 Model，便立即拥有的众多方便的操作数据库的方法。在 User中声明的 dao 静态对象是为了方便查询操作而定义的，该对象并不是必须的。同时，model无需定义 getter、setter 方法，无需 XML 配置，极大降低了代码量。\n\n以下是model常见的用法：\n\n```java\n// 创建name属性为James,age属性为25的User对象并添加到数据库\nnew User().set(\"name\", \"James\").set(\"age\", 25).save();\n// 删除id值为25的User\nUser.dao.deleteById(25);\n// 查询id值为25的User将其name属性改为James并更新到数据库\nUser.dao.findById(25).set(\"name\", \"James\").update();\n// 查询id值为25的user, 且仅仅取name与age两个字段的值\nUser user = User.dao.findByIdLoadColumns(25, \"name, age\");\n// 获取user的name属性\nString userName = user.getStr(\"name\");\n// 获取user的age属性\nInteger userAge = user.getInt(\"age\");\n// 查询所有年龄大于18岁的user\nList<User> users = User.dao.find(\"select * from user where age>18\");\n// 分页查询年龄大于18的user,当前页号为1,每页10个user\nPage<User> userPage = User.dao.paginate(1, 10, \"select *\", \"from user\nwhere age > ?\", 18);\n```\n\n**注意：**User 中定义的 public static final User dao 对象是全局共享的，**只能** 用于数据库查询，**不能** 用于数据承载对象。数据承载需要使用 new User().set(…)来实现。\n\n### @Table注解\n@Table注解是给Model使用的，表示让Model映射到哪个数据库表，使用代码如下：\n\n```java\n@Table(tableName = \"user\", primaryKey = \"id\")\npublic class User extends JbootModel <Company> {\n	\n}\n```\n值得注意的是：\n\n在Jboot应用中，我们几乎感受不到@Table这个注解的存在，因为这部分完全是代码生成器生成的，关于代码生成器，请查看 代码生成器章节。\n\n## Db + Record 模式\nDb 类及其配套的 Record 类，提供了在 Model 类之外更为丰富的数据库操作功能。使用Db 与 Record 类时，无需对数据库表进行映射，Record 相当于一个通用的 Model。以下为 Db +Record 模式的一些常见用法：\n\n```java\n// 创建name属性为James,age属性为25的record对象并添加到数据库\nRecord user = new Record().set(\"name\", \"James\").set(\"age\", 25);\nDb.save(\"user\", user);\n// 删除id值为25的user表中的记录\nDb.deleteById(\"user\", 25);\n// 查询id值为25的Record将其name属性改为James并更新到数据库\nuser = Db.findById(\"user\", 25).set(\"name\", \"James\");\nDb.update(\"user\", user);\n// 获取user的name属性\nString userName = user.getStr(\"name\");\n// 获取user的age属性\nInteger userAge = user.getInt(\"age\");\n// 查询所有年龄大于18岁的user\nPage<Record> userPage = Db.paginate(1, 10, \"select *\", \"from user where\nage > ?\", 18);\n```\n\n或者，事务操作：\n\n```java\nboolean succeed = Db.tx(new IAtom(){\n		public boolean run() throws SQLException {\n		int count = Db.update(\"update account set cash = cash - ? where\n		id = ?\", 100, 123);\n		int count2 = Db.update(\"update account set cash = cash + ? where\n		id = ?\", 100, 456);\n		return count == 1 && count2 == 1;\n	}\n});\n```\n以上两次数据库更新操作在一个事务中执行，如果执行过程中发生异常或者 run()方法返回 false，则自动回滚事务。\n\n## 更多\n请参考JFinal的文档：http://download.jfinal.com/download/3.2/jfinal-3.2-manual.pdf \n\n## 多数据源\n在Jboot中，使用多数据源非常简单。\n\n在以上章节里，我们知道，要连接数据库需要做如下配置：\n\n```xml\njboot.datasource.type=mysql\njboot.datasource.url=jdbc:mysql://127.0.0.1:3306/jbootdemo\njboot.datasource.user=root\njboot.datasource.password=your_password\n```\n\n假设我们再增加两个数据源，只需要在jboot.properties文件在添加如下配置即可：\n\n```xml\njboot.datasource.a1.type=mysql\njboot.datasource.a1.turl=jdbc:mysql://127.0.0.1:3306/jboot1\njboot.datasource.a1.tuser=root\njboot.datasource.a1.tpassword=your_password\n\njboot.datasource.a2.type=mysql\njboot.datasource.a2.turl=jdbc:mysql://127.0.0.1:3306/jboot2\njboot.datasource.a2.tuser=root\njboot.datasource.a2.tpassword=your_password\n```\n\n这表示，我们又增加了数据源`a1`和数据源`a2`，在使用的时候，我们只需要做一下使用：\n\n```java\nCompany company = new Company();\ncompany.setCid(\"1\");\ncompany.setName(\"name\");\n\ncompany.use(\"a1\").save();\n```\n`company.use(\"a1\").save();`表示使用数据源`a1`进行保存。\n\n**值得注意的是：**\n\n在多数据源应用中，很多时候，我们一个Model只有对应一个数据源，而不是一个Model对应多个数据源。假设Company只有在`a1`数据源中存在，在其他数据源并不存在，我们需要把`a1`数据源的配置修改如下：\n\n```xml\njboot.datasource.a1.type=mysql\njboot.datasource.a1.url=jdbc:mysql://127.0.0.1:3306/jboot1\njboot.datasource.a1.user=root\njboot.datasource.a1.password=your_password\njboot.datasource.a1.table=company\n\njboot.datasource.a2.type=mysql\njboot.datasource.a2.url=jdbc:mysql://127.0.0.1:3306/jboot2\njboot.datasource.a2.user=root\njboot.datasource.a2.password=your_password\njboot.datasource.a1.table=user,xxx(其他非company表)\n```\n这样，company在`a1`数据源中存在，Jboot在初始化的时候，并不会去检查company在其他数据源中是否存在，同时，代码操作company的时候，不再需要use，代码如下：\n\n```java\nCompany company = new Company();\ncompany.setCid(\"1\");\ncompany.setName(\"name\");\n\n//company.use(\"a1\").save();\ncompany.save();\n```\n代码中不再需要 `use(\"a1\")` 指定数据源，因为company只有一个数据源。\n\n\n## 分库和分表\n\n### 分库\n暂无内容\n\n### 分表\n在Jboot中，分表是通过sharding-jdbc（ 网址：https://github.com/shardingjdbc/sharding-jdbc） 来实现的，所以，在了解Jboot的分表之前，请先阅读了解sharding-jdbc的配置信息。\n\n\n\n#### demos\n\n例如：有一个userModel，我们希望能进行分为三张表，通过id的hashcode进行取模，代码如下：\n\n```java\n\n@Table(tableName = \"tb_user\",\n        primaryKey = \"id\",\n         // 具体的表tb_user${0..2} 表示有三张表 tb_user0,tb_user1,tb_user2,\n         // main 是默认数据源的名称\n        actualDataNodes = \"main.tb_user${0..2}\",\n        //分表策略\n        tableShardingStrategyConfig = UserTableShardingStrategyConfig.class \n)\npublic class UserModel extends JbootModel<UserModel> {\n\n\n    public UserModel(String id, String name) {\n        setId(id);\n        setName(name);\n    }\n\n    public UserModel() {\n    }\n\n\n    public String getId() {\n        return get(\"id\");\n    }\n\n    public void setId(String id) {\n        set(\"id\", id);\n    }\n\n    public String getName() {\n        return get(\"name\");\n    }\n\n    public void setName(String name) {\n        set(\"name\", name);\n    }\n}\n\n```\n\n编写UserModel的分表策略  UserTableShardingStrategyConfig，代码如下：\n\n```java\npublic class UserTableShardingStrategyConfig implements ShardingStrategyConfiguration {\n\n    @Override\n    public ShardingStrategy build() {\n        return shardingStrategy;\n    }\n\n\n    private ShardingStrategy shardingStrategy = new ShardingStrategy() {\n\n        @Override\n        public Collection<String> getShardingColumns() {\n            //根据id进行分表\n            return Sets.newHashSet(\"id\");\n        }\n\n        @Override\n        public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {\n            ListShardingValue shardingValue = (ListShardingValue) shardingValues.stream().findFirst().get();\n\n            String tableName = \"tb_user\" + Math.abs(shardingValue.getValues().iterator().next().toString().hashCode()) % 3;\n\n            System.out.println(\"插入数据到表：\" + tableName);\n\n            //返回通过计算得到的表\n            return Sets.newHashSet(tableName);\n\n        }\n    };\n\n}\n```\n\n编写配置文件：\n\n```\njboot.datasource.type=mysql\njboot.datasource.url=jdbc:mysql://127.0.0.1:3306/jbootsharding\njboot.datasource.user=root\njboot.datasource.password=\njboot.datasource.shardingEnable=true\n```\n\n进行UserModel保存到数据库\n\n```java\n@RequestMapping(\"/sharding\")\npublic class ShardingController extends JbootController {\n\n\n    public void index() {\n\n        UserModel user = new UserModel();\n        user.setId(StringUtils.uuid());\n        user.setName(\"Michael yang\");\n\n        user.save();\n\n        renderText(\"插入数据成功，请查看数据库...\");\n\n    }\n\n\n    public static void main(String[] args) {\n        Jboot.run(args);\n    }\n}\n\n```\n\n具体demo请参考：\n\nhttps://gitee.com/fuhai/jboot/tree/master/src/test/java/sharding\n\n\n# AOP\n\n## Google Guice\nJboot 的AOP功能，是使用了Google的Guice框架来完成的，通过AOP，我们可以轻易的在微服务体系中监控api的调用，轻易的使用@Cacheable，@CachePut，@CacheEvict等注解完成对代码的配置。\n## @Inject\n## @Bean\n\n# RPC远程调用\n在Jboot中，RPC远程调用是通过新浪的motan、或阿里的dubbo来完成的。计划会支持 grpc和thrift等。\n\n\n### 使用步骤：\n#### 第一步：配置Jboot.properties文件，内容如下：\n\n```java\n#默认类型为 motan (支持:dubbo,计划支持 grpc 和 thrift)\njboot.rpc.type = motan\n#发现服务类型为 consul ，支持zookeeper。\njboot.rpc.registryType = consul\njboot.rpc.registryAddress = 127.0.0.1:8500\n```\n\n#### 第二步：定义接口\n\n```java\npublic interface HelloService {\n    public String hello(String name);\n}\n```\n\n#### 第三步：通过@JbootrpcService注解暴露服务到注册中心\n\n```java\n@JbootrpcService\npublic class myHelloServiceImpl  implements HelloService {\n    public String hello(String name){\n         System.out.println(\"hello\" + name);\n         return \"hello ok\";\n    }\n}\n```\n\n#### 第四步：客户调用\n\n```java\n HelloService service = Jboot.me().service(HelloService.class);\n service.hello(\"michael\");\n```\n如果是在Controller中，也可以通过 @JbootrpcService 注解来获取服务，代码如下：\n\n```java\npublic class MyController extends JbootController{\n    \n    @JbootrpcService\n    HelloService service ;\n    \n    public void index(){\n        String text = service.hello();\n        renderText(text);\n    }\n    \n}\n```\n\n### 配置中心\n\n##### 下载consul\nhttps://www.consul.io \n\n##### 启动consul\n\n```java\nconsul -agent dev\n```\n\n#### zookeeper\n##### 下载zookeeper\nhttp://zookeeper.apache.org/releases.html\n\n##### 启动zookeeper\n下载zookeeper后，进入zookeeper目录下，找到 conf/zoo_example.cfg，重命名为 zoo.cfg。\n\nzoo.cfg 内容如下：\n\n```\ntickTime=2000\ndataDir=/var/lib/zookeeper\nclientPort=2181\n```\n\n在终端模式下，进入 zookeeper的更目录，执行：\n\n```java\nbin/zkServer.sh start\n```\n关于zookeeper更多的内容，请查看 http://zookeeper.apache.org 和 http://zookeeper.apache.org/doc/trunk/zookeeperStarted.html\n\n\n# MQ消息队列\nJboot 内置整个了MQ消息队列，使用MQ非常简单\n\n#### 第一步：配置jboot.properties文件，内容如下：\n```java\n#默认为redis (支持: redis,activemq,rabbitmq,hornetq,aliyunmq等 )\njboot.mq.type = redis\njboot.mq.redis.host = 127.0.0.1\njboot.mq.redis.password =\njboot.mq.redis.database =\n```\n\n#### 第二步：在服务器A中添加一个MQ消息监听器\n\n```java\nJboot.me().getMq().addMessageListener(new JbootmqMessageListener(){\n        @Override\n        public void onMessage(String channel, Object obj) {\n           System.out.println(obj);\n        }\n}, channel);\n```\n\n#### 第三步：服务器B发送一个消息\n\n```java\n Jboot.me().getMq().publish(yourObject, toChannel);\n```\n\n#### 注意：服务器A和服务器B在jboot.properties上应配置相同的内容。\n\n## RedisMQ\n## ActiveMQ\n\n# Cache缓存\nJboot中内置支持了ehcache、redis和 一个基于ehcache、redis研发的二级缓存ehredis，在使用Jboot缓存之前，先配置完成缓存的配置。\n\n### 使用步骤\n#### 第一步：配置jboot.properties文件，内容如下：\n\n```java\n#默认类型为ehcache ehcache (支持:ehcache,redis,ehredis)\njboot.cache.type = redis\njboot.cache.redis.host = 127.0.0.1\njboot.cache.redis.password =\njboot.cache.redis.database =\n```\n备注：ehredis 是一个基于ehcache和redis实现的二级缓存框架。\n\n#### 第二步：使用缓存\n\n```java\nJboot.me().getCache().put(\"cacheName\", \"key\", \"value\");\n```\n\n### 注意事项\nJboot的分布式session是通过缓存实现的，所以如果要启用Jboot的分布式session，请在缓存中配置类型为redis或者ehredis。\n\n\n## ehcache\n## redis\n## ehredis\n\n# http客户端\nJboot内置了一个轻量级的http客户端，可以通过这个客户端方便的对其他第三方http服务器进行数据请求和下载等功能。\n\n### Get请求\n\n```java\n@Test\npublic void testHttpGet(){\n    String html = Jboot.httpGet(\"https://www.baidu.com\");\n    System.out.println(html);\n}\n```\n\n或者\n\n```java\n@Test\npublic void testHttpPost(){\n    Map<String, Object> params  = new HashMap<>();\n    params.put(\"key1\",\"value1\");\n    params.put(\"key2\",\"value2\");\n\n\n    String html = Jboot.httpGet(\"http://www.oschina.net/\",params);\n    System.out.println(html);\n}\n```\n\n### Post请求\n\n```java\n@Test\npublic void testHttpPost(){\n    String html = Jboot.httpPost(\"http://www.xxx.com\");\n    System.out.println(html);\n}\n```\n\n或者\n\n```java\n@Test\npublic void testHttpPost(){\n    Map<String, Object> params  = new HashMap<>();\n    params.put(\"key1\",\"value1\");\n    params.put(\"key2\",\"value2\");\n\n\n    String html = Jboot.httpPost(\"http://www.oschina.net/\",params);\n    System.out.println(html);\n}\n```\n\n### 文件上传\n\n```java\n@Test\npublic void testHttpUploadFile(){\n    Map<String, Object> params  = new HashMap<>();\n    params.put(\"file1\",file1);\n    params.put(\"file2\",file2);\n\n\n    String html = Jboot.httpPost(\"http://www.oschina.net/\",params);\n    System.out.println(html);\n}\n```\n备注：文件上传其实和post提交是一样的，只是params中的参数是文件。\n\n### 文件下载\n\n```java\n@Test\npublic void testHttpDownload() {\n\n    String url = \"http://www.xxx.com/abc.zip\";\n    File downloadToFile = new File(\"/xxx/abc.zip\");\n\n    JbootHttpRequest request = JbootHttpRequest.create(url, null, JbootHttpRequest.METHOD_GET);\n    request.setDownloadFile(downloadToFile);\n\n    JbootHttpResponse response = Jboot.me().getHttp().handle(request);\n\n    if (response.isError()){\n        downloadToFile.delete();\n    }\n\n    System.out.println(downloadToFile.length());\n}\n```\n\n\n\n# metrics数据监控\nJboot的监控机制是通过Metrics来来做监控的，要启用metrics非常简单，通过在jboot.properties文件配置上`jboot.metrics.url`就可以启用metrics。\n\n例如\n\n```xml\njboot.metrics.url = /metrics.html\n```\n我们就可以通过访问 `http://host:port/metrics.html` 来访问到metrics数据情况。\n\n### 添加metrics数据\n默认通过Url访问到的数据是没有具体内容，因为metrics无法得知要显示什么样的数据内容。例如，我们要统计某个action的用户访问量，可以通过在action里编写如下代码。\n\n```java\npublic void myaction() {\n\n    Jboot.me().getMetric().counter(\"myaction\").inc();\n\n    renderText(\"my action\");\n}\n```\n\n当我们访问myaction这个地址后，然后再通过浏览器`http://host:port/metrics.html`访问，我们就能查看到如下的json数据。\n\n```js\n{\n	\"version\": \"3.1.3\",\n	\"gauges\": {},\n	\"counters\": {\n		\"myaction\": {\n				\"count\": 1\n			}\n	},\n	\"histograms\": {},\n	\"meters\": {},\n	\"timers\": {}\n}\n```\n当再次访问`myaction`后，count里面的值就变成2了。\n\n### metrics与Ganglia\n\n\n### metrics与Grafana\n\n### metrics与jmx\nmetrics与jmx集成非常简单，只需要在jboot.properties文件添加如下配置：\n\n```xml\njboot.metrics.jmxReporter = true\n```\n然后，我们就可以通过`JConsole`或者`VisualVM`进行查看了。\n\n\n# 容错与隔离\n\n### hystrix配置\nJboot的容错、隔离和降级服务、都是通过`Hystrix`来实现的。在RPC远程调用中，Jboot已经默认开启了Hystrix的监控机制，对数默认错误率达到50%的service则立即返回，不走网络。\n\n\n### Hystrix Dashboard 部署\n要查看hystrix的数据，我们需要部署`Hystrix Dashboard`。然后通过`Hystrix Dashboard`来查看。\n\n通过Gradle来编译：\n\n```\n$ git clone https://github.com/Netflix/Hystrix.git\n$ cd Hystrix/hystrix-dashboard\n$ ../gradlew appRun\n> Building > :hystrix-dashboard:appRun > Running at http://localhost:7979/hystrix-dashboard\n```\n\n或者通过docker来运行hystrix-dashboard:\n\n```java\ndocker run --rm -ti -p 7979:7979 kennedyoliveira/hystrix-dashboard\n```\n\n运行`hystrix-dashboard`成功后，通过浏览器输入`http://localhost:7979/hystrix-dashboard`就可以看到如下图显示：\n\n\n ![](https://github.com/Netflix/Hystrix/wiki/images/dashboard-home.png)\n\n\n### 通过 Hystrix Dashboard 查看数据\n接下来，我们需要配置jboot应用的hystrix监控地址，配置如下：\n\n```\njboot.hystrix.url = /hystrix.stream\n```\n然后在上面图片中，填写url地址为：`http://host:port/hystrix.stream`,并点击`monitor stream`按钮,就可以看到如下图显示，所以的远程调用方法都统计到了。\n \n \n **注意：** 如果是通过docker启动的`hystrix-dashboard`，`http://host:port/hystrix.stream`中的host一定是本机的真实IP地址。\n\n \n ![](https://github.com/Netflix/Hystrix/wiki/images/hystrix-dashboard-netflix-api-example-iPad.png)\n\n### 自定义监控隔离\n\n# Opentracing数据追踪\nJboot在分布式下，对数据的追踪是通过opentracing来实现的，opentracing官方地址（http://opentracing.io ）\n\n### Opentracing简介\nOpenTracing（http://opentracing.io ）通过提供平台无关、厂商无关的API，使得开发人员能够方便的添加（或更换）追踪系统的实现。OpenTracing正在为全球的分布式追踪，提供统一的概念和数据标准。\n\n目前，已经有了诸如 UBER，LightStep，Apple，yelp，workiva等公司在跟进，以及开源团队：ZIPKIN，appdash，TRACER，JAEGER，GRPC等的支持。\n\n已经支持 opentracing-api的开源库有：Zipkin，Jaeger（Uber公司的），Appdash，LightStep，Hawkular，Instana，sky-walking，inspectIT，stagemonitor等。具体地址请查看：http://opentracing.io/documentation/pages/supported-tracers.html\n\n### Opentracing在Jboot上的配置\n在jboot中启用opentracing非常简单，只需要做如下配置：\n\n```java\njboot.tracing.type=zipkin\njboot.tracing.serviceName=service1\njboot.tracing.url=http://127.0.0.1:9411/api/v2/spans\n```\n同步简单几个配置，就可以启动opentracing对数据的追踪，并把数据传输到对应的服务器上，例如使用的是zipkin，那么就会传输到zipkin的server上。\n\n### Zipkin\nzipkin官网： http://zipkin.io/ \n\n#### zipkin快速启动\n\n```java\nwget -O zipkin.jar \'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec\'\njava -jar zipkin.jar\n```\n\n或者通过docker来运行：\n\n```java\ndocker run -d -p 9411:9411 openzipkin/zipkin\n```\n\n或者 自己编译zipkin源代码，然后通过以下方式执行：\n\n```java\n# Build the server and also make its dependencies\n$ ./mvnw -DskipTests --also-make -pl zipkin-server clean install\n# Run the server\n$ java -jar ./zipkin-server/target/zipkin-server-*exec.jar\n```\n\n#### 使用zipkin\n通过以上步骤，把zipkin启动后，只需要在 jboot.properties 文件把 jboot.tracing.url 的属性修改为zipkin的地址即可：\n\n```\njboot.tracing.url = http://127.0.0.1:9411/api/v2/spans\n```\n\n配置之后，我们就可以通过zipkin来查看jboot追踪的数据了。\n![](http://zipkin.io/public/img/web-screenshot.png)\n\n### SkyWalking\nSkyWalking官网：http://skywalking.org ，Skywalking为国人开发，据说目前 **华为开发云**、**当当网** 等已经 加入 Skywalking 生态系统，具体查看：https://www.oschina.net/news/89756/devcloud-dangdang-join-skywalking \n\n#### SkyWalking快速启动\n#### 使用SkyWalking\n\n### 其他\n\n\n# 统一配置中心\n在jboot中，已经内置了统一配置中心，当中心配置文件修改后，分布式服务下的所有有用的额配置都会被修改。在某些情况下，如果统一配置中心出现宕机等情况，微服务将会使用本地配置文件当做当前配置信息。\n\n## 部署统一配置中心服务器\n部署统一配置服务器非常简单，不需要写一行代码，把jboot.proerties的配置信息修改如下，并启动jboot，此时的jboot就已经是一个统一配置中心了。\n\n```\njboot.config.serverEnable=true\njboot.config.path=/Users/michael/Desktop/test\n```\n在以上配置中，我们可以把所有的配置文件(.properties文件)放到目录 `/Users/michael/Desktop/test` 目录下，当该目录下新增配置文件、修改配置文件、删除配置文件都会通过http暴露出去。\n\n当启动 jboot 后，我们可以通过浏览器输入 `http://127.0.0.1:8080/jboot/config`来查看配置情况，微服务客户端也是定时访问这个url地址来读取配置信息。\n\n\n## 连接统一配置中心\n\n要启用远程配置也非常简单，只需要在微服务添加下配置即可。\n\n```\njboot.config.remoteEnable=true\njboot.config.remoteUrl=http://127.0.0.1:8080/jboot/config\n```\n当启用远程配置后，服务会优先使用远程配置，在远程配置未配置 或 宕机的情况下使用本地配置。\n\n# Swagger api自动生成\n\n## swagger简介\n\n## swagger使用\n\n\n### 第一步：配置并启用swagger\n在 jboot.properties上添加如下配置：\n\n```java\njboot.swagger.path=/swaggerui\njboot.swagger.title=Jboot API 测试\njboot.swagger.description=这真的真的真的只是一个测试而已，不要当真。\njboot.swagger.version=1.0\njboot.swagger.termsOfService=http://jboot.io\njboot.swagger.contact=email:fuhai999@gmail.com;qq:123456\njboot.swagger.host=127.0.0.1:8080 \n```\n\n### 第二步：下载swagger ui放到resource目录下\n注意，这里一定要放在resource的 `swaggerui` 目录，因为以上的配置中是`jboot.swagger.path=/swaggerui`,当然可以通过这个配置来修改这个存放目录。\n\n另：swagger ui 的下载地址是：https://github.com/swagger-api/swagger-ui，下载其 `dist` 目录即可，只需要这个目录里的文件。\n\n### 第三步：通过注解配置Controller的api\n\n代码如下：\n\n```java\n@SwaggerAPIs(name = \"测试接口\", description = \"这个接口集合的描述\")\n@RequestMapping(\"/swaggerTest\")\npublic class MySwaggerTestController extends JbootController {\n\n\n    @SwaggerAPI(description = \"测试description描述\", summary = \"测试summary\", operationId = \"testOnly\",\n            params = {@SwaggerParam(name = \"name\", description = \"请输入账号名称\")}\n    )\n    public void index() {\n        renderJson(Ret.ok(\"k1\", \"v1\").set(\"name\", getPara(\"name\")));\n    }\n\n\n    @SwaggerAPI(description = \"进行用户登录操作\", summary = \"用户登录API\", method = \"post\",\n            params = {\n                    @SwaggerParam(name = \"name\", description = \"请输入账号名称\"),\n                    @SwaggerParam(name = \"pwd\", description = \"请输入密码\", definition = \"MySwaggerPeople\")\n            }\n    )\n    public void login() {\n        renderJson(Ret.ok(\"k2\", \"vv\").set(\"name\", getPara(\"name\")));\n    }\n}\n```\n\n### 第四步：浏览器访问swagger生成api文档\n在第一步的配置中，因为`jboot.swagger.path=/swaggerui`，所以我们访问如下地址：`http://127.0.0.1:8080/swaggerui` 效果如下图所示。\n\n![](http://oss.yangfuhai.com/markdown/jboot/swagger/01.png)\n图片1\n\n![](http://oss.yangfuhai.com/markdown/jboot/swagger/02.png)\n图片2\n\n在图片2中，我们可以输入参数，并点击 `Execute` 按钮进行测试。\n\n## 5个swagger注解\n\n\n| 指令         |  描述  |\n| ------------- | -----|\n| SwaggerAPIs  | 在Controller上进行配置，指定Controller api的描述|\n| SwaggerAPI | 在Controller上某个action进行注解 |\n| SwaggerDefinition  |  |\n| SwaggerDefinitionEnum  |  |\n| SwaggerParam  |  |\n| SwaggerResponse  |  | \n\n# 其他\n\n## SPI扩展\nSPI的全名为Service Provider Interface。\n\n### SPI具体约定\n当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现类。而jboot装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入。\n\n### Jboot SPI模块\n在jboot中，一下模块已经实现了SPI机制。\n\n- Jbootrpc\n- JbootHttp\n- JbootCache\n- Jbootmq\n- JbootSerializer\n\n例如，在JbootCache中，内置了三种实现方案：ehcache、redis、ehredis。在配置文件中，我看可以通过 `jboot.cache.type = ehcache` 的方式来指定在Jboot应用中使用了什么样的缓存方案。\n\n但是，在Jboot中，通过SPI机制，我们一样可以扩展出第4、第5甚至更多的缓存方案出来。\n\n扩展步骤如下：\n\n- 第一步：编写JbootCache的子类\n- 第二步：通过@JbootSpi注解给刚刚编写的类设置上一个名字，例如：mycache\n- 第三步：通过在jboot.properties文件中配置上类型为 mycache，配置代码如下：\n\n```xml\njboot.cache.type = mycache\n```\n\n通过以上三步，我们就可以完成了对JbootCache模块的扩展，其他模块类似。\n\n## JbootEvnet事件机制\n为了解耦，Jboot内置了一个简单易用的事件系统，使用事件系统非常简单。\n\n#### 第一步，注册事件的监听器。\n\n```java\n@EventConfig(action = {“event1”,\"event2\"})\npublic class MyEventListener implements JbootEventListener {\n    \n    public  void onMessage(JbootEvent event){\n        Object data = event.getData();\n        System.out.println(\"get event:\"data);\n    }\n}\n```\n通过 @EventConfig 配置 让MyEventListener监听上 event1和event2两个事件。\n\n#### 第二步，在项目任何地方发生事件\n\n```java\nJboot.sendEvent(\"event1\",  object)\n```\n\n\n\n## 自定义序列化\n自定义序列化是通过Jboot的SPI机制来实现的，请参考 [SPI扩展](#SPI扩展)。\n\n## 配置文件\n\n### 读取jboot.properties的配置信息\n要读取jboot.properties的配置信息非常简单，例如我们配置内容如下：\n\n```xml\njboot.myconfig.name=aaa\njboot.myconfig.passowrd=bbb\njboot.myconfig.age=10\n```\n要读取这个配置信息，我们需要定义我们的一个model类，并通过@PropertieConfig注解给我们的类配置上类与配置文件的对应关系，如下所示：\n\n```java\n@PropertieConfig(prefix=\"jboot.myconfig\")\npublic class MyConfigModel{\n    private String name;\n    private String password;\n    private int age;\n\n    //getter setter 略\n}\n```\n\n*注意：* 类名MyConfigModel随便取\n\n编写好配置类MyConfigModel后，我们就可以通过如下代码来读取到配置信息：\n\n```java\nMyConfigModel config = Jboot.config(MyConfigModel.class);\n```\n\n### 读取自定义配置文件的配置信息\n\n在以上章节中，我们已经知道了如何来读取jboot.properties的配置文件，在某些场景下，可能需要我们把我们的配置信息编写到一个独立的properties配置文件里面去，例如：在我们的项目中有一个叫 michael.properties 文件，文件的内容如下：\n\n```xml\njboot.myconfig.name=aaa\njboot.myconfig.passowrd=bbb\njboot.myconfig.age=10\n```\n\n那么，一样的，我们需要编写一个model，并配置上@PropertieConfig注解，与读取jboot.properties文件不同的是，@PropertieConfig 需要添加上file配置，内容如下：\n\n```java\n@PropertieConfig(prefix=\"jboot.myconfig\",file=\"michael.properties\")\npublic class MyConfigModel{\n    private String name;\n    private String password;\n    private int age;\n\n    //getter setter 略\n}\n```\n\n然后，和读取jboot.properties一样。\n\n\n```java\nMyConfigModel config = Jboot.config(MyConfigModel.class);\n```\n\n## 分布式session\n\n\n## 代码生成器\nJboot内置了一个简易的代码生成器，可以用来生成model层和Service层的基础代码，在生成代码之前，请先配置jboot.properties关于数据库相关的配置信息。\n\n### 使用步骤\n\n#### 第一步：配置数据源\n```xml\njboot.datasource.type=mysql\njboot.datasource.url=jdbc:mysql://127.0.0.1:3306/jbootdemo\njboot.datasource.user=root\njboot.datasource.password=your_password\n```\n\n#### 第二步：通过JbootModelGenerator生成model代码\n```java\n  public static void main(String[] args) {\n  \n  		//model 的包名\n        String modelPackage = \"io.jboot.test\";\n        \n        JbootModelGenerator.run(modelPackage);\n\n    }\n```\n\n#### 第三步：通过JbootServiceGenerator生成Service代码\n```java\n  public static void main(String[] args) {\n  \n  		//生成service 的包名\n        String basePackage = \"io.jboot.testservice\";\n        //依赖model的包名\n        String modelPackage = \"io.jboot.test\";\n        \n        JbootServiceGenerator.run(basePackage, modelPackage);\n\n    }\n```\n\n#### 其他\n当没在jboot.properties文件配置数据源的时候，可以通过如下代码来使用：\n\n```java\n public static void main(String[] args) {\n\n        Jboot.setBootArg(\"jboot.datasource.url\", \"jdbc:mysql://127.0.0.1:3306/jbootdemo\");\n        Jboot.setBootArg(\"jboot.datasource.user\", \"root\");\n\n        String basePackage = \"io.jboot.codegen.service.test\";\n        String modelPackage = \"io.jboot.codegen.test.model\";\n        JbootServiceGenerator.run(basePackage, modelPackage);\n\n    }\n\n```\n\n\n\n\n\n# 项目构建\n在Jboot中已经内置了高性能服务器undertow，undertow的性能比tomcat高出很多（具体自行搜索：undertow vs tomcat），所以jboot构建和部署等不再需要tomcat。在Jboot构建的时候，在linux平台下，会生成jboot.sh 在windows平台下会生成jboot.bat脚本，直接执行该脚本即可。\n\n生成jboot.sh或者jboot.bat，依赖maven的appassembler插件，因此，你的maven配置文件pom.xml需要添加如下配置：\n\nconfig pom.xml\n\n```xml\n\n<build>\n    <plugins>\n        <plugin>\n            <groupId>org.apache.maven.plugins</groupId>\n            <artifactId>maven-compiler-plugin</artifactId>\n            <configuration>\n                <source>1.8</source>\n                <target>1.8</target>\n                <encoding>UTF-8</encoding>\n                <!--必须添加compilerArgument配置，才能使用JFinal的Controller方法带参数的功能-->\n                <compilerArgument>-parameters</compilerArgument>\n            </configuration>\n        </plugin>\n\n\n        <plugin>\n            <groupId>org.codehaus.mojo</groupId>\n            <artifactId>appassembler-maven-plugin</artifactId>\n            <version>1.10</version>\n            <configuration>\n            \n               <assembleDirectory>${project.build.directory}/app</assembleDirectory>\n                <repositoryName>lib</repositoryName>\n                <binFolder>bin</binFolder>\n                <configurationDirectory>webRoot</configurationDirectory>\n                <copyConfigurationDirectory>true</copyConfigurationDirectory>\n                <configurationSourceDirectory>src/main/resources</configurationSourceDirectory>\n                <repositoryLayout>flat</repositoryLayout>\n                <encoding>UTF-8</encoding>\n                <logsDirectory>logs</logsDirectory>\n                <tempDirectory>tmp</tempDirectory>\n\n                <programs>\n                    <!--程序打包 mvn package appassembler:assemble -->\n                    <program>\n                        <mainClass>io.jboot.Jboot</mainClass>\n                        <id>jboot</id>\n                        <platforms>\n                            <platform>windows</platform>\n                            <platform>unix</platform>\n                        </platforms>\n                    </program>\n                </programs>\n\n                <daemons>\n                    <!-- 后台程序打包：mvn clean package appassembler:generate-daemons -->\n                    <daemon>\n                        <mainClass>io.jboot.Jboot</mainClass>\n                        <id>jboot</id>\n                        <platforms>\n                            <platform>jsw</platform>\n                        </platforms>\n                        <generatorConfigurations>\n                            <generatorConfiguration>\n                                <generator>jsw</generator>\n                                <includes>\n                                    <include>linux-x86-32</include>\n                                    <include>linux-x86-64</include>\n                                    <include>macosx-universal-32</include>\n                                    <include>macosx-universal-64</include>\n                                    <include>windows-x86-32</include>\n                                    <include>windows-x86-64</include>\n                                </includes>\n                                <configuration>\n                                    <property>\n                                        <name>configuration.directory.in.classpath.first</name>\n                                        <value>webRoot</value>\n                                    </property>\n                                    <property>\n                                        <name>wrapper.ping.timeout</name>\n                                        <value>120</value>\n                                    </property>\n                                    <property>\n                                        <name>set.default.REPO_DIR</name>\n                                        <value>lib</value>\n                                    </property>\n                                    <property>\n                                        <name>wrapper.logfile</name>\n                                        <value>logs/wrapper.log</value>\n                                    </property>\n                                </configuration>\n                            </generatorConfiguration>\n                        </generatorConfigurations>\n                    </daemon>\n                </daemons>\n            </configuration>\n        </plugin>\n    </plugins>\n</build>\n```\n\n#### 进行maven构建\n\n```java\nmvn package appassembler:assemble\n```\n构建完毕后，会在target目录下生成一个app文件夹，在app文件的bin目录下会有一个jboot脚本（或者jboot.bat）。\n\n#### 启动应用\n```java\ncd yourProjectPath/target/app/bin\n./jboot\n```\n\n##### 在启动的时候添加上自己的配置信息\n\n```java\ncd yourProjectPath/target/app/bin\n./jboot --jboot.server.port=8080 --jboot.rpc.type=local\n```\n##### 使用你自己的配置文件来代替 jboot.properties\n\n```java\ncd yourProjectPath/target/app/bin\n./jboot --jboot.model=dev --jboot.server.port=8080\n```\n上面的命令启动后，会使用 `jboot-dev.proerties` 文件来替代 `jboot.properties` 同时设置 jboot.server.port=8080（服务器端口号为8080）\n\n\n#### 后台程序\n\n在以上文档中，如果通过如下代码进行构建的。\n\n```java\nmvn package appassembler:assemble\n```\n构建会生成 app目录，及对应的jboot脚本，但是jboot在执行的时候是前台执行的，也就是必须打开一个窗口，当关闭这个窗口后，jboot内置的服务器undertow也会随之关闭了，在正式的环境里，我们是希望它能够以服务的方式在后台运行。\n\n那么，如果构建一个后台运行的程序呢？步骤如下：\n\n##### 第一步：执行如下maven编译\n\n```java\nmvn clean package appassembler:generate-daemons\n```\nmaven命令执行完毕后，会在target下生成如下文件夹 `/generated-resources/appassembler/jsw/jboot` , 文件中我们会找到bin目录，生成的后台脚本jboot（或jboot.bat）会存放在bin目录里。\n\n##### 第二步：启动应用\n```java\ncd yourProjectPath/target/generated-resources/appassembler/jsw/jboot/bin\n./jboot\n```\n此时，启动的应用为后台程序了。\n\n\n# 鸣谢\nrpc framework: \n\n* motan(https://github.com/weibocom/motan)\n* grpc(http://grpc.io)\n* thrift(https://github.com/apache/thrift)\n\nmq framework:\n\n* activemq\n* rabbitmq\n* redis mq\n* hornetq\n* aliyun mq\n\ncache framework\n\n* ehcache\n* redis\n\ncore framework:\n\n* jfinal (https://github.com/jfinal/jfinal)\n* undertow (https://github.com/undertow-io/undertow)\n* guice (https://github.com/google/guice)\n* metrics (https://github.com/dropwizard/metrics)\n* hystrix (https://github.com/Netflix/Hystrix)\n* shiro （https://github.com/apache/shiro）\n\n# 联系作者\n* qq:1506615067\n* wechat：wx198819880\n* email:fuhai999#gmail.com\n\n# 常见问题\n\n- 使用Jboot后还能自定义Jfinal的配置文件吗？\n	- 答：可以使用，目前提供两种方案。\n		- 方案1（推荐）：编写一个类，随便起个名字，继承 JbootAppListenerBase ,然后复写里面的方法。\n		- 方案2（不推荐）：编写自己的JfinalConfig，继承 JbootAppConfig ，然后在 jboot.properties 的 jboot.jfinalConfig 配置上自己的类名。注意，在自己的config中，请优先调用super方法。例如在configConstant中，请先调用super.configConstant(constants)。\n\n		\n		\n		\n		\n		\n		\n		\n		\n		\n		\n		\n		\n		\n		\n\n', '2017-12-21 15:44:31', '5ff5b3f5d94c44b3a2366b6e21cfcbfe', 'on', 'q\'w去温泉123dddd1222', 'test', 0, 0, 'on', 'off');
INSERT INTO `dms_article` VALUES ('f1c30ae9336347f7bf3032bb6f1feb71', '第一个文章标题测试', '按时打算的', '2017-12-21 10:52:34', '5ff5b3f5d94c44b3a2366b6e21cfcbfe', 'on', '第一个文章内容测试', '测试1', 0, 0, 'on', 'on');
INSERT INTO `dms_article` VALUES ('f1c30ae9336347f7bf3032bb6f1feb72', '第一个文章标题测试', '按时打算的', '2017-12-21 10:52:34', '5ff5b3f5d94c44b3a2366b6e21cfcbfe', 'on', '第一个文章内容测试', '测试1', 0, 0, 'on', 'on');
INSERT INTO `dms_article` VALUES ('f1c30ae9336347f7bf3032bb6f1feb7a', '第一个文章标题测试', '按时打算的', '2017-12-21 10:52:34', '5ff5b3f5d94c44b3a2366b6e21cfcbfe', 'on', '第一个文章内容测试', '测试1', 0, 0, 'on', 'on');
COMMIT;

-- ----------------------------
-- Table structure for dms_config
-- ----------------------------
DROP TABLE IF EXISTS `dms_config`;
CREATE TABLE `dms_config` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT '' COMMENT '配置名称',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `desc` varchar(1000) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 配置表';

-- ----------------------------
-- Records of dms_config
-- ----------------------------
BEGIN;
INSERT INTO `dms_config` VALUES (1, 'webAppName', '呆萌狮', '2017-12-13 10:35:48', '站点名称');
INSERT INTO `dms_config` VALUES (2, 'webAppUrl', 'http://127.0.0.1', '2017-12-13 10:35:46', '站点链接地址');
COMMIT;

-- ----------------------------
-- Table structure for dms_menu
-- ----------------------------
DROP TABLE IF EXISTS `dms_menu`;
CREATE TABLE `dms_menu` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `super_id` int(36) DEFAULT '0' COMMENT '父级菜单 ID',
  `type_id` varchar(36) DEFAULT NULL COMMENT '菜单类型ID',
  `is_open` varchar(4) DEFAULT NULL COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  `desc` varchar(1000) DEFAULT NULL COMMENT '备注说明',
  `serial_num` int(20) DEFAULT '0' COMMENT '序号,用于排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT=' 菜单表';

-- ----------------------------
-- Records of dms_menu
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu` VALUES (1, '菜单管理', '/admin/menu', '2017-12-09 01:48:50', 2, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (2, '系统管理', NULL, '2017-12-09 03:52:33', 0, '1', 'on', '', 2);
INSERT INTO `dms_menu` VALUES (3, '用户管理', '/admin/user', '2017-12-09 04:02:46', 2, '1', 'on', '用户管理', 0);
INSERT INTO `dms_menu` VALUES (6, '仪表盘', '/admin/main', '2017-12-11 16:03:04', 2, '1', 'on', '后台初始化页面', 0);
INSERT INTO `dms_menu` VALUES (7, '管理员管理', NULL, '2017-12-14 10:20:31', 0, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (8, '管理员', '/admin/master', '2017-12-14 10:21:08', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (9, '角色管理', '/admin/role', '2017-12-14 10:22:05', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (16, '权限管理', '/admin/permission', '2017-12-16 00:01:41', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (18, '内容管理', NULL, '2017-12-21 10:38:30', 0, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (19, '文章管理', '/admin/article', '2017-12-21 10:38:47', 18, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (22, 'API文章演示', '/swaggerui', '2017-12-25 17:34:31', 2, '1', 'on', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for dms_menu_type
-- ----------------------------
DROP TABLE IF EXISTS `dms_menu_type`;
CREATE TABLE `dms_menu_type` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单类型名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 菜单类型表';

-- ----------------------------
-- Records of dms_menu_type
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu_type` VALUES (1, '左边菜单', '2017-12-06 18:06:32');
INSERT INTO `dms_menu_type` VALUES (2, '顶部菜单', '2017-12-09 01:08:55');
COMMIT;

-- ----------------------------
-- Table structure for dms_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_permission`;
CREATE TABLE `dms_permission` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `key` varchar(36) DEFAULT NULL COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(36) DEFAULT NULL COMMENT '权限名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of dms_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_permission` VALUES (1, 'admin:update', '2017-12-14 23:23:12', '管理员-更新', '', 'on');
INSERT INTO `dms_permission` VALUES (2, 'admin:create', '2017-12-06 17:21:38', '管理员-创建', '', 'on');
INSERT INTO `dms_permission` VALUES (3, 'admin:delete', '2017-12-14 23:23:32', '管理员-删除', '', 'on');
INSERT INTO `dms_permission` VALUES (4, 'admin:view', '2017-12-14 23:23:42', '管理员-查询', '', 'on');
INSERT INTO `dms_permission` VALUES (5, 'user:view', '2017-12-14 23:31:39', '用户-查询', '', 'on');
INSERT INTO `dms_permission` VALUES (11, 'user:update', '2017-12-19 11:07:09', '用户-更新', '', 'on');
INSERT INTO `dms_permission` VALUES (12, 'user:delete', '2017-12-19 11:07:50', '用户-删除', '', 'on');
INSERT INTO `dms_permission` VALUES (13, 'user:create', '2017-12-19 11:08:14', '用户-创建', '', 'on');
COMMIT;

-- ----------------------------
-- Table structure for dms_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_role`;
CREATE TABLE `dms_role` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `key` varchar(36) DEFAULT NULL COMMENT '角色标识',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of dms_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_role` VALUES (1, '超级管理员', '2017-12-06 17:15:48', '最高权限的管理员22333', 'admin', 'on');
INSERT INTO `dms_role` VALUES (2, '普通用户', '2017-12-15 23:14:16', '普通会员1', 'user', 'on');
COMMIT;

-- ----------------------------
-- Table structure for dms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_role_permission`;
CREATE TABLE `dms_role_permission` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` int(36) DEFAULT NULL COMMENT '角色ID',
  `pid` int(36) DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of dms_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_role_permission` VALUES (19, 5, 1, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (20, 5, 2, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (21, 5, 3, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (22, 5, 4, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (23, 5, 5, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (24, 6, 5, '2017-12-19 11:05:29', 'on');
INSERT INTO `dms_role_permission` VALUES (25, 2, 5, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (26, 2, 11, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (27, 2, 12, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (28, 2, 13, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (29, 1, 1, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (30, 1, 2, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (31, 1, 3, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (32, 1, 4, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (33, 1, 5, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (34, 1, 11, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (35, 1, 12, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (36, 1, 13, '2017-12-19 11:08:41', 'on');
COMMIT;

-- ----------------------------
-- Table structure for dms_user
-- ----------------------------
DROP TABLE IF EXISTS `dms_user`;
CREATE TABLE `dms_user` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(36) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(16) DEFAULT NULL COMMENT '密码',
  `nike_name` varchar(36) DEFAULT NULL COMMENT '昵称',
  `point` int(36) unsigned DEFAULT '0' COMMENT '积分',
  `email` varchar(36) DEFAULT NULL COMMENT '邮箱',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否启用',
  `desc` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of dms_user
-- ----------------------------
BEGIN;
INSERT INTO `dms_user` VALUES ('5df25d66dd124a839fe5d2d2d3250976', 'demo', '2017-12-25 15:50:32', '123', 'demo1', '普通用户', 1, 'demo', 'on', '123');
INSERT INTO `dms_user` VALUES ('5ff5b3f5d94c44b3a2366b6e21cfcbfe', 'admin', '2017-12-16 17:08:58', '123123123123', 'admin', '周洛熙', 0, 'zhoufengjob@sina.com', 'on', '超级管理员');
INSERT INTO `dms_user` VALUES ('9ba98abd6bbf4f3f8776257cf369af6c', 'DDDDD', '2017-12-19 00:05:12', '123', 'DDD', 'DDD', 111, 'DDD', 'on', '');
INSERT INTO `dms_user` VALUES ('c2abb2efa8d24c28b91b392b1dabd6b5', 'SSSS', '2017-12-19 00:05:36', '12', 'SSS', 'SS', 12, 'SS', 'on', '');
COMMIT;

-- ----------------------------
-- Table structure for dms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_user_role`;
CREATE TABLE `dms_user_role` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` int(36) DEFAULT NULL COMMENT '角色ID',
  `uid` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of dms_user_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_user_role` VALUES (4, 1, '5ff5b3f5d94c44b3a2366b6e21cfcbfe', '2017-12-19 00:00:39');
INSERT INTO `dms_user_role` VALUES (10, 2, '5df25d66dd124a839fe5d2d2d3250976', '2017-12-26 17:11:31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
