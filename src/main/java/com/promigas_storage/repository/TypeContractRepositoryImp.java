package com.promigas_storage.repository;

import com.promigas_storage.DTO.ConnectionInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class TypeContractRepositoryImp extends AbstractRepositoryDatabase implements  TypeContractRepository{

    Logger logger = Logger.getLogger(TypeContractRepositoryImp.class.getName());
    private static String QUERY = "";
    private static String INSERT = "insert into dbo.type_contract(type_contract) values(?)";

    @Override
    public Integer findByContract(String nameContract, ConnectionInfo connectionInfo) {
        logger.info("buscando type contract ::"+ nameContract);
        getConnectionSQLServer(connectionInfo);
        int id= 0;
        try {
            id = getID(nameContract);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public Integer insertByContract(String nameContract, ConnectionInfo connectionInfo) {
        logger.info("insertando en DB type contract ::"+ nameContract);

        getConnectionSQLServer(connectionInfo);
        int id= 0;
        try {
            PreparedStatement con = connection.prepareStatement(INSERT);
            con.setString(1,nameContract);
            int affectedRows =con.executeUpdate();
            if(affectedRows!=0)
                id= getID(nameContract);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return id;
    }
    public int getID(String nameContract) throws SQLException {
        int id= 0;

        QUERY ="select * from dbo.type_contract where type_contract like '%"+nameContract+"%'";
        PreparedStatement con = connection.prepareStatement(QUERY);
        ResultSet rs = con.executeQuery();

        while (rs.next()){
            if(rs.getString("type_contract").toUpperCase().equalsIgnoreCase(nameContract.toUpperCase())){
                id=rs.getInt("unique_id");
            }
        }
        return id;
    }
}
