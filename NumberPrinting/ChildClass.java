public class ChildClass extends Thread {
    private int number; 

    public ChildClass(int number){
        this.number = number; 
    }

    public void run(){
        System.out.println(number + "     Current Thread.      " + Thread.currentThread()); 
    }
    
}
