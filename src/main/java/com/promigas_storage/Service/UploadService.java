package com.promigas_storage.Service;

import com.promigas_storage.entity.*;
import com.promigas_storage.entity.FiguresFinancial.*;
import com.promigas_storage.entity.operatingFinancial.DistributionEntity;
import com.promigas_storage.entity.operatingFinancial.EnergySolutionEntity;
import com.promigas_storage.entity.operatingFinancial.TransportEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UploadService {

    /**
     * Metodo que setea a un objeto cada registro para poder validarlo y guardarlo
     * por orden
     * 
     * @param
     * @return List<StorageDto> lista de registros
     */
    public List<StorageEntity> getCustomersDataFromExcel(InputStream inputStream) {
        System.out.println(">>>>>>>>>>>");
        List<StorageEntity> storages = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                StorageEntity storage = new StorageEntity();
                storage = loadEntities(storage);
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            storage.getCountryEntity().setNameContry(convertString(cell));
                            break;
                        case 1:
                            storage.getOpportunityEntity().setTypeProject(convertString(cell).toUpperCase());
                            break;
                        case 2:
                            storage.getOpportunityEntity().setProjecTitle(convertString(cell));
                            break;
                        case 3:
                            storage.getSectorEntity().setTypeSector(convertString(cell));
                            break;
                        case 4:
                            String[] arrOfStr2 = String.valueOf(cell).split("-");
                            String[] array = arrOfStr2[1].split("\\.");
                            cell.setCellValue(arrOfStr2[2]+"-"+array[0]+"-"+arrOfStr2[1]);
                            storage.getOpportunityEntity().setDate(convertString(cell));
                            break;
                        case 5:
                            storage.getOpportunityEntity()
                                    .setCoordinates(convertString(cell));
                            break;
                        case 6:
                            storage.getOpportunityEntity().setDescrip(convertString(cell));
                            break;
                        case 7:
                            storage.getTypeContractEntity().setTypeContract(convertString(cell));
                            break;
                        case 8:
                            storage.getOpportunityEntity()
                                    .setHorizonPre((int) Double.parseDouble(convertString(cell)));
                            break;
                        case 9:
                            storage.getOpportunityEntity().setHorizonOpe(
                                    (int) Double.parseDouble(convertString(cell)));
                            break;
                        case 10:
                            String[] arrOfStr = String.valueOf(cell).split("-");
                            String[] arr = arrOfStr[1].split("\\.");
                            cell.setCellValue(arr[0]+"-"+arrOfStr[2]);
                            storage.getOpportunityEntity().setPoc(convertString(cell));
                            break;
                        case 11:
                            storage.getOpportunityEntity().setTrmBase(Float.parseFloat(convertString(cell)));
                            break;
                        case 12:
                            storage.getOpportunityEntity().setTrmFin(Float.parseFloat(convertString(cell)));
                            break;
                        case 13:
                            double v = Double.parseDouble(String.valueOf(cell)) * 100;
                            cell.setCellValue(Math.ceil(v) +"%");
                            storage.getOpportunityEntity().setPropCapexUsd(convertString(cell));
                            break;
                        case 14:
                            double c = Double.parseDouble(String.valueOf(cell)) * 100;
                            cell.setCellValue(Math.ceil(c) +"%");
                            storage.getOpportunityEntity()
                                    .setPropCapexCop(convertString(cell));
                            break;
                        case 15:
                            storage.getBenefTributariesEntity()
                                    .setBenef1715(validatesTrue(
                                            cell.getStringCellValue()));
                            break;
                        case 16:
                            storage.getBenefTributariesEntity().setBenefLawCrec(validatesTrue(
                                    cell.getStringCellValue()));
                            break;
                        case 17:
                            storage.getBenefTributariesEntity().setBenefCej(validatesTrue(
                                    cell.getStringCellValue()));
                            break;
                        case 18:
                            storage.getBenefTributariesEntity()
                                    .setBenefOthers(validatesTrue(
                                            cell.getStringCellValue()));
                            break;
                        case 19:
                            storage.getOpportunityEntity()
                                    .setFinancialAsset(validatesTrue(cell.getStringCellValue()));
                            break;
                        case 20:
                            String[] year = String.valueOf(cell).split("\\.");
                            storage.getCapexEntity().setYear(year[0]);
                            break;
                        case 21:
                            storage.getCountryEntity().setCod_country(convertString(cell));
//                            storage.getOpportunityEntity().setCity(convertString(cell));
                            break;
                        case 22:
                            storage.getCapexEntity().setCapexUsd(Double.parseDouble(convertString(cell)));
                            break;
                        case 23:
                            storage.getCapexEntity().setCapexCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 24:
                            storage.getEbitdaEntity()
                                    .setValueUsd(Double.parseDouble(convertString(cell)));
                            System.out.println(">>>>>>>>>>>2");

                            break;
                        case 25:
                            storage.getEbitdaEntity()
                                    .setValueCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 26:
                            storage.getIncomeEntity().setIncomeUsd(Double.parseDouble(convertString(cell)));
                            break;
                        case 27:
                            storage.getIncomeEntity().setIncomeCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 28:
                            storage.getUtilityEntity().setUtilityUsd(Double.parseDouble(convertString(cell)));
                            break;
                        case 29:
                            storage.getUtilityEntity().setUtilityCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 30:
                            storage.getFclEntity().setFclUsd(Double.parseDouble(convertString(cell)));
                            break;
                        case 31:
                            storage.getFclEntity().setFclCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 32:
                            storage.getFclShareholderEntity()
                                    .setShareholderUsd(Double.parseDouble(convertString(cell)));
                            break;
                        case 33:
                            storage.getFclShareholderEntity()
                                    .setShareholderCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 34:
                            storage.getDividensEntity()
                                    .setDividensUsd(Double.parseDouble(convertString(cell)));
                            break;
                        case 35:
                            storage.getDividensEntity().setDividensCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 36:
                            storage.getTarifEntity().setValueTarifUsd(
                                    Double.parseDouble(convertString(cell)));
                            break;
                        case 37:
                            storage.getTarifEntity().setValueTarifCop(Double.parseDouble(convertString(cell)));
                            break;
                        case 38:
                            String[] yearEbitda = String.valueOf(cell).split("\\.");
                            storage.getTarifEntity().setYear(yearEbitda[0]);
                            break;
                        case 39:
                            storage.getTirProjectEntity().setTirprojfigure(Double.parseDouble(convertString(cell)));
                            System.out.println(">>>>>>>>>>>3");

                            break;

                    //  tir_project_year
                    //  tir_project_unit
                        case 40:
                            storage.getTirEquityEntity().setTirEqFig(Double.parseDouble(convertString(cell)));
                            break;
                        case 41:
                            storage.getTirEquityEntity().setTirEqCapital(convertString(cell));
                            break;
                        case 42:
                            storage.getTirEquityEntity().setTirEqCost(convertString(cell));
                            break;
                    // tir_equity_year
                    // tir_equity_unit
                    // tir_equity_amortization
                        case 43:
                            storage.getEnergySolutionEntity()
                                    .setSolEnergyPowerFigure(Float.parseFloat(convertString(cell)));
                            break;
                        case 44:
                            storage.getEnergySolutionEntity().setSolGenerFigure(
                                    convertString(cell));
                            break;
                        case 45:
                            storage.getEnergySolutionEntity().setSolDegradation(convertString(cell));
                            break;
                        case 46:
                            storage.getEnergySolutionEntity()
                                    .setSolEnergyHourSun(Float.parseFloat(convertString(cell)));
                            break;
                    //solenerg_power_unit
                    //solenerg_gener_unit
                        case 47:
                            storage.getTransportEntity()
                                    .setTransportCapacityMax(Float.parseFloat(convertString(cell)));
                            break;
                        case 48:
                            storage.getTransportEntity()
                                    .setTransportCapacityHired(Float.parseFloat(convertString(cell)));
                            break;
                        case 49:
                            storage.getTransportEntity()
                                    .setTransportVolumen(Float.parseFloat(convertString(cell)));
                            break;
                        case 50:
                            storage.getTransportEntity()
                                    .setTransportLengthpipeline(Float.parseFloat(convertString(cell)));
                            break;
                        case 51:
                            storage.getDistributionEntity()
                                    .setVolumeDistribution(Float.parseFloat(convertString(cell)));
                            break;
                        case 52:

                            storage.getDistributionEntity()
                                    .setDistributionUsers(Float.parseFloat(convertString(cell)));
                            System.out.println(">>>>>>>>>>>3.1");

                            break;
                        case 53:
                            storage.getDistributionEntity()
                                    .setDistriPeriod(Float.parseFloat(convertString(cell)));
                            break;
                        case 54:
                            storage.getDistributionEntity()
                                    .setDistributionKms(Float.parseFloat(convertString(cell)));
                            break;
                        case 55:

                            storage.getDistributionEntity()
                                    .setDistributionPenetration(Float.parseFloat(convertString(cell)));
                            System.out.println(">>>>>>>>>>>4");
                            break;
                        case 56:
                            // indicadores_deuda_usd
                            System.out.println("case 56");
                            break;
                    // indicadores_deuda_cop
                        case 57:
                            break;
                    // deuda_neta_usd
                        case 58:
                            break;
                    // deuda_neta_cop
                        case 59:
                            break;
                    // indicador_ebitda_usd
                        case 60:
                            break;
                    // indicador_ebitda_cop
                        case 61:
                            break;
                    //FFO_usd
                        case 62:
                            break;
                    //FFO_cop
                        case 63:
                            break;
                        case 64:
                            System.out.println(">>>>>>>>>>>5");
                            storage.getOpportunityEntity()
                                    .setCity(convertString(cell));
                            System.out.println(">>>>>>>>>>>5");
                            break;
                        case 65:
                            storage.getCountryEntity().setCod_country(convertString(cell));
                            break;
                        case 66:
                            storage.getCountryEntity()
                                    .setUrlFlags(convertString(cell));
                            break;
                    }
                    cellIndex++;
                }
                storages.add(storage);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println(storages);
        return storages;
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
        storage.setCountryEntity(countryEntity);
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
