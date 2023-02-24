package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.FclEntity;

import java.util.List;

public interface FclRepository {
    public List<Integer> findByFcl(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertFcl(int idOpportunity, FclEntity fclEntity, ConnectionInfo connectionInfo);
    public boolean deleteFcl(int idOpportunity, ConnectionInfo connectionInfo);
}
