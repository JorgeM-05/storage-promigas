package com.promigas_storage.Service;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.DTO.enums.ConstantsEnum;
import com.promigas_storage.entity.SecretAdapter;
import com.promigas_storage.entity.StorageEntity;
import com.promigas_storage.repository.*;

import java.io.InputStream;
import java.util.List;

public class UploadDataService extends AbstractRepositoryDatabase {

    private CountryRepository repositoryCountry = new CountryRepositoryImp();
    private SectorRepository repositorySector = new SectorRepositoryImp();
    private TypeContractRepository repositoryContract = new TypeContractRepositoryImp();
    private OpportunitiesRepository repositoryOpportunity = new OpportunityRepositoryImp();
    private UploadFinancialService financialService;

//    // inyeccion interfaz
//    private IUploadService UIUploadService;
//    // nueva clase para utilizar la interfaz
//    public List<StorageEntity> reader(InputStream document){
//        List<StorageEntity> list = UIUploadService.getCustomersDataFromExcel(document);
//        return list;
//    }
    public void DataService(List<StorageEntity> customersDataFromExcel){
        SecretPort secretPort = new SecretAdapter();
        ConnectionInfo connectionInfo = secretPort.querySecretConnection(ConstantsEnum.SECRET_SQL_SERVER.getValue());

        System.out.println("DATA ::: \n "+customersDataFromExcel);

        for (StorageEntity data: customersDataFromExcel){
            System.out.println("DATA ::: \n "+data.getCountryeEntity());
//            CountryEntity(unique_id=0, nameContry=Colombia, urlFlags=null, cod_country=null)
            int idCountry = getCountry(data.getCountryeEntity().getNameContry(),connectionInfo);

            int idSector = getSector(data.getSectorEntity().getTypeSector(),connectionInfo);
            if(idSector==0){
                idSector = repositorySector.insertByType(data.getSectorEntity().getTypeSector(),connectionInfo);
            }
            System.out.println("idSector "+idSector);

            int idTypeContract = getTypeContract(data.getTypeContractEntity().getTypeContract(),connectionInfo);
            if(idTypeContract==0){
                idTypeContract = repositoryContract.insertByContract(data.getTypeContractEntity().getTypeContract(),
                        connectionInfo);
            }
            System.out.println("id contratr"+idTypeContract);

            int idOpportunity = getOpportunity(data.getOpportunityEntity().getProjecTitle(),connectionInfo);
            System.out.println("id opp"+idOpportunity);
            if(idOpportunity==0){
                idOpportunity = repositoryOpportunity.insertByOpportunitiy(idSector,idCountry,idTypeContract,
                        data.getOpportunityEntity(),connectionInfo);
                financialService.setDataFinancial(idOpportunity,data);
            }
            else {
                repositoryOpportunity.updateOpportunity(idOpportunity,idSector,idCountry,idTypeContract,data.getOpportunityEntity(),connectionInfo);
            }
        }
    }
    public int getCountry(String country, ConnectionInfo connectionInfo){
       return  repositoryCountry.findByCountry(country,connectionInfo);
    }
    public int getSector(String country, ConnectionInfo connectionInfo){
        return  repositorySector.findBySector(country,connectionInfo);
    }
    public int getTypeContract(String country, ConnectionInfo connectionInfo){
        return  repositoryContract.findByContract(country,connectionInfo);
    }

    public int getOpportunity(String opportunity,ConnectionInfo connectionInfo){
        return repositoryOpportunity.findByOpportunity(opportunity,connectionInfo);
    }



}
