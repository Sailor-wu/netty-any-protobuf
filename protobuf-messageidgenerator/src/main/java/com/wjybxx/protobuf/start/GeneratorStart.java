package com.wjybxx.protobuf.start;

import com.wjybxx.protobuf.generator.*;

import java.io.IOException;
import java.util.Properties;

public class GeneratorStart {

    public static void main(String[] args) throws IOException {
        System.out.println("-------------------start---------------------");

        Properties properties= ResourceLoader.loadConfig("generator.properties");
        ProtoMessageRepository allMessage = ProtoBufFileUtils.findAllMessage(properties);
        // 这里使用自己需要的策略(模式)
        MessageIdGenerator generator = new HashMessageIdGenerator();
        generator.generateMessageId(properties,allMessage);
        System.out.println("--------------------end----------------------.");
    }
}
