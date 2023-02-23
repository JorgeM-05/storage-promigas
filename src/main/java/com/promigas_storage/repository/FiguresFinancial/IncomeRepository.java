package com.promigas_storage.repository.FiguresFinancial;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.FiguresFinancial.CapexEntity;
import com.promigas_storage.entity.FiguresFinancial.IncomeEntity;

import java.util.List;

public interface IncomeRepository {
    public List<Integer> findByIncome(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertIncome(int idOpportunity, IncomeEntity incomeEntity, ConnectionInfo connectionInfo);
    public boolean deleteIncome(int idOpportunity, ConnectionInfo connectionInfo);
}
