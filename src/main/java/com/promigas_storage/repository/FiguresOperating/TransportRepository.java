package com.promigas_storage.repository.FiguresOperating;

import com.promigas_storage.DTO.ConnectionInfo;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;

import java.util.List;

public interface TransportRepository {
    public List<Integer> findByTransport(int idOportunity, ConnectionInfo connectionInfo);
    public boolean insertTransport(int idOpportunity, TransportEntity transportEntity, ConnectionInfo connectionInfo);
    public boolean deleteTransport(int idOpportunity, ConnectionInfo connectionInfo);
}
