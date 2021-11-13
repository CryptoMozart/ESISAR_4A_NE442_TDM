import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class SommeTCP extends Thread{

    int portStart;
    int valeur; 

    //on créer une arrayList avec le port associé à la valeur
    public static ArrayList<Max> tabPortMax = new ArrayList<>();

    public SommeTCP(int portStart){
        this.portStart = portStart;
    }

    public void run(){
        //chaque thread ouvre une connexion TCP avec le serveur
        try{
            exec();
        }
        catch(Exception e){
            System.out.println("ERROR");
        }
        
    }
    public static void main(String[] args) throws Exception{

        SommeTCP tabThread[] = new SommeTCP[2001];

        //on initialise les threads
        for(int i = 0; i < tabThread.length ; i++){
            tabThread[i] = new SommeTCP(21000+i);
        }

        //on les run un par un
        for(int i = 0; i < tabThread.length ; i++){
            tabThread[i].start();
        }
        System.out.println("Début de la recherche");

        //on les joins
        for(int i = 0; i < tabThread.length ; i++){
            tabThread[i].join();
        }

        Max result = new Max();
        Max result2 = new Max();
        result = result.maxMax(tabPortMax);
        
        long resAllValues = result2.maxAllValues(tabPortMax);

        System.out.println("Le montant maximal est: " + result.max +"€");
        System.out.println("Ce montant correspond au port: " + result.port);
        System.out.println("La somme des montants retournés par tous les ports est: " + resAllValues);
        System.out.println("Fin du programme");
    }

    synchronized private void exec() throws IOException{            
            //Creation de la socket
        	Socket socket = new Socket();
            // Connexion au serveur 
            InetSocketAddress adrDest = new InetSocketAddress("localhost",portStart);
            socket.connect(adrDest); 

            //envoie du message COMBIEN au serveur
            byte[] bufE = new String("COMBIEN").getBytes();
            OutputStream os = socket.getOutputStream();
            os.write(bufE);

            //attente de la réponse
            byte[] bufR = new byte[2048];
            InputStream is = socket.getInputStream();
            int lenBufR = is.read(bufR);
            String message = new String();

            while(lenBufR != -1){
                String reponse_serveur = new String(bufR, 0 , lenBufR );
                message = message + reponse_serveur;

                if(message.contains("EURO") && message.contains("=")){
                    //traitement de la réponse
                    valeur = traitement(message);
                }

                lenBufR = is.read(bufR);
            }

            //sauvegarde du montant max et du port
            synchronized(tabPortMax){
                Max m = new Max(valeur,portStart);
                tabPortMax.add(m);
            }
            socket.close(); 
    }

    private int traitement(String message){
        return( Integer.parseInt (message.substring( message.indexOf('=')+1 , message.indexOf('E' ) ) ) );
    }
    public static class Max{

        private int max;
        private int port; 
    
        public Max(int max, int port){
            this.max = max;
            this.port = port;
        }
        public Max(){

        }
    
        public Max maxMax(ArrayList<Max> m){
            int maximum = -1;
            Max res = new Max();
            for(Max val: m){
                if(val.max > maximum){
                    res.port = val.port;
                }
                maximum = Math.max(val.max,maximum);
            }
            res.max = maximum;
            return res;
        } 
    
        synchronized public long maxAllValues(ArrayList<Max> m){
            long res = 0;
            for(Max val: m){
                res += val.max;
            }
            return res;
        }
    
    } 

}
