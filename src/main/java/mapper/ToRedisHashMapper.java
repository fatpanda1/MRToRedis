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
        String[] lines = line.split("\\|");
        String outPutKey = "";
        String outPutValue = "";

        //前缀
        String prefix = prop.getHashKeyPrefix();
        //获取key后缀的索引
        String[] suffixIndex = prop.getHashKeySuffixIndex().split(",");
        //拼接key后缀的所有值
        for (String item : suffixIndex) {
            //从数据源中按照后缀索引取值
            outPutKey += lines[Integer.parseInt(item)] + prop.getHashKeySuffixSeparator();
        }
        //key去掉后缀尾部的分隔符
        outPutKey = outPutKey.substring(0, outPutKey.length() - 1);

        //field取值
        //获取field的前缀
        String preField = prop.getHashFieldPrefix();
        //获取field后缀的索引
        String[] fieldSuffixIndex = prop.getHashFieldSuffixIndex().split(",");
        //拼接field后缀的所有值
        String outPutField = "";
        for (String item : fieldSuffixIndex) {
            //从数据源中按照后缀索引取值
            outPutField += lines[Integer.parseInt(item)] + prop.getHashFieldSuffixSeparator();
        }
        //key去掉后缀尾部的分隔符
        outPutField = preField + outPutField.substring(0, outPutKey.length() - 1);

        //value取值
        //获取value的值
        String[] valueIndex = prop.getHashValueIndex().split(",");
        for (String item : valueIndex){
            outPutValue += lines[Integer.parseInt(item)] + prop.getHashKeySuffixSeparator();
        }
        outPutValue = outPutValue.substring(0, outPutValue.length() - 1);
//       添加key的前缀
        context.write(new Text(prefix + outPutKey + "|" + outPutField),new Text(outPutValue));
    }
}
