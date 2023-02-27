package com.promigas_storage.repository.FiguresOperating;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;
import com.promigas_storage.repository.AbstractRepositoryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class TransportRepositorylmp extends AbstractRepositoryDatabase implements TransportRepository {

    private static String QUERY = "";

    private static String INSERT = "insert into dbo.co_transport values(?,?,?,?,?)";
    private static String DELETE = "delete from dbo.co_transport  where id_opportunity = ?";


    @Override
    public List<Integer> findByTransport(int idOportunity, ConnectionInfo connectionInfo) {
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
    public boolean insertTransport(int idOpportunity, TransportEntity transportEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1,idOpportunity);
            con.setString(2,transportEntity.getTransportCapacityMax());
            con.setDouble(3,transportEntity.getTransportCapacityHired());
            con.setDouble(4,transportEntity.getTransportVolumen());
            con.setDouble(4,transportEntity.getTransportLengthpipeline());

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
    public boolean deleteTransport(int idOpportunity, ConnectionInfo connectionInfo) {
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
        QUERY = "select * from dbo.co_transport where id_opportunity = "+idOportunity;
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            id.add(rs.getInt("unique_id"));
        }
        return id;
    }
}
