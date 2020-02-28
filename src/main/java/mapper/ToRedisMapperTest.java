package mapper;

import bean.MyProperties;
import com.google.gson.Gson;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import utils.JsonObj;

import java.io.IOException;

public class ToRedisMapperTest extends Mapper<LongWritable, Text, Text, Text> {
    private MyProperties prop = new MyProperties();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\\|");
        String outPutKey = fields[0];
        String outPutValue;
        //前缀
        String prefix = "";
            prefix = prop.getStringKeyPrefix();

            outPutValue = line.substring(outPutKey.length() + 1);

//       添加key的前缀
        context.write(new Text(prefix + outPutKey),new Text(outPutValue));
    }
}
