package com.promigas_storage.repository;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.CountryEntity;
import com.promigas_storage.repository.FiguresOperating.EnergySolutionlmp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class CountryRepositoryImp extends AbstractRepositoryDatabase implements CountryRepository{
    Logger logger = Logger.getLogger(CountryRepositoryImp.class.getName());

    private static String QUERY_COUNTRIES = "";

    @Override
    public Integer findByCountry(String country, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        int id= 0;
        logger.info("buscando en BD country :: "+ country);
        try {

            QUERY_COUNTRIES = "select * from dbo.country where name_country like '%"+country+"%'";
            PreparedStatement con = connection.prepareStatement(QUERY_COUNTRIES);
            ResultSet rs = con.executeQuery();

            while (rs.next()){
                if(rs.getString("name_country").toUpperCase().equalsIgnoreCase(country.toUpperCase())){
                    id=rs.getInt("unique_id");
                    logger.info("pais encotrando con el id :: "+id);
                }
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public boolean insertCountry(CountryEntity countryEntity, ConnectionInfo connectionInfo) {
        return false;
    }

}
