package com.promigas_storage.entity;


import com.promigas_storage.entity.FiguresFinancial.*;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;
import com.promigas_storage.entity.operatingFinancial.EnergySolutionEntity;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;
import lombok.Data;

@Data
public class StorageEntity {

  private BenefTributariesEntity benefTributariesEntity;
  private CountryEntity countryEntity;
  private OpportunitiesEntity opportunityEntity;
  private SectorEntity sectorEntity;
  private TypeContractEntity typeContractEntity;
  private DistributionEntity distributionEntity;
  private EnergySolutionEntity energySolutionEntity;
  private TransportEntity transportEntity;
  private CapexEntity capexEntity;
  private DividensEntity dividensEntity;
  private EbitdaEntity ebitdaEntity;
  private FclEntity fclEntity;
  private FclShareholderEntity fclShareholderEntity;
  private IncomeEntity incomeEntity;
  private TarifEntity tarifEntity;
  private TirEquityEntity tirEquityEntity;
  private TirProjectEntity tirProjectEntity;
  private UtilityEntity utilityEntity;

}