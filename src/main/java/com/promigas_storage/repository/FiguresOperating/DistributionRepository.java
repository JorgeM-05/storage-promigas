package com.promigas_storage.repository.FiguresOperating;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;

import java.util.List;

public interface DistributionRepository {
    public List<Integer> findByDistribution(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertDistribution(int idOpportunity, DistributionEntity distributionEntity, ConnectionInfo connectionInfo);
    public boolean deleteDistribution(int idOpportunity, ConnectionInfo connectionInfo);
}
