package com.promigas_storage.repository;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.CountryEntity;
import com.promigas_storage.repository.FiguresOperating.EnergySolutionlmp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CountryRepositoryImp extends AbstractRepositoryDatabase implements CountryRepository{
    Logger logger = Logger.getLogger(CountryRepositoryImp.class.getName());

    private static String QUERY_COUNTRIES = "";
    private static String INSERT = "insert into dbo.country(name_country,url_flags,cod_country) values(?,?,?)";


    @Override
    public Integer findByCountry(String country, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        int id= 0;
        logger.info("buscando en BD country :: "+ country);
        try {
            id= getId(country);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public Integer insertCountry(CountryEntity countryEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        int id= 0;
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setString(1, countryEntity.getNameContry());
            con.setString(2, countryEntity.getUrlFlags());
            con.setString(3, countryEntity.getCod_country());
            int affectedRows =con.executeUpdate();
            if(affectedRows!=0)
                id= getId(countryEntity.getNameContry());
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return id;
    }
    public int getId(String country) throws SQLException {
        int id= 0;

        QUERY_COUNTRIES = "select * from dbo.country where name_country like '%"+country+"%'";
        PreparedStatement con = connection.prepareStatement(QUERY_COUNTRIES);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            if(rs.getString("name_country").toUpperCase().equalsIgnoreCase(country.toUpperCase())){
                id=rs.getInt("unique_id");
                logger.info("pais encotrando con el id :: "+id);
            }
        }
        return id;
    }
}
