public class SequentialThread extends Thread{
    
    double time = 0;

    public void run(){
        System.out.println(getName()+" Start");
        time = Math.random()*10000;
        try{
            Thread.sleep((int)time);
        }
        catch(Exception e){
            System.out.println("ERROR SLEEP");
        }
    }
    public static void main(String[] args) throws Exception{
        SequentialThread t1 = new SequentialThread();
        SequentialThread t2 = new SequentialThread();
        SequentialThread t3 = new SequentialThread();
        SequentialThread t4 = new SequentialThread();
        SequentialThread t5 = new SequentialThread();
        SequentialThread t6 = new SequentialThread();

        t1.start();
        t1.join();
        t2.start();
        t3.start();
        t4.start();
        t2.join();
        t3.join();
        t5.start();
        t4.join();
        t5.join();
        t6.start();

        System.out.println("THIS IS THE END");
    }
}
