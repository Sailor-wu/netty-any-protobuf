// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: p_enum.proto

package com.wjybxx.generatedmessage;

public final class PEnum {
  private PEnum() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code wjybxx.RoleType}
   */
  public enum RoleType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>ROLE_TYPE_CODER = 0;</code>
     */
    ROLE_TYPE_CODER(0),
    /**
     * <code>ROLE_TYPE_MANAGER = 1;</code>
     */
    ROLE_TYPE_MANAGER(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ROLE_TYPE_CODER = 0;</code>
     */
    public static final int ROLE_TYPE_CODER_VALUE = 0;
    /**
     * <code>ROLE_TYPE_MANAGER = 1;</code>
     */
    public static final int ROLE_TYPE_MANAGER_VALUE = 1;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static RoleType valueOf(int value) {
      return forNumber(value);
    }

    public static RoleType forNumber(int value) {
      switch (value) {
        case 0: return ROLE_TYPE_CODER;
        case 1: return ROLE_TYPE_MANAGER;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<RoleType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        RoleType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<RoleType>() {
            public RoleType findValueByNumber(int number) {
              return RoleType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.wjybxx.generatedmessage.PEnum.getDescriptor().getEnumTypes().get(0);
    }

    private static final RoleType[] VALUES = values();

    public static RoleType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private RoleType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:wjybxx.RoleType)
  }

  /**
   * Protobuf enum {@code wjybxx.Gender}
   */
  public enum Gender
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>MAN = 0;</code>
     */
    MAN(0),
    /**
     * <code>WOMAN = 1;</code>
     */
    WOMAN(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>MAN = 0;</code>
     */
    public static final int MAN_VALUE = 0;
    /**
     * <code>WOMAN = 1;</code>
     */
    public static final int WOMAN_VALUE = 1;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Gender valueOf(int value) {
      return forNumber(value);
    }

    public static Gender forNumber(int value) {
      switch (value) {
        case 0: return MAN;
        case 1: return WOMAN;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Gender>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Gender> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Gender>() {
            public Gender findValueByNumber(int number) {
              return Gender.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.wjybxx.generatedmessage.PEnum.getDescriptor().getEnumTypes().get(1);
    }

    private static final Gender[] VALUES = values();

    public static Gender valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Gender(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:wjybxx.Gender)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014p_enum.proto\022\006wjybxx*6\n\010RoleType\022\023\n\017RO" +
      "LE_TYPE_CODER\020\000\022\025\n\021ROLE_TYPE_MANAGER\020\001*\034" +
      "\n\006Gender\022\007\n\003MAN\020\000\022\t\n\005WOMAN\020\001B&\n\033com.wjyb" +
      "xx.generatedmessageB\005PEnumH\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}