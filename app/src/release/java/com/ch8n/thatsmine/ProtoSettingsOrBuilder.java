// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: settings_pb.proto

package com.ch8n.thatsmine;

public interface ProtoSettingsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ProtoSettings)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>optional bool notification_enabled = 1;</code>
   */
  boolean getNotificationEnabled();

  /**
   * <code>optional bool loggedIn = 2;</code>
   */
  boolean getLoggedIn();

  /**
   * <code>optional bool guestMode = 3;</code>
   */
  boolean getGuestMode();

  /**
   * <code>optional .ProtoSettings.ActiveUser activeUser = 4;</code>
   */
  boolean hasActiveUser();
  /**
   * <code>optional .ProtoSettings.ActiveUser activeUser = 4;</code>
   */
  com.ch8n.thatsmine.ProtoSettings.ActiveUser getActiveUser();

  /**
   * <code>optional .ProtoSettings.Theme currentTheme = 5;</code>
   */
  int getCurrentThemeValue();
  /**
   * <code>optional .ProtoSettings.Theme currentTheme = 5;</code>
   */
  com.ch8n.thatsmine.ProtoSettings.Theme getCurrentTheme();
}
