import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;

public class Serveur {
    public static void main(String[] args) throws Exception{

        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 2000
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(2000));

        // Attente de la connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());

        //reception message
        byte[] bufR = new byte[2048];
        InputStream is = socketConnexion.getInputStream();
        int lenBufR = is.read(bufR);
        String message2 = "";
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
            message2 = message;
        }
           
        // Emission du fichier
        FileInputStream file = new FileInputStream(message2);
        byte[] bufE = new byte[2048];
        OutputStream os = socketConnexion.getOutputStream();

        //envoie de la taille du fichier 
        String nomFichier = message2;
        File f = new File(nomFichier);
        long fileSize = f.length(); 
        String fileSizeS=String.valueOf(fileSize);
        byte[] buf = new String(fileSizeS+";").getBytes();
        os.write(buf);

        int len=0;
        long start = System.currentTimeMillis();
        while((len = file.read(bufE)) != -1){
            os.write(bufE,0,len);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");
        System.out.println("Message envoye = ok");

     

        // Fermeture de la socket de connexion
        socketConnexion.close();

        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }
}
