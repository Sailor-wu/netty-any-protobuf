namespace wjybxx{

    /**
     * 消息名字到id的映射，用于注册处理器时使用的枚举
     * 使用const enum 即可，只在ts代码中出现即可
     */
    export const enum MessageEnum {
        // padding like UpperCase(messageName):messageId,
        SERVER_CLIENT_FIRST_MESSAGE=-916409472,
        CLIENT_SERVER_PING=-757590406,
        SERVER_CLIENT_PONG=571393264,
        SERVER_CLIENT_ONE_REQUEST=508972478,
        SERVER_CLIENT_ONE_REQUEST_RESULT=1917199806,
        SERVER_SERVER_FIRST_MESSAGE=1181121784,
        SERVER_SERVER_PING=-13509134,

    }

    /**
     * 消息实例对象映射
     */
    export class MessageMappingHolder {
        /**
         * 消息ID到class的映射
         */
        private static readonly messageId2classMapping:INumberMap<any>={
            // 由于未导出protoBuf的ts文件。因此这里会导致报错，暂时先屏蔽
            // padding like messageId:messageName,
            // -916409472:server_client_first_message,
            // -757590406:client_server_ping,
            // 571393264:server_client_pong,
            // 508972478:server_client_one_request,
            // 1917199806:server_client_one_request_result,
            // 1181121784:server_server_first_message,
            // -13509134:server_server_ping,
        };

        private static readonly messageName2idMapping:IStringMap<number>={
            // padding like messageName:messageId,
            "server_client_first_message":-916409472,
            "client_server_ping":-757590406,
            "server_client_pong":571393264,
            "server_client_one_request":508972478,
            "server_client_one_request_result":1917199806,
            "server_server_first_message":1181121784,
            "server_server_ping":-13509134,
        };

        /**
         * 获取消息id对应的class对象(解码器)
         * @param messageId
         */
        public static getMessageClass(messageId:number):any{
            return this.messageId2classMapping[messageId];
        }

        /**
         * 获取消息对应的messageId
         * @param msg
         */
        public static getMessageId(msg:IProtoMessage):number{
            // TODO 这里应该采用更好的方式获取类名。
            return this.messageName2idMapping[msg.constructor.name];
        }
    }

}
