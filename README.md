# iForms 后台管理系统

iForms web 问卷调查管理系统

#### 项目简介
一个基于 Spring Boot 2.2.4 、 Spring Boot Jpa、Mybatis、 JWT、Spring Security、Redis、Vue的前后端分离的后台管理系统



## 项目运行

#### 部署
1、修改配置文件

按需修改我们的 application-prod.yml

2、打包项目
```bash
mvn package
```

我们需要将项目打包并且上传到服务器，具体路径自己定

我的jar包位置：/opt/iforms/文件夹下iforms-system-1.0.0.jar

3、编写脚本
编写脚步操作 java 服务

(1) 启动脚本 start.sh
```bash
cd ../opt/iforms/
nohup java -jar -Xms64m -Xmx128m iforms-system-1.0.0.jar --spring.profiles.active=prd > /opt/iforms/nohup.out 2>&1 &
```
(2) 停止脚本 stop.sh
```bash
PID=$(ps -ef | grep iforms-system-1.0.0.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
echo Application is already stopped
else
echo kill $PID
kill $PID
fi
```
(3) 新建空白Log文件，保存日志 nohup.out
```bash
touch nohup.out
```
(4) 查看日志 log.sh
```bash
tail -f nohup.out
```
4、操作java服务：脚本创建完成后就可以操作 java 服务了
```bash
# 启动java
./start.sh
# 停止java服务
./stop.sh
# 查看日志
./log.sh
```

## LICENSE

MIT

