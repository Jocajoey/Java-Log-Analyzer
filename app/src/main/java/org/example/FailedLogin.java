package org.example;

//upon failed login attempts, record the username that was tried and the IP it came from
public record FailedLogin(String user, String ip) {}