package com.promigas_storage.Service;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.DTO.enums.ConstantsEnum;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.SecretAdapter;
import com.promigas_storage.entity.StorageEntity;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;
import com.promigas_storage.entity.operatingFinancial.EnergySolutionEntity;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;
import com.promigas_storage.repository.FiguresOperating.*;
import com.promigas_storage.repository.SecretPort;

import java.util.List;

public class UploadOperatingService {
    private DistributionRepository distributionRepository = new DistributionRepositorylmp();
    private EnergySolutionRepository energySolutionRepository = new EnergySolutionlmp();
    private TransportRepository transportRepository = new TransportRepositorylmp();

    public void setDataFinancial(int idOportunity, StorageEntity data){
        SecretPort secretPort = new SecretAdapter();
        ConnectionInfo connectionInfo = secretPort.querySecretConnection(ConstantsEnum.SECRET_SQL_SERVER.getValue());
        setdistribution(idOportunity,connectionInfo,data.getDistributionEntity());
        setEnergy(idOportunity,connectionInfo,data.getEnergySolutionEntity());
        setTransport(idOportunity,connectionInfo,data.getTransportEntity());
    }

    public void setdistribution(int id, ConnectionInfo connectionInfo, DistributionEntity distributionEntity){
        List<Integer> ids = distributionRepository.findByDistribution(id,connectionInfo);
        if(ids.size()>0){
            if(distributionRepository.deleteDistribution(id,connectionInfo))
                distributionRepository.insertDistribution(id,distributionEntity,connectionInfo);
        }
        else {
            distributionRepository.insertDistribution(id,distributionEntity,connectionInfo);
        }
    }
    public void setEnergy(int id, ConnectionInfo connectionInfo, EnergySolutionEntity energySolutionEntity){
        List<Integer> ids = energySolutionRepository.findByEnergySolution(id,connectionInfo);
        if(ids.size()>0){
            if(energySolutionRepository.deleteEnergySolution(id,connectionInfo))
                energySolutionRepository.insertEnergySolution(id,energySolutionEntity,connectionInfo);
        }
        else {
            energySolutionRepository.insertEnergySolution(id,energySolutionEntity,connectionInfo);
        }
    }
    public void setTransport(int id, ConnectionInfo connectionInfo, TransportEntity transportEntity){
        List<Integer> ids = transportRepository.findByTransport(id,connectionInfo);
        if(ids.size()>0){
            if(transportRepository.deleteTransport(id,connectionInfo))
                transportRepository.insertTransport(id,transportEntity,connectionInfo);
        }
        else {
            transportRepository.insertTransport(id,transportEntity,connectionInfo);
        }
    }
}
