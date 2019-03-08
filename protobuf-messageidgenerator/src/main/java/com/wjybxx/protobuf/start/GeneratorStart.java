package com.wjybxx.protobuf.start;

import com.wjybxx.protobuf.codegenerator.JavaEnumGenerator;
import com.wjybxx.protobuf.codegenerator.TypeScriptEnumGenerator;
import com.wjybxx.protobuf.generator.*;

import java.io.IOException;
import java.util.Properties;

public class GeneratorStart {

    public static void main(String[] args) throws IOException {
        Properties properties= ResourceLoader.loadConfig("generator.properties");
        if(null==properties){
            throw new IllegalArgumentException("can't find generator.properties");
        }

        MessageRepository messageRepository = ProtoBufFileUtils.findAllMessage(properties);

        // 这里使用自己需要的策略(模式)
//        SequenceMessageIdGenerator messageIdGenerator = new HashMessageIdGenerator();
        SequenceMessageIdGenerator messageIdGenerator = new SequenceMessageIdGenerator();
        System.out.println("\nstart generateMessageId, strategy="+messageIdGenerator.getClass().getSimpleName());
        messageIdGenerator.generateMessageId(properties,messageRepository);

        // 生成代码各端代码 (Java + TS)
        System.out.println("\nstart generate java enum");
        new JavaEnumGenerator().generateCode(properties,messageRepository);

        System.out.println("\nstart generate typescript enum");
        new TypeScriptEnumGenerator().generateCode(properties,messageRepository);

        System.out.println("\nsuccess.");
    }
}
