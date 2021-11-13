public class PiMultiThread extends Thread{

    private static final long N = Long.parseUnsignedLong("10000000000");

    private long start;
    private long end;
    private double res;

    public PiMultiThread(long start,long end){
        this.start = start;
        this.end = end;
    }
    
    public void run(){
       for(long i = start; i< end; i++){
            if( (i%2) == 0){
                res += 1/(2*(double)i+1);
            }
            else{
                res -= 1/(2*(double)i+1);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        PiMultiThread prg1 = new PiMultiThread(0,N/8);
        PiMultiThread prg2 = new PiMultiThread(N/8,2*N/8);
        PiMultiThread prg3 = new PiMultiThread(2*N/8,3*N/8);
        PiMultiThread prg4 = new PiMultiThread(3*N/8,4*N/8);
        PiMultiThread prg5 = new PiMultiThread(4*N/8,5*N/8);
        PiMultiThread prg6 = new PiMultiThread(5*N/8,6*N/8);
        PiMultiThread prg7 = new PiMultiThread(6*N/8,7*N/8);
        PiMultiThread prg8 = new PiMultiThread(7*N/8,N);

        long start = System.currentTimeMillis();
        prg1.start();
        prg2.start();
        prg3.start();
        prg4.start();
        prg5.start();
        prg6.start();
        prg7.start();
        prg8.start();

        prg1.join();
        prg2.join();
        prg3.join();
        prg4.join();
        prg5.join();
        prg6.join();
        prg7.join();
        prg8.join();

        double pi = 4*(prg1.res + prg2.res + prg3.res + prg4.res + prg6.res + prg7.res + prg8.res);
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");
        System.out.println("pi = "+pi);
    }
}
