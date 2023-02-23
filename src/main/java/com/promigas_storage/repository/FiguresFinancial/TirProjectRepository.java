package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.TirProjectEntity;

import java.util.List;

public interface TirProjectRepository {
    public List<Integer> findByTirProject(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertTirProject(int idOpportunity, TirProjectEntity tirProjectEntity, ConnectionInfo connectionInfo);
    public boolean deleteTirProject(int idOpportunity, ConnectionInfo connectionInfo);
}
