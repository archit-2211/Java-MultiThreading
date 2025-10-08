package MergeSort;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10) ; 
        try {
            

            List<Integer> numbers = List.of(9,99,86,23,1,2,3,11,2,34,5,44,34,5,67,76,88,89,899);

            Sorter sort = new Sorter(numbers, executor) ; 

            Future <List<Integer>> output = executor.submit(sort); 
            System.out.println(output.get()) ; 


        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
        finally {
            executor.shutdown();

        }
    }
    
}
