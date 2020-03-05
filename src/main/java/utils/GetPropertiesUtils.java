package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileContext;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class GetPropertiesUtils {

    private Properties prop = new Properties();
    private String proPath;
    public GetPropertiesUtils(String proPath){
        this.proPath = proPath;
    }

    public String getProp(String key){
//        try {
//            InputStream in = new BufferedInputStream(new FileInputStream(proPath));
//            prop.load(in);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            Configuration conf = new Configuration();
//            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            conf.set("dfs.client.use.datanode.hostname", "true");
            conf.set("dfs.replication","2");

            FileSystem fs = FileSystem.get(URI.create("hdfs://private001:9000"),conf);
            InputStream in = fs.open(new Path("/testData/writeToRedis.properties"));
            prop.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
