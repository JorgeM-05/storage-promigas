package com.promigas_storage.Service;

import com.promigas_storage.entity.StorageEntity;

import java.util.ArrayList;
import java.util.List;

public class UploadExcelTest {
    public List<StorageEntity> testStorages(){
        StorageEntity storage = new StorageEntity();
        List<StorageEntity> listStorage = new ArrayList<>();

        storage.getCountryEntity().setCod_country("COL");
        storage.getCountryEntity().setUrlFlags("irlll.....");

        storage.getOpportunityEntity().setCity("Colombia");
        storage.getOpportunityEntity().setCoordinates("dsdss");


        storage.getCapexEntity().setCapexCop(22.2);
        




        listStorage.add(storage);
        return listStorage;
    }
}
