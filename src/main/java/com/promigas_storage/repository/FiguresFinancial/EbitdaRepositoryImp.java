package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.EbitdaEntity;
import com.promigas_storage.repository.AbstractRepositoryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EbitdaRepositoryImp extends AbstractRepositoryDatabase implements EbitdaRepository{

    private static String QUERY = "";
    private static String INSERT = "insert into dbo.cf_ebitda values(?,?,?,?)";
    private static String DELETE = "delete from dbo.cf_ebitda where id_opportunity = ?";

    @Override
    public List<Integer> findByEbitda(int idOportunity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        List<Integer> id;
        try {
            System.out.println("consultando EBITDA");
            id= getID(idOportunity);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public boolean insertEbida(int idOpportunity, EbitdaEntity ebitdaEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);

        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1,idOpportunity);
            con.setString(2,ebitdaEntity.getYear());
            con.setDouble(3,ebitdaEntity.getValueUsd());
            con.setDouble(4,ebitdaEntity.getValueCop());

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
    public boolean deleteEbitda(int idOpportunity, ConnectionInfo connectionInfo) {
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
        QUERY = "select * from dbo.cf_ebitda where id_opportunity = "+idOportunity;
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            id.add(rs.getInt("unique_id"));
        }

        return id;
    }
}
