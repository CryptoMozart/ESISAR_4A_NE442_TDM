public class SequentielThreadComplex extends Thread{
    
    int time = 0;

    public SequentielThreadComplex(){
        time = (int)(Math.random()*10000);
    }
    public void run(){
        System.out.println(getName()+" Start");
        
        try{
            Thread.sleep(time);
        }
        catch(Exception e){
            System.out.println("ERROR SLEEP");
        }
    }

    public static void main(String[] args) throws Exception{
        SequentielThreadComplex t0 = new SequentielThreadComplex();
        SequentielThreadComplex t1 = new SequentielThreadComplex();
        SequentielThreadComplex t2 = new SequentielThreadComplex();
        SequentielThreadComplex t3 = new SequentielThreadComplex();
        SequentielThreadComplex t4 = new SequentielThreadComplex();
        SequentielThreadComplex t5 = new SequentielThreadComplex();
        SequentielThreadComplex t6 = new SequentielThreadComplex();

        t0.start();
        t0.join();
        t1.start();
        t2.start();
        t3.start();
        if(Math.max(t1.time,t2.time) > t3.time){
            t3.join();
            t5.start();
            t1.join();
            t2.join();
            t4.start();
        } 
        else{
            t1.join();
            t2.join();
            t4.start();
            t3.join();
            t5.start();
        }
        t4.join();
        t5.join();
        t6.start();
        t6.join();
        System.out.println("THIS IS THE END");
    }
}
