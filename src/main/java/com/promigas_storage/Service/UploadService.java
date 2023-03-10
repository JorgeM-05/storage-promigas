package com.promigas_storage.Service;


import com.promigas_storage.entity.*;
import com.promigas_storage.entity.FiguresFinancial.*;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;
import com.promigas_storage.entity.operatingFinancial.EnergySolutionEntity;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UploadService implements IUploadService {

    /**
     * Metodo que setea a un objeto cada registro para poder validarlo y guardarlo
     * por orden
     * 
     * @param
     * @return List<StorageDto> lista de registros
     */
    public List<StorageEntity> getCustomersDataFromExcel(InputStream inputStream) {
//        System.out.println(">>>>>>>>>>>>>>>>>");
//        List<StorageEntity> storages = new ArrayList<>();
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            int rowIndex = 0;
//
//            for (Row row : sheet) {
//                if (rowIndex == 0) {
//                    rowIndex++;
//                    continue;
//                }
//                Iterator<Cell> cellIterator = row.iterator();
//                int cellIndex = 0;
//                StorageEntity storage = new StorageEntity();
//                storage = loadEntities(storage);
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    switch (cellIndex) {
//                        case 0 : storage.getCountryeEntity().setNameContry(convertString(cell));
//                            break;
//                        case 1 :
//                            storage.getOpportunityEntity().setTypeProject(convertString(cell));
//                            break;
//                        case 2 :
//                            storage.getOpportunityEntity().setProjecTitle(convertString(cell));
//                            break;
//                        case 3 : storage.getSectorEntity().setTypeSector(convertString(cell));
//                            break;
//                        case 4 :
//                            storage.getOpportunityEntity().setDate(convertString(cell));
//                            break;
//                        case 5 :
//                            storage.getOpportunityEntity()
//                                    .setUbicGeograf(convertString(cell));
//                            break;
//                        case 6 :
//                            storage.getOpportunityEntity().setDescrip(convertString(cell));
//                            break;
//                        case 7 ->
//                            storage.getTypeContractEntity().setTypeContract(convertString(cell));
//                        case 8 ->
//                            storage.getOpportunityEntity()
//                                    .setHorizonPre((int) Double.parseDouble(convertString(cell)));
//                        case 9 ->
//                            storage.getOpportunityEntity().setHorizonOpe(
//                                    (int) Double.parseDouble(convertString(cell)));
//                        case 10 ->
//                            storage.getOpportunityEntity().setPoc(convertString(cell));
//                        case 11 ->
//                            storage.getOpportunityEntity().setTrmBase(Float.parseFloat(convertString(cell)));
//                        case 12 ->
//                            storage.getOpportunityEntity().setTrmFin(Float.parseFloat(convertString(cell)));
//                        case 13 ->
//                            storage.getOpportunityEntity()
//                                    .setPropCapexUsd(convertString(cell));
//                        case 14 ->
//                            storage.getOpportunityEntity()
//                                    .setPropCapexCop(convertString(cell));
//                        case 15 -> storage.getBenefTributariesEntity()
//                                .setBenef1715(validatesTrue(
//                                        cell.getStringCellValue()));
//                        case 16 -> storage.getBenefTributariesEntity().setBenefLawCrec(validatesTrue(
//                                cell.getStringCellValue()));
//                        case 17 -> storage.getBenefTributariesEntity().setBenefCej(validatesTrue(
//                                cell.getStringCellValue()));
//                        case 18 -> storage.getBenefTributariesEntity()
//                                .setBenefOthers(validatesTrue(
//                                        cell.getStringCellValue()));
//                        case 19 ->
//                            storage.getOpportunityEntity()
//                                    .setFinancilAsset(validatesTrue(cell.getStringCellValue()));
//                        case 20 ->
//                            storage.getCapexEntity().setYear(convertString(cell));
//                        // Moneda por validar
//                        case 21 -> storage.getOpportunityEntity().setCity(convertString(cell));
//                        case 22 ->
//                            storage.getCapexEntity().setCapexUsd(Double.parseDouble(convertString(cell)));
//                        case 24 ->
//                            storage.getEbitdaEntity()
//                                    .setValueUsd(Double.parseDouble(convertString(cell)));
//                        case 25 ->
//                            storage.getEbitdaEntity()
//                                    .setValueCop(Double.parseDouble(convertString(cell)));
//                        case 26 ->
//                            storage.getIncomeEntity().setIncomeUsd(Double.parseDouble(convertString(cell)));
//                        case 27 ->
//                            storage.getIncomeEntity().setIncomeCop(Double.parseDouble(convertString(cell)));
//                        case 28 ->
//                            storage.getUtilityEntity().setUtilityUsd(Double.parseDouble(convertString(cell)));
//                        case 29 ->
//                            storage.getUtilityEntity().setUtilityCop(Double.parseDouble(convertString(cell)));
//                        case 30 -> storage.getFclEntity().setFclUsd(Double.parseDouble(convertString(cell)));
//                        case 31 -> storage.getFclEntity().setFclCop(Double.parseDouble(convertString(cell)));
//                        case 32 ->
//                            storage.getFclShareholderEntity()
//                                    .setShareholderUsd(Double.parseDouble(convertString(cell)));
//                        case 33 -> storage.getFclShareholderEntity()
//                                .setShareholderCop(Double.parseDouble(convertString(cell)));
//                        case 34 ->
//                            storage.getDividensEntity()
//                                    .setDividensUsd(Double.parseDouble(convertString(cell)));
//                        case 35 ->
//                            storage.getDividensEntity().setDividensCop(Double.parseDouble(convertString(cell)));
//                        case 36 -> storage.getTarifEntity().setValueTarifUsd(
//                                Double.parseDouble(convertString(cell)));
//                        case 37 ->
//                            storage.getTarifEntity().setValueTarifCop(Double.parseDouble(convertString(cell)));
//                        case 38 ->
//                            storage.getTarifEntity().setYear(convertString(cell));
//                        case 39 ->
//                            storage.getTirProjectEntity().setTirprojfigure(Double.parseDouble(convertString(cell)));
//                        case 40 ->
//                            storage.getTirEquityEntity().setTirEqFig(Double.parseDouble(convertString(cell)));
//                        case 41 ->
//                            storage.getTirEquityEntity().setTirEqCapital(convertString(cell));
//                        case 42 ->
//                            storage.getTirEquityEntity().setTirEqCost(convertString(cell));
//                        case 43 -> storage.getEnergySolutionEntity()
//                                .setSolEnergyPowerFigure(Float.parseFloat(convertString(cell)));
//                        case 44 -> storage.getEnergySolutionEntity().setSolGenerFigure(
//                                convertString(cell));
//                        case 45 ->
//                            storage.getEnergySolutionEntity().setSolDegradation(convertString(cell));
//                        case 46 -> storage.getEnergySolutionEntity()
//                                .setSolEnergyHourSun(Float.parseFloat(convertString(cell)));
//                        case 47 -> storage.getTransportEntity()
//                                .setTransportCapacityMax(Float.parseFloat(convertString(cell)));
//                        case 48 -> storage.getTransportEntity()
//                                .setTransportCapacityHired(Float.parseFloat(convertString(cell)));
//                        case 49 -> storage.getTransportEntity()
//                                .setTransportVolumen(Float.parseFloat(convertString(cell)));
//                        case 50 -> storage.getTransportEntity()
//                                .setTransportLengthpipeline(Float.parseFloat(convertString(cell)));
//                        case 51 -> storage.getDistributionEntity()
//                                .setVolumeDistribution(Float.parseFloat(convertString(cell)));
//                        case 52 -> storage.getDistributionEntity()
//                                .setDistributionUsers(Float.parseFloat(convertString(cell)));
//                        case 53 ->
//                            storage.getDistributionEntity()
//                                    .setDistriPeriod(Float.parseFloat(convertString(cell)));
//                        case 54 ->
//                            storage.getDistributionEntity()
//                                    .setDistributionKms(Float.parseFloat(convertString(cell)));
//                        case 55, 56 ->
//                            storage.getDistributionEntity()
//                                    .setDistributionPenetration(Float.parseFloat(convertString(cell)));
//                        default -> {
//                        }
//                    }
//                    cellIndex++;
//                }
//                storages.add(storage);
//            }
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//        System.out.println(storages);
//        return storages;
        return null;
    }


    /**
     * Metodo que valida el tipo de celda para que no de error por numericos o
     * strings
     * 
     * @return String. Campo convertido a string
     */
    public String convertString(Cell cell) {
        if (cell.getCellType().toString().equals("STRING")) {
            return cell.getStringCellValue();
        }
        return String.valueOf(cell.getNumericCellValue());
    }

    /**
     * Metodo que valida si es true o false
     */

    public Boolean validatesTrue(String param) {
        return param.equals("True") || param.equals("true") ? true : false;
    }

    public StorageEntity loadEntities(StorageEntity storage) {
        BenefTributariesEntity benefTributariesEntity = new BenefTributariesEntity();
        CountryEntity countryEntity = new CountryEntity();
        OpportunitiesEntity opportunityEntity = new OpportunitiesEntity();
        SectorEntity sectorEntity = new SectorEntity();
        TypeContractEntity typeContractEntity = new TypeContractEntity();
        DistributionEntity distributionEntity = new DistributionEntity();
        EnergySolutionEntity energySolutionEntity = new EnergySolutionEntity();
        TransportEntity transportEntity = new TransportEntity();
        CapexEntity capexEntity = new CapexEntity();
        DividensEntity dividensEntity = new DividensEntity();
        EbitdaEntity ebitdaEntity = new EbitdaEntity();
        FclEntity fclEntity = new FclEntity();
        FclShareholderEntity fclShareholderEntity = new FclShareholderEntity();
        IncomeEntity incomeEntity = new IncomeEntity();
        TarifEntity tarifEntity = new TarifEntity();
        TirEquityEntity tirEquityEntity = new TirEquityEntity();
        TirProjectEntity tirProjectEntity = new TirProjectEntity();
        UtilityEntity utilityEntity = new UtilityEntity();
        storage.setCountryeEntity(countryEntity);
        storage.setBenefTributariesEntity(benefTributariesEntity);
        storage.setOpportunityEntity(opportunityEntity);
        storage.setDistributionEntity(distributionEntity);
        storage.setCapexEntity(capexEntity);
        storage.setUtilityEntity(utilityEntity);
        storage.setTypeContractEntity(typeContractEntity);
        storage.setTransportEntity(transportEntity);
        storage.setTirProjectEntity(tirProjectEntity);
        storage.setTirEquityEntity(tirEquityEntity);
        storage.setTarifEntity(tarifEntity);
        storage.setSectorEntity(sectorEntity);
        storage.setIncomeEntity(incomeEntity);
        storage.setFclShareholderEntity(fclShareholderEntity);
        storage.setFclEntity(fclEntity);
        storage.setEbitdaEntity(ebitdaEntity);
        storage.setDividensEntity(dividensEntity);
        storage.setEnergySolutionEntity(energySolutionEntity);
        return storage;
    }
}
