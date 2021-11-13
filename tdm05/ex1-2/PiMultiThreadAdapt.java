public class PiMultiThreadAdapt extends Thread{

    private static final long N = Long.parseUnsignedLong("10000000000");

    long start;
    long end;
    double res;

    public PiMultiThreadAdapt(long start,long end){
        this.start = start;
        this.end = end;
    }
    public void run(){
        for(long i = start; i< end; i++){
            if(i%2==0){
                res += 1/(2*(double)i+1);
            }
            else{
                res += -1/(2*(double)i+1);
            }
        }
    }
    public static void main(String[] args) throws Exception{

        int nb_threads = Integer.parseInt(args[0]);
        //System.out.println("Nombre de Thread: "+nb_threads);
	    PiMultiThreadAdapt tab[]= new PiMultiThreadAdapt[nb_threads];
        for(int i=0; i < nb_threads;i++ ){
            tab[i] = new PiMultiThreadAdapt(i*N/nb_threads,(i+1)*N/nb_threads);
        }
        long start = System.currentTimeMillis();
        for(int i=0; i < nb_threads;i++ ){
            tab[i].start();
        }
        for(int i=0; i < nb_threads;i++ ){
            tab[i].join(); 
        }
        double pi = 0;
        for(int i=0; i < nb_threads;i++ ){
            pi += tab[i].res;
        }
        pi = 4*pi;
        long stop = System.currentTimeMillis();
        System.out.println((stop-start));
        //System.out.println("pi = "+pi);
    }
}
