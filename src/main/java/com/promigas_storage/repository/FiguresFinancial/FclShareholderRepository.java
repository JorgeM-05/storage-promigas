package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.FclShareholderEntity;

import java.util.List;

public interface FclShareholderRepository {
    public List<Integer> findByFclShareholder(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertFclShareholder(int idOpportunity, FclShareholderEntity fclShareholderEntity, ConnectionInfo connectionInfo);
    public boolean deleteFclShareholder(int idOpportunity, ConnectionInfo connectionInfo);
}
