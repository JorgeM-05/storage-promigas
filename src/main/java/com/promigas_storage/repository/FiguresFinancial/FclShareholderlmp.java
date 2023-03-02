package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.FclShareholderEntity;
import com.promigas_storage.repository.AbstractRepositoryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FclShareholderlmp extends AbstractRepositoryDatabase implements FclShareholderRepository{

    private static String QUERY = "";

    private static String INSERT = "insert into dbo.cf_fcl_shareholder values(?,?,?,?)";
    private static String DELETE = "delete from dbo.cf_fcl_shareholder where id_opportunity = ?";

    @Override
    public List<Integer> findByFclShareholder(int idOportunity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        List<Integer> id;
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
    public boolean insertFclShareholder(int idOpportunity, FclShareholderEntity fclShareholderEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1,idOpportunity);
            con.setString(2,fclShareholderEntity.getYear());
            con.setDouble(3,fclShareholderEntity.getShareholderUsd());
            con.setDouble(4,fclShareholderEntity.getShareholderCop());

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
    public boolean deleteFclShareholder(int idOpportunity, ConnectionInfo connectionInfo) {
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
        List<Integer> id= new ArrayList<>();
        QUERY = "select * from dbo.cf_fcl_shareholder where id_opportunity = "+idOportunity;
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            id.add(rs.getInt("unique_id"));
        }
        return id;
    }

}
