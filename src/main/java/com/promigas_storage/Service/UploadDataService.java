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
    private UploadFinancialService financialService = new UploadFinancialService();
    private UploadOperatingService operatingService = new UploadOperatingService();

    public void DataService(List<StorageEntity> customersDataFromExcel){
        SecretPort secretPort = new SecretAdapter();
        ConnectionInfo connectionInfo = secretPort.querySecretConnection(ConstantsEnum.SECRET_SQL_SERVER.getValue());

        for (StorageEntity data: customersDataFromExcel){
            int idCountry = 0;
            if(!data.getCountryEntity().getNameContry().equals("0.0")){
                idCountry = getCountry(data.getCountryEntity().getNameContry(),connectionInfo);
            }
            int idSector =0;
            if(data.getSectorEntity().getTypeSector()!=null) {
                idSector = getSector(data.getSectorEntity().getTypeSector(), connectionInfo);
                if (idSector == 0) {
                    idSector = repositorySector.insertByType(data.getSectorEntity().getTypeSector(), connectionInfo);
                }
            }
            int idTypeContract =0;
            if(data.getTypeContractEntity().getTypeContract()!=null) {
                idTypeContract = getTypeContract(data.getTypeContractEntity().getTypeContract(), connectionInfo);
                if (idTypeContract == 0) {
                    idTypeContract = repositoryContract.insertByContract(data.getTypeContractEntity().getTypeContract(),
                            connectionInfo);
                }
            }

            int idOpportunity = getOpportunity(data.getOpportunityEntity().getProjecTitle(),connectionInfo);
            if(idOpportunity==0 ){
                if(idSector>0 && idCountry>0 && idTypeContract>0) {
                    idOpportunity = repositoryOpportunity.insertByOpportunitiy(idSector, idCountry, idTypeContract,
                            data.getOpportunityEntity(), connectionInfo);
                    if(idOpportunity>0) {
                        financialService.setDataFinancial(idOpportunity, data);
                        operatingService.setDataFinancial(idOpportunity, data);
                    }
                }
            }
            else {
                repositoryOpportunity.updateOpportunity(idOpportunity,idSector,idCountry,idTypeContract,data.getOpportunityEntity(),connectionInfo);
                financialService.setDataFinancial(idOpportunity, data);
                operatingService.setDataFinancial(idOpportunity,data);
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
