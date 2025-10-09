package DownloadManager.ImageProcessing;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ImageProcessor {

    public static void main(String[] args) {
        /*
         * Here I am writing using Lamda functions as the code to double the pixels of image are not large, but if this feels over whelming we can create concrete class extending Runnable or Callable 
         * and then created separate objects and provide these tasks to the executor service. 
         */
    

    int[][] image = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

    /*
     * We need to create thread pool of size 4 -> newFixedThreadPool 
     * Each thread in threadpool should work on 1 quadrant
     */

     int n = image.length ;
     int mid = n/2 ; 

     ExecutorService ex = Executors.newFixedThreadPool(4) ; 
     

     /*
      * This where we are doubling quadrant one 
      what is quadrant one dividing the matrix into 4 halves all first half will be from row number 0 to row number mid-1 and colNumber 0 to col number mid-1
      */
     ex.submit(() -> {
        System.out.println("Processing Quadrant 1 ") ; 
        for (int row = 0 ; row<mid ; row++) {
            for (int col = 0 ; col < mid; col++) {
                image[row][col] = image[row][col]*2; 
            }
            
        }
        System.out.println("Processed Quadrant One using " + " " + Thread.currentThread().getName());

     });

     /*
      * Now lets start with quadrant 2 : Rownumber 0 to mid-1 and colNumber mid to length-1
      */

      ex.submit(() -> {
        System.out.println("Processing Quadrant 2 ") ; 
        for (int row = 0 ; row < mid; row++) {
            for (int col = mid ; col < n; col ++) {
                image[row][col] *= 2; 

            }

        }
        System.out.println("Processed Quadrant 2 using " + " " + Thread.currentThread().getName());

      });

      /*
       * Now lets do the same for quadrant 3 
       * Row number mid to length-1 and colNumber 0 to mid-1
       */

       ex.submit(() -> {
        System.out.println("Processing Quadrant 3 ") ; 
        for (int row = mid; row<n ; row++) {
            for(int col = 0; col < mid; col++) {
                image[row][col] *=2 ; 
            }
        }

        System.out.println("Processed Quadrant 3 using " + " " + Thread.currentThread().getName());

       });

       /*
        * Now comes the last quadrant rowNumber from mid to length-1 and colNumber from mid to length-1
        */

        ex.submit(() -> {
        System.out.println("Processing Quadrant 4 ") ; 
        for (int row = mid; row<n ; row++) {
            for(int col = mid; col < n; col++) {
                image[row][col] *=2 ; 
            }
        }
        System.out.println("Processed Quadrant 4 using " + " " + Thread.currentThread().getName());
    });

    ex.shutdown(); 

    /*
     * Here before printing we have to wait for the completion of execution of threads. For this we can take future values of void and call get individually or just check if executor service is active or terminated
     * If its terminated -> All threads are siuccessfully Processed
     */

     while (!ex.isTerminated()){
        /*
         * This while loop doesnt do anyhting just doesnt let the main thread to move forward this point untill executor service is terminated
         */

     }
     
     for (int[] row : image) {
        for (int val : row) {
            System.out.print(val + "  ");


        }
        System.out.println("");
     }






    }
}
