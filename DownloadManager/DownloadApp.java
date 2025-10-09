package DownloadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class DownloadApp {
    public static void main(String[] args) {
        List<String> downloadFiles = new ArrayList<>(); 
        downloadFiles.add("www.google.com") ; 
        downloadFiles.add("www.scaler.com") ;

        System.out.println("This is the main thread.   " + Thread.currentThread().getName()) ; 

        ExecutorService ex = Executors.newSingleThreadExecutor(); 

        DownloadManager downloadManager = new DownloadManager(ex);
        System.out.println("Downloading using separate thread, but not pool of threads, single separate executor thread"); 

        downloadManager.linkDownloader(downloadFiles);
        ex.shutdown();

    }
    
}
