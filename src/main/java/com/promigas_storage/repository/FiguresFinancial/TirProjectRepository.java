package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.TirProjectEntity;

import java.util.List;

public interface TirProjectRepository {
    public List<Integer> findByTirProjectRepository(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertTirProjectRepository(int idOpportunity, TirProjectEntity tirProjectEntity, ConnectionInfo connectionInfo);
    public boolean deleteTirProjectRepository(int idOpportunity, ConnectionInfo connectionInfo);
}
