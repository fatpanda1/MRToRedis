package mapper;

import bean.MyProperties;
import com.google.gson.Gson;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import utils.JsonObj;

import java.io.IOException;

/**
 * 当以String类型写入Redis时的Mapper
 */
public class ToRedisStrMapper extends Mapper<LongWritable, Text, Text, Text> {
    private MyProperties prop = new MyProperties();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\\|");
        String outPutKey = "";
        String outPutValue = "";
        //前缀
        String prefix = prop.getStringKeyPrefix();
        //获取key后缀的索引
        String[] suffixIndex = prop.getStringKeySuffixIndex().split(",");
        //拼接key后缀的所有值
        for (String item : suffixIndex) {
            outPutKey += fields[Integer.parseInt(item)] + prop.getStringKeySuffixSeparator();
        }
        //key去掉后缀尾部的分隔符
        outPutKey = outPutKey.substring(0, outPutKey.length() - 1);

        //判断value数据类型是否为json
        if(prop.getStringValueIsJson()){
            //是json
            Gson gson = new Gson();
            JsonObj jsonObj = new JsonObj(fields[0],fields[1],fields[2],fields[3],fields[4]);
            outPutValue = gson.toJson(jsonObj);
        } else {
            //不是json则切分value值
            String[] valueIndex = prop.getStringValueIndex().split(",");
            for (String item : valueIndex){
                outPutValue += fields[Integer.parseInt(item)] + prop.getStringKeySuffixSeparator();
            }
            outPutValue.substring(0, outPutValue.length() - 1);
        }

        System.out.println(outPutKey + outPutValue);
//       添加key的前缀
        context.write(new Text(prefix + outPutKey),new Text(outPutValue));
    }
}
