package MergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Callable<List<Integer>>{
    private List<Integer> numbers; 
    private ExecutorService executor ; 

    public Sorter(List<Integer> numbers, ExecutorService executor) {
        this.numbers = numbers ; 
        this.executor = executor; 

    }

    @Override
    public List<Integer> call() throws Exception {

        if (numbers.size() <= 1) {
            return this.numbers; 
        }

        List<Integer> leftArray = new ArrayList<>() ; 
        List<Integer> rightArray = new ArrayList<>(); 

        int mid = numbers.size() /2 ; 

        for (int i = 0 ; i< mid; i ++) {
            leftArray.add(this.numbers.get(i)) ; 
        }
        for (int i = mid ; i < numbers.size() ; i++) {
            rightArray.add(this.numbers.get(i)) ; 
        }
        
        
        Future<List<Integer>> leftSorted = executor.submit(new Sorter(leftArray, executor)) ; 
        Future<List<Integer>> rightSorted = executor.submit(new Sorter(rightArray, executor)) ; 

        leftArray = leftSorted.get() ; 
        rightArray = rightSorted.get() ; 

        return this.merge(leftArray, rightArray);
   
    }

    private List<Integer> merge(List<Integer> leftArray, List<Integer> rightArray) {

        List<Integer> answer = new ArrayList<>(); 

        int i =0,j = 0 ;

        while (i < leftArray.size() && j < rightArray.size() ) {
            if (leftArray.get(i) <= rightArray.get(j) ) {
                answer.add(leftArray.get(i)) ; 
                i++; 
            }
            else {
                answer.add(rightArray.get(j)) ; 
                j++; 
            }


        }

        while (i<leftArray.size()) {
            answer.add(leftArray.get(i));
            i++; 
        }

        while (j<rightArray.size()) {
            answer.add(rightArray.get(j));
            j++ ; 

        }

        return answer; 

    }
    
}
