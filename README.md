# 通用MR写Redis使用说明

运行程序前需要根据需求首先配置writeToRedis.properties文件中得关键字，并将文件上传到hdfs服务器的指定路径中，默认为/testData/writeToRedis.properties

## 运行方式

在集群中执行`hadoop jar mrToRedis-1.0-SNAPSHOT-jar-with-dependencies.jar path1 path2`命令，其中，path1为需要读取的文件在hdfs服务器上的路径，path2为日志信息输出路径

## properties文件的配置

### 配置Redis服务器信息

```properties
#redis服务器的地址
redis.out.nodes=
#redis服务器端口
redis.out.port=

#判断类型的字段请填写false或者true,不可两个关键字同时为false或true
#redis的value是否为string类型
redis.value.is.string=
#redis的value是否为hash类型
redis.value.is.hash=

#redis的过期时间(不填写则默认不过期)
redis.expire.time=100000
```

### 如果redis的value类型为String，则配置以下关键字

```properties

#key的前缀
string.key.prefix=B_
#假设前缀为B_，则输出到redis的key为B_Key

#key后缀所对应文件中的位置
string.key.suffix.index=0,1
#假设数据源为jack|25|20190304,则key将由索引为0,1的值拼接
#即 jack+25

#多个key后缀的分隔符
string.key.suffix.separator=:
#数据源同上，假设key后缀由多个字段拼接，则字段之间用指定分隔符进行分隔
#即 jack:25

#value类型是否为json
string.value.is.json=false
#选择value是否以json格式输出，根据数据源不同可能需要重写JsonObj类

#value类型所在文件的位置
string.value.index=2,3,4
#可以类比关键字string.key.suffix.index，指定value由哪几个字段拼接而成，分隔符直接使用string.key.suffix.separator
```

### 如果Redis的value类型为Hash，则配置以下关键字

```properties
#需要取hashcode值的位置
hash.key.hashCode.index=0
#配置key值中需要取hashcode值的字段索引，其中同一字段可以既取原数值也取hashcode值，hash.key.hashCode.index和hash.key.suffix.index两个参数互相不冲突

#以下参数均可类比String类型的配置说明
#key的前缀
hash.key.prefix=H_

#key后缀所对应文件中的位置
hash.key.suffix.index=0

#多个key后缀的分隔符
hash.key.suffix.separator=:

#field的前缀
hash.field.prefix=hf_

#field后缀所对应文件中的位
hash.field.suffix.index=0,1

#多个field后缀的分隔符
hash.field.suffix.separator=:

#value所在文件中的位置
hash.value.index=2,3,4
```

