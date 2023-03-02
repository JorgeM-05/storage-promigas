package com.promigas_storage.entity.operatingFinancial;

import com.promigas_storage.entity.OpportunitiesEntity;
import lombok.Data;

@Data
public class EnergySolutionEntity {

    private int unique_id;
    private OpportunitiesEntity idOpportunity;
    private String solEnergyPowerUnit;
    private float solEnergyPowerFigure;
    private String solGenerUnit; // este valor esta en BD como un string y aqui un float
    private float  solGenerFigure;
    private float solDegradation;
    private float solEnergyHourSun;
}
