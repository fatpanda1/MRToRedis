package utils;

import java.io.*;
import java.util.Properties;

public class GetPropertiesUtils {

    private Properties prop = new Properties();
    private String proPath;
    public GetPropertiesUtils(String proPath){
        this.proPath = proPath;
    }

    public String getProp(String key){
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(proPath));
            prop.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
