import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        System.out.println("Demarrage du client ...");

         //Creation de la socket
         Socket socket = new Socket();

         // Connexion au serveur 
         InetSocketAddress adrDest = new InetSocketAddress("localhost",2000);
         socket.connect(adrDest); 


        FileOutputStream file = new FileOutputStream(args[1]);
        InputStream is = socket.getInputStream();

        // Envoi de la requete
        byte[] bufE = new String(args[0]).getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        int lenBufR = is.read(bufR);
        String message = new String(bufR, 0 , lenBufR);

        while(!message.contains(";")){
            lenBufR = is.read(bufR);
        }
        System.out.println("Taille du fichier = "+message);
        message = message.substring(0,message.indexOf(";"));
        lenBufR = 0;
        float current_lenBufR = 0;
        long start = System.currentTimeMillis();
        long last_millis = System.currentTimeMillis();
        while((lenBufR = is.read(bufR)) != -1){
            current_lenBufR+=lenBufR;
            if( System.currentTimeMillis() - last_millis >= 500){
                System.out.println("Avancement: "+(int)(100 * current_lenBufR/ (float)Integer.parseUnsignedInt(message))+"%");
                System.out.print("\033[1A");
                last_millis = System.currentTimeMillis();
            }
            file.write(bufR,0,lenBufR);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Avancement: 100%");
        System.out.println("Elapsed Time = "+(stop-start)+" ms");

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
    
}
