import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Serveur {
    public static void main(String[] args){
        try{
            Serveur s = new Serveur();
            s.exectue();
        }
        catch(Exception e){
            System.out.println("Erreur serveur");
        }
        
    }

    public void exectue() throws Exception{
        //déclaration serveur écoute sur le port 4005
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4005));
        System.out.println("Démmarage du serveur: ");
        ArrayList<InetSocketAddress> listeAdres = new ArrayList<InetSocketAddress>();

        String reponse = "oui";
        //le dernier client enverra "manger"
        while(!reponse.contains("3766")){

            //attente de la réponse sous la forme @IP:port
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            if(!reponse.startsWith("3766")){
                //on ajoute le client à la liste
                listeAdres.add(recepAdr(reponse));
                System.out.println("Client bien ajouté");
            }
            
        }
        int cnt=0;
        //10 tours suffiront
        while(cnt!=10){
            for(int i=1;i<=listeAdres.size();i++){
                //permission de passer au rouge
                byte[] bufE = new String(String.valueOf(i)).getBytes();
                DatagramPacket dpE = new DatagramPacket(bufE, bufE.length,listeAdres.get(i-1).getAddress(),listeAdres.get(i-1).getPort());
                socket.send(dpE);
                System.out.println("J'envoie le message UDP: à: "+listeAdres.get(i-1).getAddress());

                Thread.sleep(1000);

                //permission de passer au vert
                bufE = new String(String.valueOf(i)).getBytes();
                dpE = new DatagramPacket(bufE, bufE.length,listeAdres.get(i-1).getAddress(),listeAdres.get(i-1).getPort());
                socket.send(dpE);
                System.out.println("J'envoie le message UDP: à: "+listeAdres.get(i-1).getAddress());

            }
            cnt++;
            System.out.println(cnt);
        }
        socket.close();
        System.out.println("Arret du serveur .");
    }

    private InetSocketAddress recepAdr(String reponse) throws Exception{
        int indice = reponse.indexOf(':');
        String port = reponse.substring(indice+1,reponse.length());
        String adrIP = reponse.substring(0,indice);
        System.out.println("Port: "+port+" IP: "+adrIP);
        int int_port = Integer.parseInt(port);
        InetSocketAddress retour = new InetSocketAddress(adrIP,int_port);
        System.out.println("Création retour InetSocketAddress");
        return retour;
    }
}
