package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.TarifEntity;

import java.util.List;

public interface TarifRepository {
    public List<Integer> findByTarif(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertTarif(int idOpportunity, TarifEntity tarifEntity, ConnectionInfo connectionInfo);
    public boolean deleteTarif(int idOpportunity, ConnectionInfo connectionInfo);
}
