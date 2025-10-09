package DownloadManager;

import java.util.concurrent.ExecutorService;
import java.util.*;

/*
 * We can further make this class as singleton; as it doesnt have amy state variables and creating this variable can be costly but this is just the example to understand multi threading, different thread pool 
 * executors
 * 
 * 
 */

public class DownloadManager  {
    private ExecutorService ex ; 

    public DownloadManager(ExecutorService ex) {
        this.ex = ex ; 

    }


    public void linkDownloader(List<String> links) {
        for (String link : links) {
            ex.submit(() -> {
                for (int i = 0; i <=100; i++) {
                    System.out.println("Download percentage : " + i) ; 

                    try {
                    Thread.sleep(500) ; 
    
                    }
                    catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Downloaded the link" + link + "    Using.  " + Thread.currentThread().getName());
            });
        }

    }


    
}
