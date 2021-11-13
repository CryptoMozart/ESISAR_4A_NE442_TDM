import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 * 
 */
public class ClientMultiplication
{
   
    public static void main(String[] args) throws Exception
    {
        try{
            ClientMultiplication clientUDP = new ClientMultiplication();
            clientUDP.execute();
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
    private void execute() throws IOException
    {
        System.out.println("Demarrage du client ...");
        int nbpartie = 0;

        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();

        InetSocketAddress adrDest = new InetSocketAddress("localhost",11000);
        while(nbpartie != 10){
            System.out.println("Partie n°: "+(nbpartie+1));
            byte[] bufE = new String("JOUER"+'\n').getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("Message envoyé: JOUER");
            // Attente de la reponse 1
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());

            // Attente de la reponse 2
            byte[] bufR2 = new byte[2048];
            DatagramPacket dpR2 = new DatagramPacket(bufR2, bufR2.length);
            socket.receive(dpR2);
            String reponse2 = new String(bufR2, dpR2.getOffset(), dpR2.getLength());

            System.out.println("Le serveur a repondu: "+reponse + "et "+reponse2);

            int int_reponse1 = Integer.parseInt(reponse.substring(0,1));
            int int_reponse2 = Integer.parseInt(reponse2.substring(0,1));
            int int_reponse = int_reponse1 * int_reponse2;

            String rep = String.valueOf(int_reponse)+';';
            bufE = new String(rep).getBytes();
            System.out.println("Message envoyé: "+rep);
            dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);

            bufR = new byte[2048];
            dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Reponse recue = "+reponse);

            nbpartie ++;
        }
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }

}
