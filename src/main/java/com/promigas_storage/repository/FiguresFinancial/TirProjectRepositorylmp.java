package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.TirProjectEntity;
import com.promigas_storage.repository.AbstractRepositoryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TirProjectRepositorylmp extends AbstractRepositoryDatabase implements TirProjectRepository{

    private static String QUERY = "";

    private static String INSERT = "insert into dbo.cf_tir_project values(?,?,?,?)";
    private static String DELETE = "delete from dbo.cf_tir_project where id_opportunity = ?";

    @Override
    public List<Integer> findByTirProject(int idOportunity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        List<Integer> id= Collections.singletonList(0);
        try {
            id= getID(idOportunity);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;    }

    @Override
    public boolean insertTirProject(int idOpportunity, TirProjectEntity tirProjectEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1,idOpportunity);
            con.setString(2,tirProjectEntity.getYear());
//            con.setDouble(3,tirProjectEntity.getTirprojectunit());
//            con.setDouble(4,tirProjectEntity.getTirprojfigure());

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
    public boolean deleteTirProject(int idOpportunity, ConnectionInfo connectionInfo) {
        return false;
    }

    public List<Integer> getID(int idOportunity) throws SQLException {
        List<Integer> id= Collections.singletonList(0);
        QUERY = "select * from dbo.cf_tir_project where id_opportunity = "+idOportunity;
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            id.add(rs.getInt("unique_id"));
        }
        return id;
    }

}
