package com.promigas_storage.repository;

import com.google.gson.Gson;
import com.promigas_storage.DTO.ConnectionInfo;

public class SecretRepositoryImpl implements SecretRepository{
    @Override
    public String querySecret(String secretName) {

        ConnectionInfo connection = new ConnectionInfo();
        if ("connectionSQL".equals(secretName)) {
            connection.setConnectionPattern("jdbc:sqlserver://{0}:{1};encrypt=true;databaseName={2};user={3};password={4};trustServerCertificate=true");
            connection.setHost("localhost");
            connection.setPort("1433");
            connection.setSchema("promigas_dpi");
            connection.setUser("sa");
            connection.setPwd("root");

        }

        return new Gson().toJson(connection);
    }
}
