public class PiMonoThread {

    private static final long N = Long.parseUnsignedLong("10000000000");

    public static void main(String[] args) {
        double res = 0;
        long start = System.currentTimeMillis();
        for(int i=0;i<N;i++){
            if(i%2==0){
                res += 1/(2*(double)i+1);
            }
            else{
                res += -1/(2*i+1);
            }        }
        res = 4*res;
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");
        System.out.println("pi = "+res);
    }
}