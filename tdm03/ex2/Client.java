import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Client c = new Client();
        c.execute();
    }

    public void execute() throws IOException{

        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("localhost",3024);
        socket.connect(adrDest);        

        // Envoi de la requete
        byte[] bufE = new String("question du client").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");

    }
}
