package com.promigas_storage;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.*;
import com.promigas_storage.Service.UploadDataService;
import com.promigas_storage.Service.UploadService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
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
            System.out.println("Storga......." + filename);
            UploadDataService uploadData = new UploadDataService();
            if (filename.equals("data.xlsx")) {
                UploadService uploadService = new UploadService();
                uploadService.getCustomersDataFromExcel(document);
               // uploadData.DataService(uploadService.getCustomersDataFromExcel(document));
            }
        }
    }
}
