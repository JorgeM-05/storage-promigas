package com.promigas_storage.Service;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.DTO.enums.ConstantsEnum;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.EbitdaEntity;
import com.promigas_storage.entity.SecretAdapter;
import com.promigas_storage.entity.StorageEntity;
import com.promigas_storage.repository.FiguresFinancial.*;
import com.promigas_storage.repository.SecretPort;

import java.util.List;

public class UploadFinancialService {
    private CapexRepository capexRepository = new CapexRepositoryImp();
    private EbitdaRepository ebitdaRepository = new EbitdaRepositoryImp();
    private DividensRepository dividensRepository = new DividensRepositorylmp();
    private FclRepository fclRepository = new FclRepositorylmp();
    private FclShareholderRepository fclShRepository = new FclShareholderlmp();
    private IncomeRepository incomeRepository = new IncomeRepositorylmp();
    private TarifRepository tarifRepository = new TarifRepositorylmp();
    private TirProjectRepository tirProjectRepository = new TirProjectRepositorylmp();
    private UtilityRepository utilityRepository = new UtilityRepositorylmp();
    
    public void setDataFinancial(int idOportunity, StorageEntity data){
        SecretPort secretPort = new SecretAdapter();
        ConnectionInfo connectionInfo = secretPort.querySecretConnection(ConstantsEnum.SECRET_SQL_SERVER.getValue());
        setCapex(idOportunity,connectionInfo,data.getCapexEntity());
        setEbitda(idOportunity,connectionInfo,data.getEbitdaEntity());
    }

    public void setCapex(int id, ConnectionInfo connectionInfo, CapexEntity capexEntity){
        List<Integer> ids = capexRepository.findByCapex(id,connectionInfo);
        if(ids.size()>0){
            if(capexRepository.deleteCapex(id,connectionInfo))
                capexRepository.insertCapex(id,capexEntity,connectionInfo);
        }
        else {
            capexRepository.insertCapex(id,capexEntity,connectionInfo);
        }
    }
    public void setEbitda(int id, ConnectionInfo connectionInfo, EbitdaEntity ebitdaEntity){
        List<Integer> ids = ebitdaRepository.findByEbitda(id,connectionInfo);
        if(ids.size()>0){
            if(ebitdaRepository.deleteEbitda(id,connectionInfo))
                ebitdaRepository.insertEbida(id,ebitdaEntity,connectionInfo);
        }
        else {
            ebitdaRepository.insertEbida(id,ebitdaEntity,connectionInfo);
        }
    }
}
