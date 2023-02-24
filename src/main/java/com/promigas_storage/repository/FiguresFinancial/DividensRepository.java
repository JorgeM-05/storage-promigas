package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.DividensEntity;

import java.util.List;

public interface DividensRepository {
    public List<Integer> findByDividens(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertDividens(int idOpportunity, DividensEntity dividensEntity, ConnectionInfo connectionInfo);
    public boolean deleteDividens(int idOpportunity, ConnectionInfo connectionInfo);
}
