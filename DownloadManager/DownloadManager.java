package DownloadManager;

import java.util.concurrent.ExecutorService;
import java.util.*;

public class DownloadManager  {
    private ExecutorService ex ; 

    public DownloadManager(ExecutorService ex) {
        this.ex = ex ; 

    }


    public void linkDownloader(List<String> links) {
        for (String link : links) {
            ex.submit(() -> {
                System.out.println("Downloading the link" + link + "     " + Thread.currentThread().getName());
            });
        }

    }


    
}
