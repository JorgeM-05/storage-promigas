package com.promigas_storage.DTO;

import lombok.Data;

@Data
public class ConnectionInfo {
    private String user;
    private String pwd;
    private String host;
    private String schema;
    private String port;
    private String connectionPattern;
    private int connectionTimeout;
}
