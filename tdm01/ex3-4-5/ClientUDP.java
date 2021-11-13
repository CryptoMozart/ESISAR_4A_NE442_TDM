import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 * 
 */
public class ClientUDP
{
   
    public static void main(String[] args) throws Exception
    {
        try{
            ClientUDP clientUDP = new ClientUDP();
            clientUDP.execute(args[0],Integer.parseInt(args[1]),args[2]);
        }
        catch(Exception argument){
            System.out.println("Erreur argument");
        }
     
    }
    
   
    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute(String adressdest, int portdest, String message) throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();

        // Creation et envoi du message
        InetSocketAddress adrDest = new InetSocketAddress(adressdest,portdest);
        byte[] bufE = new String(message+'\n').getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Message envoyé à "+adrDest);

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Reponse recue = "+reponse);

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }

}
