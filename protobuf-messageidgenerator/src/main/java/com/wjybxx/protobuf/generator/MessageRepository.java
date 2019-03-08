package com.wjybxx.protobuf.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class MessageRepository {
    /**
     * 所有的proto文件信息
     */
    private final LinkedHashMap<String,ProtoFileInfo> repository=new LinkedHashMap<>();

    public void addProtoFileInfo(ProtoFileInfo protoFileInfo){
        repository.put(protoFileInfo.getFileName(),protoFileInfo);
    }

    public ProtoFileInfo getProtoFileInfo(String fileName){
        return repository.get(fileName);
    }

    public List<ProtoFileInfo> values(){
        return Collections.unmodifiableList(new ArrayList<>(repository.values()));
    }
}
