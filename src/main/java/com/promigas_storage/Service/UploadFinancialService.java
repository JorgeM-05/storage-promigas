package com.promigas_storage.Service;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.DTO.enums.ConstantsEnum;
import com.promigas_storage.entity.FiguresFinancial.*;
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
        setDividens(idOportunity,connectionInfo,data.getDividensEntity());
        setFcl(idOportunity,connectionInfo,data.getFclEntity());
        setFclSH(idOportunity,connectionInfo,data.getFclShareholderEntity());
        setIncome(idOportunity,connectionInfo,data.getIncomeEntity());
        setTarif(idOportunity,connectionInfo,data.getTarifEntity());
        setTirProject(idOportunity,connectionInfo,data.getTirProjectEntity());
        setUtility(idOportunity,connectionInfo,data.getUtilityEntity());
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

    public void setDividens(int id, ConnectionInfo connectionInfo, DividensEntity dividensEntity){
        List<Integer> ids = dividensRepository.findByDividens(id,connectionInfo);
        if(ids.size()>0){
            if(dividensRepository.deleteDividens(id,connectionInfo))
                dividensRepository.insertDividens(id,dividensEntity,connectionInfo);
        }
        else {
            dividensRepository.insertDividens(id,dividensEntity,connectionInfo);
        }
    }

    public void setFcl(int id, ConnectionInfo connectionInfo, FclEntity fclEntity){
        List<Integer> ids = fclRepository.findByFcl(id,connectionInfo);
        if(ids.size()>0){
            if(fclRepository.deleteFcl(id,connectionInfo))
                fclRepository.insertFcl(id,fclEntity,connectionInfo);
        }
        else {
            fclRepository.insertFcl(id,fclEntity,connectionInfo);
        }
    }

    public void setFclSH(int id, ConnectionInfo connectionInfo, FclShareholderEntity fclShareholderEntity){
        List<Integer> ids = fclShRepository.findByFclShareholder(id,connectionInfo);
        if(ids.size()>0){
            if(fclShRepository.deleteFclShareholder(id,connectionInfo))
                fclShRepository.insertFclShareholder(id,fclShareholderEntity,connectionInfo);
        }
        else {
            fclShRepository.insertFclShareholder(id,fclShareholderEntity,connectionInfo);
        }
    }

    public void setIncome(int id, ConnectionInfo connectionInfo, IncomeEntity incomeEntity){
        List<Integer> ids = incomeRepository.findByIncome(id,connectionInfo);
        if(ids.size()>0){
            if(incomeRepository.deleteIncome(id,connectionInfo))
                incomeRepository.insertIncome(id,incomeEntity,connectionInfo);
        }
        else {
            incomeRepository.insertIncome(id,incomeEntity,connectionInfo);
        }
    }

    public void setTarif(int id, ConnectionInfo connectionInfo, TarifEntity tarifEntity){
        List<Integer> ids = tarifRepository.findByTarif(id,connectionInfo);
        if(ids.size()>0){
            if(tarifRepository.deleteTarif(id,connectionInfo))
                tarifRepository.insertTarif(id,tarifEntity,connectionInfo);
        }
        else {
            tarifRepository.insertTarif(id,tarifEntity,connectionInfo);
        }
    }

    public void setTirProject(int id, ConnectionInfo connectionInfo, TirProjectEntity tirProjectEntity){
        List<Integer> ids = tirProjectRepository.findByTirProject(id,connectionInfo);
        if(ids.size()>0){
            if(tirProjectRepository.deleteTirProject(id,connectionInfo))
                tirProjectRepository.insertTirProject(id,tirProjectEntity,connectionInfo);
        }
        else {
            tirProjectRepository.insertTirProject(id,tirProjectEntity,connectionInfo);
        }
    }

    public void setUtility(int id, ConnectionInfo connectionInfo, UtilityEntity utility){
        List<Integer> ids = utilityRepository.findByUtility(id,connectionInfo);
        if(ids.size()>0){
            if(utilityRepository.deleteUtility(id,connectionInfo))
                utilityRepository.insertUtility(id,utility,connectionInfo);
        }
        else {
            utilityRepository.insertUtility(id,utility,connectionInfo);
        }
    }
}
