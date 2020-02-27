package format;

import bean.MyProperties;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Progressable;
import redis.clients.jedis.Jedis;

import java.io.IOException;

public class ToRedisOutputFormat extends FileOutputFormat<Text, Text> {

    public RecordWriter<Text,Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {

        return new ToRedisRecordWriter(job);
    }
}
