package mapper;

import format.ToRedisOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ToRedisMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(ToRedisMain.class);

//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(ToRedisStrMapper.class);
//        job.setReducerClass(ProMarketReduce.class);

        job.setOutputFormatClass(ToRedisOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path("F:\\learning\\MapReduce_Test\\MRTORedis"));
        FileOutputFormat.setOutputPath(job,new Path("F:\\learning\\MapReduce_Test\\MRTORedisOut"));

        job.waitForCompletion(true);
    }
}
