package com.promigas_storage.Service;

import com.promigas_storage.entity.StorageEntity;

import java.util.ArrayList;
import java.util.List;

public class UploadExcelTest {
    public List<StorageEntity> testStorages(){
        List<StorageEntity> listStorage = new ArrayList<>();

        listStorage.add(getStorageOne());
        listStorage.add(getStorageTwo());
        return listStorage;
    }
    /** simulado con uno de la BD que NO esiste*/

    public StorageEntity getStorageOne(){
        StorageEntity storage = new StorageEntity();

        storage.getCountryEntity().setCod_country("COL");
        storage.getCountryEntity().setUrlFlags("irlll.....");
        storage.getCountryEntity().setNameContry("");

        storage.getOpportunityEntity().setCity("Colombia");
        storage.getOpportunityEntity().setCoordinates("dsdss");

        storage.getCapexEntity().setCapexCop(22.2);
        storage.getCapexEntity().setCapexCop(22.2);
        storage.getCapexEntity().setYear("2022");

        return storage;
    }

    /** simulado con uno de la BD que YA esiste*/
    public StorageEntity getStorageTwo(){
        StorageEntity storage = new StorageEntity();

        storage.getCountryEntity().setCod_country("COL");
        storage.getCountryEntity().setUrlFlags("irlll.....");
        storage.getCountryEntity().setNameContry("name");

        storage.getOpportunityEntity().setCity("Colombia");
        storage.getOpportunityEntity().setCoordinates("dsdss");

        storage.getCapexEntity().setCapexCop(22.2);
        storage.getCapexEntity().setCapexCop(22.2);
        storage.getCapexEntity().setYear("2022");
        return storage;
    }
}
