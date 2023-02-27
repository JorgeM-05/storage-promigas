package com.promigas_storage.repository.FiguresOperating;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.operatingFinancial.EnergySolutionEntity;

import java.util.List;

public interface EnergySolutionRepository {
    public List<Integer> findByEnergySolution(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertEnergySolution(int idOpportunity, EnergySolutionEntity energySolutionEntity, ConnectionInfo connectionInfo);
    public boolean deleteEnergySolution(int idOpportunity, ConnectionInfo connectionInfo);
}
