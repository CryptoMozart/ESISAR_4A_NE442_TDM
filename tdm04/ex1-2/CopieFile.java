import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopieFile {
    public static void main(String[] args) throws Exception{

        FileInputStream r = new FileInputStream("/home/hugo/Documents/ESISAR/4A/S1/NE442/TDM/tdm04/ex1-2/"+args[0]); 
        FileOutputStream w = new FileOutputStream("/home/hugo/Documents/ESISAR/4A/S1/NE442/TDM/tdm04/ex1-2/"+args[1]);

        int o = 0;
        int lenBuff = Integer.parseInt(args[2]);
        byte[] buffer = new byte[lenBuff];

        long start = System.currentTimeMillis();
        while((o = r.read(buffer)) != -1){
            w.write(buffer,0,o);
        }
        long stop = System.currentTimeMillis();
        System.out.println(stop-start);
        r.close();
        w.close();
    }
}
