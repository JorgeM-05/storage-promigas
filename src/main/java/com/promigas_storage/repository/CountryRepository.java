package com.promigas_storage.repository;


import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.CountryEntity;

public interface CountryRepository {

    public Integer findByCountry(String country, ConnectionInfo connectionInfo);
    public Integer insertCountry(CountryEntity countryEntity, ConnectionInfo connectionInfo);
}
