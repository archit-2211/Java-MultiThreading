package FactorialCalculation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lets calculate Factorial !!") ; 
        List<Integer> numbers = List.of(2,3,4,33,4,56,7);

    for (int num : numbers) {
        Thread t = new Thread(() -> {
            Long factorial = 1L;
            for (int i = 1; i <= num; i++) {
                factorial = factorial * i; 
            }

            System.out.println("Fcatorial of " + num + " is " + factorial + " Thread executing this is " + Thread.currentThread());


        });
        t.start();
    
    }

    }


    
}
