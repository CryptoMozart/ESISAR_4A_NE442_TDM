import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 * 
 */
public class ClientPingPong
{
   
    public static void main(String[] args) throws Exception
    {
        try{
            ClientPingPong clientUDP = new ClientPingPong();
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

        InetSocketAddress adrDest = new InetSocketAddress("localhost",29000);
        while(nbpartie != 10){
            System.out.println("Partie n°: "+(nbpartie+1));
            byte[] bufE = new String("JOUER"+'\n').getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("Message envoyé: JOUER");
            // Attente de la reponse 
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());

                if(reponse.equals("PING")){
                    System.out.println("Reponse recue = "+reponse);
                    bufE = new String("PONG").getBytes();
                    System.out.println("Message envoyé: PONG");
                    dpE = new DatagramPacket(bufE, bufE.length, adrDest);
                    socket.send(dpE);
                }
                else if(reponse.equals("PONG")){
                    System.out.println("Reponse recue = "+reponse);
                    bufE = new String("PING").getBytes();
                    System.out.println("Message envoyé: PING");
                    dpE = new DatagramPacket(bufE, bufE.length, adrDest);
                    socket.send(dpE);
                }

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
