namespace wjybxx{

    /**
     * 消息名字到id的映射，用于注册处理器时使用的枚举
     * 使用const enum 即可，只在ts代码中出现即可
     */
    export const enum MessageEnum {
        // padding like UpperCase(messageName):messageId,
        //MessageEnumPadding

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
            //messageId2ClassMapping
        };

        private static readonly messageName2idMapping:IStringMap<number>={
            // padding like messageName:messageId,
            //messageName2IdMapping
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