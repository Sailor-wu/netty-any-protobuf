namespace wjybxx{

    /**
     * 消息名字到id的映射，用于注册处理器时使用的枚举
     * 使用const enum 即可，只在ts代码中出现即可
     */
    export const enum MessageEnum {
        // padding like UpperCase(messageName):messageId,
        SERVER_CLIENT_FIRST_MESSAGE=10001,
        CLIENT_SERVER_PING=10002,
        SERVER_CLIENT_PONG=10003,
        SERVER_CLIENT_ONE_REQUEST=10004,
        SERVER_CLIENT_ONE_REQUEST_RESULT=10005,
        SERVER_SERVER_FIRST_MESSAGE=20001,
        SERVER_SERVER_PING=20002,

    }

    /**
     * 消息实例对象映射
     */
    export class MessageMappingHolder {
        /**
         * 消息ID到class的映射
         */
        private static readonly messageId2classMapping:INumberMap<any>={
            // padding like messageId:messageName,
            10001:server_client_first_message,
            10002:client_server_ping,
            10003:server_client_pong,
            10004:server_client_one_request,
            10005:server_client_one_request_result,
            20001:server_server_first_message,
            20002:server_server_ping,
        };

        private static readonly messageName2idMapping:IStringMap<number>={
            // padding like messageName:messageId,
            "server_client_first_message":10001,
            "client_server_ping":10002,
            "server_client_pong":10003,
            "server_client_one_request":10004,
            "server_client_one_request_result":10005,
            "server_server_first_message":20001,
            "server_server_ping":20002,
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
