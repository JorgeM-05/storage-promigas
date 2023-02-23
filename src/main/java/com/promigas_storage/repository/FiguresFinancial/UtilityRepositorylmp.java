package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.UtilityEntity;
import com.promigas_storage.repository.AbstractRepositoryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UtilityRepositorylmp extends AbstractRepositoryDatabase implements UtilityRepository{


    private static String QUERY = "";

    private static String INSERT = "insert into dbo.cf_utility_net values(?,?,?,?)";
    private static String DELETE = "delete from dbo.cf_utility_net where id_opportunity = ?";
    @Override
    public List<Integer> findByUtility(int idOportunity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        List<Integer> id= Collections.singletonList(0);
        try {
            id= getID(idOportunity);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public boolean insertUtility(int idOpportunity, UtilityEntity utilityEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1,idOpportunity);
            con.setString(2,utilityEntity.getYear());
            con.setDouble(3,utilityEntity.getUtilityUsd());
            con.setDouble(4,utilityEntity.getUtilityCop());

            int affectedRows =con.executeUpdate();
            if(affectedRows!=0)
                return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return false;    }

    @Override
    public boolean deleteUtility(int idOpportunity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        try {
            PreparedStatement con = connection.prepareStatement(DELETE);
            con.setInt(1,idOpportunity);

            int affectedRows =con.executeUpdate();
            if(affectedRows!=0)
                return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return false;    }

    public List<Integer> getID(int idOportunity) throws SQLException {
        List<Integer> id= Collections.singletonList(0);
        QUERY = "select * from dbo.cf_utility_net where id_opportunity = "+idOportunity;
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            id.add(rs.getInt("unique_id"));
        }
        return id;
    }

}
