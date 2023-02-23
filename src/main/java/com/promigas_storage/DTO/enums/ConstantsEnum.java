package com.promigas_storage.DTO.enums;

public enum ConstantsEnum {
    SECRET_SQL_SERVER ("connectionSQL");

    private ConstantsEnum(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
