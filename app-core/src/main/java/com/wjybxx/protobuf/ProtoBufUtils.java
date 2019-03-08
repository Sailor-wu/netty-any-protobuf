package com.wjybxx.protobuf;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ProtoBufUtils {

    private static final Logger logger= LoggerFactory.getLogger(ProtoBufUtils.class);

    private ProtoBufUtils() {
        // close
    }

    /**
     * 获取proto文件定义的文件的完整类名
     * @param javaPackageName 文件中的java_package
     * @param javaOuterClassName 文件中的java_outer_classname
     * @param messageName 消息体名字
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T extends MessageLite> Class<T> findMessageClass(String javaPackageName, String javaOuterClassName, String messageName) throws ClassNotFoundException {
        String classFullName=javaPackageName+"."+javaOuterClassName+"$"+messageName;
        return (Class<T>)Class.forName(classFullName);
    }

    /**
     * 反射获取消息体的Parser对象
     * @param messageClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Parser<T> findMessageParser(Class<T> messageClass) throws ReflectiveOperationException{
        // protoBuf3获取parser的静态方法 parser();
        Method method = messageClass.getDeclaredMethod("parser");
        if (null != method) {
            method.setAccessible(true);
            return (Parser<T>) method.invoke(null);
        }
        // proto2 静态parser域,public的
        Field field = messageClass.getDeclaredField("PARSER");
        field.setAccessible(true);
        return (Parser<T>) field.get(null);
    }
}
