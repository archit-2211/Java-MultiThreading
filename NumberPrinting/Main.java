public class Main {
    public static void main(String[] args) {

        // Synchronous way of printing numbers 1 to 100

        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ".  Current Thread     " + Thread.currentThread());
        }

        /*
         * Thread class provides a static method : currenrtThread() to get the thread
         * name that is executing the current code.
         * 
         * Now lets do it in asynchronous way
         * 
         * 2 ways possible
         * 
         * 1. To create a child class of Thread class and assign the task to this thread
         * class
         * 2. Create a concrete class implementing runnable interface. Note: Runnable
         * interface is Funtional Interface,
         * 
         * 
         */

        // By using Child class Thread
        // Thread.start() => starts executing code written in run method.

        for (int i = 1; i <= 100; i++) {
            Thread object = new ChildClass(i);
            object.start();

        }

        // As we can see in the output each thread is separately executed by seprate
        // thread.
        // Note : .start() method can only be executed once per thread, subsequent calls
        // results in error

        // By using Runnable Interface
        for (int i = 1; i <= 100; i++) {
            RunnableImpl obj = new RunnableImpl(i);
            Thread t = new Thread(obj);
            t.start();
        }

        /*
         * Since runnable is a functional interface we can use lambda functions here
         */
        for (int i = 1; i <= 100; i++) {
            final int num = i; // must be effectively final to use inside lambda
            Thread t = new Thread(() -> {
                System.out.println(num + "     " + Thread.currentThread());
            });
            t.start(); // start the thread
        }
        // As you can see in the output executed by seprate threads.
        // It is always suggested to use Runnable interface to create threads instead of
        // creating concrete class extending Thread class.

        /*
         * Question : This step Thread t = new Thread(RunnableObj) isnt it boiler plate
         * ? Like for example when ever w ecreate a new task, the developer is himself
         * handlimng the task of creating thread, executing thread
         * Shouldnt this process be handled by java, we should only be concerned with
         * task assignment ??
         * 
         * Here comes the concept of executors, threadpool
         
         */

    }

}