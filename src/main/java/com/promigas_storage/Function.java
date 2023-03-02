package com.promigas_storage;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.*;
import com.promigas_storage.Service.UploadDataService;
import com.promigas_storage.Service.UploadExcelTest;
import com.promigas_storage.entity.StorageEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    Logger logger = Logger.getLogger(Function.class.getName());

    @FunctionName("info-processor")
    public void blobProcessor(
            @BlobTrigger(name = "blobProcessor",
                    dataType = "binary",
                    path = "datapromigas/{name}",
                    connection = "AzureStorageConnectionString") byte[] content,
            @BindingName("name") String filename,
            final ExecutionContext context){
        context.getLogger().info("Name: " + filename + " Size: " + content + " bytes");
        InputStream document = new ByteArrayInputStream(content);
        if(filename.equals("data.xlsx")) {
            logger.info("Storga......." + filename);
            UploadDataService uploadDataService = new UploadDataService();
            if (filename.equals("data.xlsx")) {
                UploadExcelTest test = new UploadExcelTest();
                //uploadService.getCustomersDataFromExcel(document);
               // uploadData.DataService(uploadService.getCustomersDataFromExcel(document));
                logger.info("leyendo excel :: "+filename);

                List<StorageEntity> storage = test.testStorages();
                logger.info("Result:: \n "+storage+"\n");
                uploadDataService.DataService(storage);
                logger.info("FIN");

            }
        }
    }
}
