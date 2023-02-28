package com.promigas_storage.repository;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.OpportunitiesEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpportunityRepositoryImp extends AbstractRepositoryDatabase implements OpportunitiesRepository{

    private static String QUERY = "";

    private static String INSERT = "insert into dbo.opportunities values(?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)";
    private static String UPDATE = "update dbo.opportunities\n" +
            "set id_country=?,id_sector=?,id_type_contract=?,type_project=?,project_title=?," +
            "date_update=?,coordinates=?,opportunity_descrip=?,\n" +
            "\thorizon_operation=?,pre_operation_horizon=?,POC=?,TRM_base_capex=?," +
            "TRM_fin_capex=?,prop_capex_usd=?,prop_capex_cop=?,financial_asset=?,city=?\n" +
            "\t where unique_id=?;";

    @Override
    public Integer findByOpportunity(String opportunity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        int id= 0;
        try {
            id= getID(opportunity);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public Integer insertByOpportunitiy(int idSector, int idCountry, int idcontract,
                                        OpportunitiesEntity opportunitiesEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        int id= 0;
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setInt(1,idCountry);
            con.setInt(2,idSector);
            con.setInt(3,idcontract);
            con.setBoolean(4,true);// aqui es un string en BD real
            con.setString(5,opportunitiesEntity.getProjecTitle());
            con.setDate(6, java.sql.Date.valueOf("2023-12-08"));
            con.setString(7,opportunitiesEntity.getCoordinates());
            con.setString(8,opportunitiesEntity.getDescrip());
            con.setInt(9,opportunitiesEntity.getHorizonOpe());
            con.setInt(10,opportunitiesEntity.getHorizonPre());
            con.setString(11,opportunitiesEntity.getPoc());
            con.setDouble(12,opportunitiesEntity.getTrmBase());
            con.setDouble(13,opportunitiesEntity.getTrmFin());
            con.setString(14,opportunitiesEntity.getPropCapexUsd());
            con.setString(15,opportunitiesEntity.getPropCapexCop());
            con.setBoolean(16,true);// aqui validar que llega
            con.setString(17, opportunitiesEntity.getCity());

            int affectedRows =con.executeUpdate();
            if(affectedRows!=0) {
                id = getID(opportunitiesEntity.getProjecTitle());
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return id;
    }
    @Override
    public boolean updateOpportunity(int id,int idSector, int idCountry, int idcontract, OpportunitiesEntity opportunitiesEntity, ConnectionInfo connectionInfo) {
        getConnectionSQLServer(connectionInfo);
        boolean result=false;
        try {
            PreparedStatement con = connection.prepareStatement(UPDATE);
            System.out.println("bd 1");
            con.setInt(1,idCountry);
            con.setInt(2,idSector);
            con.setInt(3,idcontract);
            con.setString(4,opportunitiesEntity.getTypeProject());
            con.setString(5,opportunitiesEntity.getProjecTitle());
//            System.out.println(">>>>>>>>>>> "+java.sql.Date.valueOf("2022-02-03"));
//            System.out.println(">>>>>>>>>>> op:date "+opportunitiesEntity.getDate());
//            System.out.println(">>>>>>>>>>> 1"+java.sql.Date.valueOf(opportunitiesEntity.getDate()));
//            con.setDate(6, java.sql.Date.valueOf(opportunitiesEntity.getDate()));
            con.setDate(6, java.sql.Date.valueOf("2024-02-24"));
            con.setString(7,opportunitiesEntity.getCoordinates());
            con.setString(8,opportunitiesEntity.getDescrip());
            con.setInt(9,opportunitiesEntity.getHorizonOpe());
            con.setInt(10,opportunitiesEntity.getHorizonPre());
            con.setString(11,opportunitiesEntity.getPoc());
            con.setDouble(12,opportunitiesEntity.getTrmBase());
            con.setDouble(13,opportunitiesEntity.getTrmFin());
            con.setString(14,opportunitiesEntity.getPropCapexUsd());
            con.setString(15,opportunitiesEntity.getPropCapexCop());
            con.setBoolean(16, opportunitiesEntity.isFinancialAsset());
            con.setString(17, opportunitiesEntity.getCity());
            con.setInt(18,id);


            int affectedRows =con.executeUpdate();
            if(affectedRows!=0)
                result = true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }

        return result;
    }

    public int getID(String opportunity) throws SQLException {
        int id= 0;

        QUERY = "select * from dbo.opportunities where project_title like '%"+opportunity+"%'";
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            if(rs.getString("project_title").toUpperCase().equalsIgnoreCase(opportunity.toUpperCase())){
                id=rs.getInt("unique_id");
            }
        }
        return id;
    }
}
