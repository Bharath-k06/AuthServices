package com.entity;

import java.time.LocalDateTime;

public class AuthAuditEvent {

    private String userId;
    private LocalDateTime timestamp;
    private String eventType; // LOGIN_SUCCESS, LOGIN_FAILURE, PASSWORD_RESET, etc.
    private String ipAddress;
    private String details; // Optional, for additional information

    // Constructors, getters, setters, toString()
    public AuthAuditEvent(){}
    public AuthAuditEvent(String userId, LocalDateTime timestamp, String eventType, String ipAddress, String details) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.ipAddress = ipAddress;
        this.details = details;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "AuthAuditEvent{" +
                "userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
