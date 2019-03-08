package com.wjybxx.logger;

import com.wjybxx.protobuf.ResourceLoader;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoggerUtils {

    private static final String LOG_PROPERTIES_FILE_NAME = "log4j.properties";

    private LoggerUtils() {
    }

    /**
     * 创建日志文件
     * @param propertiesFilePath 配置文件路径
     * @param log4jLogPath 日志输出文件
     */
    public static void createLogFile(String propertiesFilePath, String log4jLogPath) throws IOException {
        System.setProperty("log4jLogPath",new File(log4jLogPath).getAbsolutePath());

        File file=new File(propertiesFilePath);
        if (file.exists() && file.isFile()){
            try(FileInputStream fileInputStream=new FileInputStream(file)){
                Properties properties=new Properties();
                properties.load(fileInputStream);
                PropertyConfigurator.configure(properties);
            }
        }else {
            throw new IllegalArgumentException("propertiesFilePath " + propertiesFilePath + " is invalid.");
        }
    }

    /**
     * 创建日志文件
     * 会在在当前运行环境和jar包下寻找配置文件
     * @param log4jLogPath 日志输出文件
     */
    public static void createLogFile(String log4jLogPath) throws IOException {
        System.setProperty("log4jLogPath",new File(log4jLogPath).getAbsolutePath());

        Properties properties=ResourceLoader.loadConfig(LOG_PROPERTIES_FILE_NAME);
        if (null==properties){
            throw new IllegalArgumentException("create log failed.");
        }
        PropertyConfigurator.configure(properties);
    }

}
