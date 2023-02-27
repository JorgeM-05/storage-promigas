package com.promigas_storage.repository.FiguresOperating;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;
import com.promigas_storage.repository.AbstractRepositoryDatabase;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DistributionRepositorylmp extends AbstractRepositoryDatabase implements DistributionRepository {

    private static String QUERY = "";

    private static String INSERT = "insert into dbo.co_distribution values(?,?,?,?,?,?)";
    private static String DELETE = "delete from dbo.co_distribution where id_opportunity = ?";

    @Override
    public List<Integer> findByDistribution(int idOportunity, ConnectionInfo connectionInfo) {
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
    public boolean insertDistribution(int idOpportunity, DistributionEntity distributionEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1, idOpportunity);
            con.setString(2, distributionEntity.getVolumeDistribution());
            con.setDouble(3, distributionEntity.getDistriPeriod());
            con.setDouble(4, distributionEntity.getDistributionKms());
            con.setDouble(3, distributionEntity.getDistributionUsers());
            con.setDouble(4, distributionEntity.getDistributionPenetration());

            int affectedRows =con.executeUpdate();
            if(affectedRows!=0)
                return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public boolean deleteDistribution(int idOpportunity, ConnectionInfo connectionInfo) {
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
        return false;
    }

    public List<Integer> getID(int idOportunity) throws SQLException {
        List<Integer> id= Collections.singletonList(0);
        QUERY = "select * from dbo.co_distribution where id_opportunity = "+idOportunity;
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            id.add(rs.getInt("unique_id"));
        }
        return id;
    }
}
