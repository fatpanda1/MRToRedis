package format;

import bean.MyProperties;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ToRedisRecordWriter extends RecordWriter<Text, Text> {

    private MyProperties prop = new MyProperties();
    private Jedis jedis;
    private String host;
    private int port;

    public ToRedisRecordWriter(TaskAttemptContext job){
        MyProperties myProperties = new MyProperties();
        host = myProperties.getRedisOutNodes();
        port = myProperties.getRedisOutPort();
        jedis = new Jedis(host,port);
    }

    public void write(Text key, Text value) throws IOException, InterruptedException {
//        判断写入redis的数据类型
        if (prop.getRedisValueIsString()){ //String类型
            jedis.set(key.toString(), value.toString());
        } else if (prop.getRedisValueIsHash()){ //Hash类型
            Map map = new HashMap();
            String[] keys = key.toString().split("\\|");
            map.put(keys[1],value.toString());
            jedis.hmset(keys[0],map);
        }
    }

    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        if (jedis != null) {
            jedis.disconnect();
        }
    }
}
