public class RunnableImpl implements Runnable{
    private int number ; 

    public RunnableImpl(int number) {
        this.number = number; 
    }

    @Override
    public void run() {
        System.out.println(this.number + "      current Thread.       " + Thread.currentThread()) ; 
    }

    
}
