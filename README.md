# 在线教育

SpringCloud + Vue 

前后端分离

开发**在线视频**系统

视频学习网站+管理后台

## 项目模块说明

* **admin**<br>
  控台管理，vue cli项目
* **business**<br>
  核心业务模块，spring boot项目
* **doc**<br>
  项目相关的文档，包含项目数据库初始化脚本
* **eureka**<br>
  注册中心，spring boot项目
* **file**<br>
  文件模块，spring boot项目
* **gateway**<br>
  路由模块，spring boot项目
* **generator**<br>
  代码生成器
* **server**<br>
  公共jar模块，被business, file, system依赖
* **system**<br>
  系统模块，spring boot项目
* **web**<br>
  网站模块，vue cli项目


## 项目初始化

* 需要本地安装好idea, nodejs，jdk1.8, mysql5.7, navicat（数据库可视化工具）
* 将下载好的源码，用idea打开
* 刷新maven依赖
* 安装vue cli，参照课程4-1

```
npm install -g @vue/cli
```

* 下载node模块

```
初始化web模块
cd web
npm install

初始化admin模块
cd admin
npm install
```

* 新建数据库courseimooc，并courseimooc，密码courseimooc，参照课程3-1
* 数据库初始脚本在/doc/db/all.sql

## 项目启动

* 启动注册中心：EurekaApplication
* 启动路由模块：GatewayApplication
* 启动系统模块：SystemApplication
* 启动业务模块：BusinessApplication
* 启动文件模块：FileApplication
* 启动控台管理：admin\package.json
* 启动前端网站：web\package.json

## 页面访问

* 控台地址: http://localhost:8080/login<br>
  初始用户名密码：test/test
* 控台地址: http://localhost:8081<br>
  可以自己注册用户，短信验证码从后台日志看，或看sms表

## 资源配置

所有资源都在/doc/db/resource.json<br>
需要在控台上将所有的资源配置进去，并在角色管理中配置权限
权限配置好后，需要重新登录