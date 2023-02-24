package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.UtilityEntity;

import java.util.List;

public interface UtilityRepository {
    public List<Integer> findByUtility(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertUtility(int idOpportunity, UtilityEntity utilityEntity, ConnectionInfo connectionInfo);
    public boolean deleteUtility(int idOpportunity, ConnectionInfo connectionInfo);
}
