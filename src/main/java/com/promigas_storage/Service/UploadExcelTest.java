package com.promigas_storage.Service;

import com.promigas_storage.entity.*;
import com.promigas_storage.entity.FiguresFinancial.*;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;
import com.promigas_storage.entity.operatingFinancial.EnergySolutionEntity;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UploadExcelTest {
    Logger logger = Logger.getLogger(UploadExcelTest.class.getName());

    public List<StorageEntity> testStorages(){
        List<StorageEntity> listStorage = new ArrayList<>();
        logger.info("agregando 1 ");
        listStorage.add(getStorageOne());

        logger.info("result TEST :: \n "+listStorage);
        return listStorage;
    }
    /** simulado con uno de la BD que NO esiste*/

    public StorageEntity getStorageOne(){
        StorageEntity storage = new StorageEntity();
        logger.info("TEST 1 ini");
        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setCod_country("Ec");
        System.out.println("<<<<1");
        countryEntity.setUrlFlags("https://countryflagsapi.com/png/ec");
        countryEntity.setNameContry("Ecuador");

        System.out.println("<<<<2");
        OpportunitiesEntity opportunities = new OpportunitiesEntity();
        opportunities.setCity("Machala");
        opportunities.setCoordinates("-1.356100009620227, -78.61881109829805");

        System.out.println("<<<<3");
        SectorEntity sectorEntity = new SectorEntity();
        sectorEntity.setTypeSector("eolico");
        sectorEntity.setUnique_id(5);

        TypeContractEntity typeContract = new TypeContractEntity();
        typeContract.setUnique_id(0);
        typeContract.setTypeContract("termino fijo");


        opportunities.setIdSector(sectorEntity);
        opportunities.setIdContract(typeContract);
        opportunities.setTypeProject("M&A");
        opportunities.setProjecTitle("fotovol2");
        opportunities.setDate("2023-12-03");
        opportunities.setDescrip("luminis");
        opportunities.setHorizonOpe(1);
        opportunities.setHorizonPre(12);
        opportunities.setPoc("ene-2025");
        opportunities.setTrmBase(4500);
        opportunities.setTrmFin(4800);
        opportunities.setPropCapexUsd(String.valueOf(58));
        opportunities.setPropCapexCop(String.valueOf(76));
        opportunities.setFinancialAsset(true);
        logger.info("<<<<1>>>>>>>> "+opportunities);


        BenefTributariesEntity benefTributariesEntity = new BenefTributariesEntity();
        logger.info("<<<<1");
        benefTributariesEntity.setBenef1715(true);
        benefTributariesEntity.setBenefCej(true);
        benefTributariesEntity.setBenefOthers(false);
        logger.info("<<<<2");
        benefTributariesEntity.setBenefLawCrec(true);



        System.out.println("<<<5");

        CapexEntity capexEntity = new CapexEntity();
        capexEntity.setCapexCop(234);
        capexEntity.setCapexCop(235);
        capexEntity.setYear("2019");

        System.out.println("<<<6");

        EbitdaEntity ebitdaEntity = new EbitdaEntity();
       ebitdaEntity.setValueUsd(234.6);
       ebitdaEntity.setValueCop(647.9);
       ebitdaEntity.setYear("2008");

       DividensEntity dividensEntity = new DividensEntity ();
        dividensEntity.setYear("2021");
        dividensEntity.setDividensUsd(26553.9);
        dividensEntity.setDividensCop(98762);

        FclEntity fclEntity = new FclEntity ();
        fclEntity.setYear("2026");
        fclEntity.setFclUsd(1626.2);
        fclEntity.setFclCop(343.5);
        logger.info("<<<<8");
        FclShareholderEntity fclShareholderEntity = new FclShareholderEntity();
        fclShareholderEntity.setYear("2022");
        fclShareholderEntity.setShareholderUsd(247.4);
        fclShareholderEntity.setShareholderCop(2837.3);
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.setYear("2018");
        incomeEntity.setIncomeUsd(3655.4);
        incomeEntity.setIncomeCop(2653.3);
        TarifEntity tarifEntity = new TarifEntity();
        tarifEntity.setYear("2017");
        tarifEntity.setValueTarifCop(2863.7);
        tarifEntity.setValueTarifUsd(27363);
        TirEquityEntity tirEquityEntity = new TirEquityEntity();
        tirEquityEntity.setYear("2025");
        tirEquityEntity.setTirEqFig(2973.3);
        tirEquityEntity.setTirEqUnit("m.n.d.i");
        tirEquityEntity.setTirEqCapital("1762");
        tirEquityEntity.setTirEqCost("26535");
        tirEquityEntity.setTirEqAmortizacion("2635");
        TirProjectEntity tirProjectEntity = new TirProjectEntity();
        tirProjectEntity.setYear("2023");
        tirProjectEntity.setTirprojfigure(2836);
        tirProjectEntity.setTirprojectunit("u.m.f.");
        UtilityEntity utilityEntity = new UtilityEntity();
        utilityEntity.setYear("2024");
        utilityEntity.setUtilityUsd(23366);
        utilityEntity.setUtilityCop(27738);
        DistributionEntity distributionEntity = new DistributionEntity();
        distributionEntity.setDistributionKms(27736);
        distributionEntity.setDistributionUsers(72633);
        distributionEntity.setDistributionPenetration(27366);
        distributionEntity.setDistriPeriod(6635);
        distributionEntity.setVolumeDistribution(6265353);
        EnergySolutionEntity energySolutionEntity = new EnergySolutionEntity();
        energySolutionEntity.setSolDegradation(15525);
        energySolutionEntity.setSolEnergyHourSun(2635);
        energySolutionEntity.setSolEnergyPowerFigure(26535);
        energySolutionEntity.setSolEnergyPowerUnit("MWp");
        energySolutionEntity.setSolGenerFigure(7263);
        energySolutionEntity.setSolGenerUnit("MWh/año");
        TransportEntity transportEntity = new TransportEntity();
        transportEntity.setTransportLengthpipeline(Float.valueOf("4.5"));
        transportEntity.setTransportVolumen(Float.valueOf(40));
        transportEntity.setTransportCapacityMax(Float.valueOf(23));
        transportEntity.setTransportCapacityHired(Float.valueOf(25));


        storage.setCountryEntity(countryEntity);
        storage.setOpportunityEntity(opportunities);
        storage.setCapexEntity(capexEntity);
        storage.setEbitdaEntity(ebitdaEntity);
        storage.setDividensEntity(dividensEntity);
        storage.setDistributionEntity(distributionEntity);
        storage.setFclEntity(fclEntity);
        storage.setIncomeEntity(incomeEntity);
        storage.setBenefTributariesEntity(benefTributariesEntity);
        storage.setEnergySolutionEntity(energySolutionEntity);
        storage.setTarifEntity(tarifEntity);
        storage.setTransportEntity(transportEntity);
        storage.setFclShareholderEntity(fclShareholderEntity);
        storage.setTirEquityEntity(tirEquityEntity);
        storage.setTirProjectEntity(tirProjectEntity);
        storage.setUtilityEntity(utilityEntity);
        storage.setSectorEntity(sectorEntity);
        storage.setTypeContractEntity(typeContract);

        logger.info("FIN TEST :: "+storage);
        return storage;
    }

}
