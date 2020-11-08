// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: owned_Items_pb.proto

package com.ch8n.thatsmine;

/**
 * Protobuf type {@code ProtoOwnedItems}
 */
public  final class ProtoOwnedItems extends
    com.google.protobuf.GeneratedMessageLite<
        ProtoOwnedItems, ProtoOwnedItems.Builder> implements
    // @@protoc_insertion_point(message_implements:ProtoOwnedItems)
    ProtoOwnedItemsOrBuilder {
  private ProtoOwnedItems() {
    offlineBufferItem_ = emptyProtobufList();
  }
  private int bitField0_;
  public static final int LASTVIEWITEM_FIELD_NUMBER = 1;
  private com.ch8n.thatsmine.ProtoOwnedItem lastViewItem_;
  /**
   * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
   */
  public boolean hasLastViewItem() {
    return lastViewItem_ != null;
  }
  /**
   * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
   */
  public com.ch8n.thatsmine.ProtoOwnedItem getLastViewItem() {
    return lastViewItem_ == null ? com.ch8n.thatsmine.ProtoOwnedItem.getDefaultInstance() : lastViewItem_;
  }
  /**
   * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
   */
  private void setLastViewItem(com.ch8n.thatsmine.ProtoOwnedItem value) {
    if (value == null) {
      throw new NullPointerException();
    }
    lastViewItem_ = value;
    
    }
  /**
   * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
   */
  private void setLastViewItem(
      com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
    lastViewItem_ = builderForValue.build();
    
  }
  /**
   * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
   */
  private void mergeLastViewItem(com.ch8n.thatsmine.ProtoOwnedItem value) {
    if (lastViewItem_ != null &&
        lastViewItem_ != com.ch8n.thatsmine.ProtoOwnedItem.getDefaultInstance()) {
      lastViewItem_ =
        com.ch8n.thatsmine.ProtoOwnedItem.newBuilder(lastViewItem_).mergeFrom(value).buildPartial();
    } else {
      lastViewItem_ = value;
    }
    
  }
  /**
   * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
   */
  private void clearLastViewItem() {  lastViewItem_ = null;
    
  }

  public static final int OFFLINEBUFFERITEM_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.ProtobufList<com.ch8n.thatsmine.ProtoOwnedItem> offlineBufferItem_;
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  public java.util.List<com.ch8n.thatsmine.ProtoOwnedItem> getOfflineBufferItemList() {
    return offlineBufferItem_;
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  public java.util.List<? extends com.ch8n.thatsmine.ProtoOwnedItemOrBuilder> 
      getOfflineBufferItemOrBuilderList() {
    return offlineBufferItem_;
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  public int getOfflineBufferItemCount() {
    return offlineBufferItem_.size();
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  public com.ch8n.thatsmine.ProtoOwnedItem getOfflineBufferItem(int index) {
    return offlineBufferItem_.get(index);
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  public com.ch8n.thatsmine.ProtoOwnedItemOrBuilder getOfflineBufferItemOrBuilder(
      int index) {
    return offlineBufferItem_.get(index);
  }
  private void ensureOfflineBufferItemIsMutable() {
    if (!offlineBufferItem_.isModifiable()) {
      offlineBufferItem_ =
          com.google.protobuf.GeneratedMessageLite.mutableCopy(offlineBufferItem_);
     }
  }

  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void setOfflineBufferItem(
      int index, com.ch8n.thatsmine.ProtoOwnedItem value) {
    if (value == null) {
      throw new NullPointerException();
    }
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.set(index, value);
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void setOfflineBufferItem(
      int index, com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.set(index, builderForValue.build());
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void addOfflineBufferItem(com.ch8n.thatsmine.ProtoOwnedItem value) {
    if (value == null) {
      throw new NullPointerException();
    }
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.add(value);
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void addOfflineBufferItem(
      int index, com.ch8n.thatsmine.ProtoOwnedItem value) {
    if (value == null) {
      throw new NullPointerException();
    }
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.add(index, value);
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void addOfflineBufferItem(
      com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.add(builderForValue.build());
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void addOfflineBufferItem(
      int index, com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.add(index, builderForValue.build());
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void addAllOfflineBufferItem(
      java.lang.Iterable<? extends com.ch8n.thatsmine.ProtoOwnedItem> values) {
    ensureOfflineBufferItemIsMutable();
    com.google.protobuf.AbstractMessageLite.addAll(
        values, offlineBufferItem_);
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void clearOfflineBufferItem() {
    offlineBufferItem_ = emptyProtobufList();
  }
  /**
   * <pre>
   * repeated =&gt; list
   * </pre>
   *
   * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
   */
  private void removeOfflineBufferItem(int index) {
    ensureOfflineBufferItemIsMutable();
    offlineBufferItem_.remove(index);
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (lastViewItem_ != null) {
      output.writeMessage(1, getLastViewItem());
    }
    for (int i = 0; i < offlineBufferItem_.size(); i++) {
      output.writeMessage(2, offlineBufferItem_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (lastViewItem_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getLastViewItem());
    }
    for (int i = 0; i < offlineBufferItem_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, offlineBufferItem_.get(i));
    }
    memoizedSerializedSize = size;
    return size;
  }

  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.ch8n.thatsmine.ProtoOwnedItems parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.ch8n.thatsmine.ProtoOwnedItems prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * Protobuf type {@code ProtoOwnedItems}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.ch8n.thatsmine.ProtoOwnedItems, Builder> implements
      // @@protoc_insertion_point(builder_implements:ProtoOwnedItems)
      com.ch8n.thatsmine.ProtoOwnedItemsOrBuilder {
    // Construct using com.ch8n.thatsmine.ProtoOwnedItems.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
     */
    public boolean hasLastViewItem() {
      return instance.hasLastViewItem();
    }
    /**
     * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
     */
    public com.ch8n.thatsmine.ProtoOwnedItem getLastViewItem() {
      return instance.getLastViewItem();
    }
    /**
     * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
     */
    public Builder setLastViewItem(com.ch8n.thatsmine.ProtoOwnedItem value) {
      copyOnWrite();
      instance.setLastViewItem(value);
      return this;
      }
    /**
     * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
     */
    public Builder setLastViewItem(
        com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
      copyOnWrite();
      instance.setLastViewItem(builderForValue);
      return this;
    }
    /**
     * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
     */
    public Builder mergeLastViewItem(com.ch8n.thatsmine.ProtoOwnedItem value) {
      copyOnWrite();
      instance.mergeLastViewItem(value);
      return this;
    }
    /**
     * <code>optional .ProtoOwnedItem lastViewItem = 1;</code>
     */
    public Builder clearLastViewItem() {  copyOnWrite();
      instance.clearLastViewItem();
      return this;
    }

    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public java.util.List<com.ch8n.thatsmine.ProtoOwnedItem> getOfflineBufferItemList() {
      return java.util.Collections.unmodifiableList(
          instance.getOfflineBufferItemList());
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public int getOfflineBufferItemCount() {
      return instance.getOfflineBufferItemCount();
    }/**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public com.ch8n.thatsmine.ProtoOwnedItem getOfflineBufferItem(int index) {
      return instance.getOfflineBufferItem(index);
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder setOfflineBufferItem(
        int index, com.ch8n.thatsmine.ProtoOwnedItem value) {
      copyOnWrite();
      instance.setOfflineBufferItem(index, value);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder setOfflineBufferItem(
        int index, com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
      copyOnWrite();
      instance.setOfflineBufferItem(index, builderForValue);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder addOfflineBufferItem(com.ch8n.thatsmine.ProtoOwnedItem value) {
      copyOnWrite();
      instance.addOfflineBufferItem(value);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder addOfflineBufferItem(
        int index, com.ch8n.thatsmine.ProtoOwnedItem value) {
      copyOnWrite();
      instance.addOfflineBufferItem(index, value);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder addOfflineBufferItem(
        com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
      copyOnWrite();
      instance.addOfflineBufferItem(builderForValue);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder addOfflineBufferItem(
        int index, com.ch8n.thatsmine.ProtoOwnedItem.Builder builderForValue) {
      copyOnWrite();
      instance.addOfflineBufferItem(index, builderForValue);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder addAllOfflineBufferItem(
        java.lang.Iterable<? extends com.ch8n.thatsmine.ProtoOwnedItem> values) {
      copyOnWrite();
      instance.addAllOfflineBufferItem(values);
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder clearOfflineBufferItem() {
      copyOnWrite();
      instance.clearOfflineBufferItem();
      return this;
    }
    /**
     * <pre>
     * repeated =&gt; list
     * </pre>
     *
     * <code>repeated .ProtoOwnedItem offlineBufferItem = 2;</code>
     */
    public Builder removeOfflineBufferItem(int index) {
      copyOnWrite();
      instance.removeOfflineBufferItem(index);
      return this;
    }

    // @@protoc_insertion_point(builder_scope:ProtoOwnedItems)
  }
  protected final Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.ch8n.thatsmine.ProtoOwnedItems();
      }
      case IS_INITIALIZED: {
        return DEFAULT_INSTANCE;
      }
      case MAKE_IMMUTABLE: {
        offlineBufferItem_.makeImmutable();
        return null;
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case VISIT: {
        Visitor visitor = (Visitor) arg0;
        com.ch8n.thatsmine.ProtoOwnedItems other = (com.ch8n.thatsmine.ProtoOwnedItems) arg1;
        lastViewItem_ = visitor.visitMessage(lastViewItem_, other.lastViewItem_);
        offlineBufferItem_= visitor.visitList(offlineBufferItem_, other.offlineBufferItem_);
        if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
            .INSTANCE) {
          bitField0_ |= other.bitField0_;
        }
        return this;
      }
      case MERGE_FROM_STREAM: {
        com.google.protobuf.CodedInputStream input =
            (com.google.protobuf.CodedInputStream) arg0;
        com.google.protobuf.ExtensionRegistryLite extensionRegistry =
            (com.google.protobuf.ExtensionRegistryLite) arg1;
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!input.skipField(tag)) {
                  done = true;
                }
                break;
              }
              case 10: {
                com.ch8n.thatsmine.ProtoOwnedItem.Builder subBuilder = null;
                if (lastViewItem_ != null) {
                  subBuilder = lastViewItem_.toBuilder();
                }
                lastViewItem_ = input.readMessage(com.ch8n.thatsmine.ProtoOwnedItem.parser(), extensionRegistry);
                if (subBuilder != null) {
                  subBuilder.mergeFrom(lastViewItem_);
                  lastViewItem_ = subBuilder.buildPartial();
                }

                break;
              }
              case 18: {
                if (!offlineBufferItem_.isModifiable()) {
                  offlineBufferItem_ =
                      com.google.protobuf.GeneratedMessageLite.mutableCopy(offlineBufferItem_);
                }
                offlineBufferItem_.add(
                    input.readMessage(com.ch8n.thatsmine.ProtoOwnedItem.parser(), extensionRegistry));
                break;
              }
            }
          }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw new RuntimeException(e.setUnfinishedMessage(this));
        } catch (java.io.IOException e) {
          throw new RuntimeException(
              new com.google.protobuf.InvalidProtocolBufferException(
                  e.getMessage()).setUnfinishedMessage(this));
        } finally {
        }
      }
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        if (PARSER == null) {    synchronized (com.ch8n.thatsmine.ProtoOwnedItems.class) {
            if (PARSER == null) {
              PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            }
          }
        }
        return PARSER;
      }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:ProtoOwnedItems)
  private static final com.ch8n.thatsmine.ProtoOwnedItems DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ProtoOwnedItems();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static com.ch8n.thatsmine.ProtoOwnedItems getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<ProtoOwnedItems> PARSER;

  public static com.google.protobuf.Parser<ProtoOwnedItems> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

