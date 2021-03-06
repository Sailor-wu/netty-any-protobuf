package com.wjybxx.protobuf.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 资源加载器
 * 1.优先尝试从本地运行环境加载(debug优先)
 * 2.其次尝试从jar包环境加载
 */
public class ResourceLoader {

    private ResourceLoader(){
        // close
    }

    /**
     * 加载配置文件
     * @param cfgName
     * @return
     * @throws IOException
     */
    public static Properties loadConfig(String cfgName) throws IOException {
        InputStream stream = loadAsStream(cfgName);
        if (null==stream){
            return null;
        }

        try {
            Properties properties=new Properties();
            properties.load(stream);
            return properties;
        } finally {
            stream.close();
        }
    }

    /**
     * 以流形式加载一个文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public static InputStream loadAsStream(String fileName) throws IOException{
        try {
            // 优先尝试从本地运行环境加载(debug优先)
            InputStream curEnvironmentStream = findFromCurEnvironment(fileName);

            if (null!=curEnvironmentStream){
                return curEnvironmentStream;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // 其次尝试从jar包环境加载
        return findFromJarEnvironment(fileName);
    }

    /**
     * 从当前环境中寻找
     * @param fileName
     * @return
     * @throws IOException
     */
    private static InputStream findFromCurEnvironment(String fileName) throws IOException {
        String path=new File("").getAbsolutePath() + File.separator + fileName;
        System.out.println("try findFromCurEnvironment,path=" +path);

        File file=new File(path);
        if (file.exists() && file.isFile()){
            return new FileInputStream(file);
        }else {
            return null;
        }
    }

    /**
     * 在jar包环境中寻找
     * @return
     * @throws IOException
     */
    private static InputStream findFromJarEnvironment(String fileName) throws IOException{
        System.out.println("try findFromJarEnvironment,resource name=" + fileName);
       return ResourceLoader.class.getClassLoader().getResourceAsStream(fileName);
    }
}
