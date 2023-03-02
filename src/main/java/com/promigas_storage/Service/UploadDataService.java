package com.promigas_storage.Service;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.DTO.enums.ConstantsEnum;
import com.promigas_storage.entity.SecretAdapter;
import com.promigas_storage.entity.StorageEntity;
import com.promigas_storage.repository.*;
import com.promigas_storage.repository.FiguresOperating.EnergySolutionlmp;

import java.util.List;
import java.util.logging.Logger;

public class UploadDataService   {
    Logger logger = Logger.getLogger(EnergySolutionlmp.class.getName());

    private CountryRepository repositoryCountry = new CountryRepositoryImp();
    private SectorRepository repositorySector = new SectorRepositoryImp();
    private TypeContractRepository repositoryContract = new TypeContractRepositoryImp();
    private OpportunitiesRepository repositoryOpportunity = new OpportunityRepositoryImp();
    private UploadFinancialService financialService = new UploadFinancialService();
    private UploadOperatingService operatingService = new UploadOperatingService();

    public String DataService(List<StorageEntity> customersDataFromExcel){
        logger.info("inicio de llenado a la base datos :: "+customersDataFromExcel);
        SecretPort secretPort = new SecretAdapter();
        ConnectionInfo connectionInfo = secretPort.querySecretConnection(ConstantsEnum.SECRET_SQL_SERVER.getValue());

        for (StorageEntity data: customersDataFromExcel){
            int idCountry = 0;
            if(!data.getCountryEntity().getNameContry().equals("0.0")){
                logger.info("consultando id de pais :: "+data.getCountryEntity().getNameContry());
                idCountry = getCountry(data.getCountryEntity().getNameContry(),connectionInfo);
                if(idCountry==0){
                    logger.info("insertando data pais :: "+data.getCountryEntity());
                    idCountry = repositoryCountry.insertCountry(data.getCountryEntity(),connectionInfo);
                }
                logger.info("id pais :: "+idCountry);
            }
            int idSector =0;
            if(data.getSectorEntity().getTypeSector()!=null) {
                logger.info("consultando id de sector :: "+data.getSectorEntity().getTypeSector());
                idSector = getSector(data.getSectorEntity().getTypeSector(), connectionInfo);
                if (idSector == 0) {
                    logger.info("insertando data sector :: "+data.getSectorEntity());
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

            /** en esta seccion se consulta la oportunidad y me retorna id*/
            int idOpportunity = getOpportunity(data.getOpportunityEntity().getProjecTitle(),connectionInfo);
            if(idOpportunity==0 ){
                if(idSector>0 && idCountry>0 && idTypeContract>0) {
                    idOpportunity = repositoryOpportunity.insertByOpportunitiy(idSector, idCountry, idTypeContract,
                            data.getOpportunityEntity(), connectionInfo);
                    logger.info("id oportunidad ::" +idOpportunity);
                    if(idOpportunity>0) {
                        //llenando data de cifras financiera y operativas
                        financialService.setDataFinancial(idOpportunity, data);
                        operatingService.setDataFinancial(idOpportunity, data);
                    }
                }
            }
            else {
                logger.info("id oportunidad ::" +idOpportunity);
                repositoryOpportunity.updateOpportunity(idOpportunity,idSector,idCountry,idTypeContract,data.getOpportunityEntity(),connectionInfo);
                financialService.setDataFinancial(idOpportunity, data);
                operatingService.setDataFinancial(idOpportunity,data);
            }
        }


        return null;
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
