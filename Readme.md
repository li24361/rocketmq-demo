##RocketMQ使用说明

###下载&&安装
```shell
wget https://github.com/alibaba/RocketMQ/releases/download/v3.2.2/alibaba-rocketmq-3.2.2.tar.gz

tar xvf alibaba-rocketmq-3.2.2.tar.gz
```
###启动Name Server
```shell
cd alibaba-rocketmq/bin/
#默认占用9876端口 指定日志输出的位置
nohup sh mqnamesrv > ../logs/mq.log 2>&1 &
```

###启动Broker
```shell
#默认端口10911（127.0.0.1:9876为nameserver，链接进行注册）指定日志输出的位置
nohup sh mqbroker -n "127.0.0.1:9876" > ../logs/mq.log 2>&1 &
```

执行 `more nohup.out` 显示
```
The Name Server boot success.
The broker[ZhengYings-MacBook-Pro.local, 192.168.23.39:10911] boot success. and name server is 127.0.0.1:9876
```
则代表启动成功

###停止服务
```shell
sh mqshutdown broker
sh mqshutdown namesrv
```

###1主1备配置

####生成broker配置模板
```shell
cd alibaba-rocketmq/bin/
sh mqbroker -m > broker.properties
#主配置
cp broker.properties broker-m.properties
#从配置
cp broker.properties broker-s.properties
```

####创建日志目录
```
#创建日志目录
mkdir -p /Users/zhengying/store
#创建数据存储目录
mkdir -p /Users/zhengying/store/commitlog
```

####启动

```shell
#启动nameserver
nohup sh mqnamesrv > ../logs/mq.log 2>&1 &
#启动主broker
nohup sh mqbroker -c ../conf/1m-1s-sync/broker-m.properties > ../logs/mq.log 2>&1 &
#启动从broker
nohup sh mqbroker -c ../conf/1m-1s-sync/broker-s.properties > ../logs/mq.log 2>&1 &
```

