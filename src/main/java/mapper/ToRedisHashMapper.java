package mapper;

import bean.MyProperties;
import com.google.gson.Gson;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import utils.JsonObj;

import java.io.IOException;

public class ToRedisHashMapper extends Mapper<LongWritable, Text, Text, Text> {
    private MyProperties prop = new MyProperties();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\\|");
        String outPutKey = fields[0];
        String outPutValue;
        //前缀
        String prefix = "";
        //根据数据类型不同赋值不同的前缀
        if (prop.getRedisValueIsString()){  //String类型
            prefix = prop.getStringKeyPrefix();
        } else if (prop.getRedisValueIsHash()){ //Hash类型
            prefix = prop.getHashKeyPrefix();
        }

        //判断value数据类型是否为json
        if(prop.getStringValueIsJson()){
            Gson gson = new Gson();
            JsonObj jsonObj = new JsonObj(fields[0],fields[1],fields[2],fields[3],fields[4]);
            outPutValue = gson.toJson(jsonObj);
        } else {
            outPutValue = line.substring(outPutKey.length() + 1);
        }

//       添加key的前缀
        context.write(new Text(prefix + outPutKey),new Text(outPutValue));
    }
}
