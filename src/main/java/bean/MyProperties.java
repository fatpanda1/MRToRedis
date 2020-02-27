package bean;

import com.sun.org.apache.xpath.internal.operations.Bool;
import utils.GetPropertiesUtils;

import java.util.Properties;

public class MyProperties {

    private GetPropertiesUtils prop = new GetPropertiesUtils("deploy/writeToRedis.properties");

    private String serverAddress = prop.getProp("server.address");
    private String serverPassword = prop.getProp("server.password");
    private String serverPath = prop.getProp("server.path");
    private String redisOutNodes = prop.getProp("redis.out.nodes");
    private String redisOutPort = prop.getProp("redis.out.port");
    private String redisValueIsString = prop.getProp("redis.value.is.string");
    private String redisValueIsHash = prop.getProp("redis.value.is.hash");
    private String redisValueIsZset = prop.getProp("redis.value.is.zest");
    private String stringKeyPrefix = prop.getProp("string.key.prefix");
    private String stringKeySuffixIndex = prop.getProp("string.key.suffix.index");
    private String stringKeySuffixSeparator= prop.getProp("string.key.suffix.separator");
    private String stringValueIsJson = prop.getProp("string.value.is.json");
    private String stringValueIndex = prop.getProp("string.value.index");
    private String hashKeyPrefix = prop.getProp("hash.key.prefix");

    public String getServerAddress() {
        return serverAddress;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public String getServerPath() {
        return serverPath;
    }

    public String getRedisOutNodes() {
        return redisOutNodes;
    }

    public int getRedisOutPort() {
        return Integer.parseInt(redisOutPort);
    }

    public Boolean getRedisValueIsString() {
        return Boolean.parseBoolean(redisValueIsString);
    }

    public Boolean getRedisValueIsHash() {
        return Boolean.parseBoolean(redisValueIsHash);
    }

    public Boolean getRedisValueIsZset() {
        return Boolean.parseBoolean(redisValueIsZset);
    }

    public String getStringKeyPrefix() {
        return stringKeyPrefix;
    }

    public String getStringKeySuffixIndex() {
        return stringKeySuffixIndex;
    }

    public String getStringKeySuffixSeparator() {
        return stringKeySuffixSeparator;
    }

    public Boolean getStringValueIsJson() {
        return Boolean.parseBoolean(stringValueIsJson);
    }

    public String getStringValueIndex() {
        return stringValueIndex;
    }

    public String getHashKeyPrefix() {
        return hashKeyPrefix;
    }
}
