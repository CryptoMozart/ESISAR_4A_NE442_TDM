import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.awt.Color;
import javax.swing.JFrame;

public class Client {
    public static void main(String[] args) {
        try{
            Client c = new Client();
            c.execute(Integer.parseInt(args[0]));
        }
        catch(Exception e){
            System.out.println("Erreur");
        }
    }

    public void execute(int num) throws Exception{
        //se déclare au niveau de la couche transport
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(3100+num));
        InetSocketAddress adrDest = new InetSocketAddress("localhost",4005);

        System.out.println("Démarrage du client sur le port: "+(3100+num));

        JFrame frame = new JFrame(String.valueOf(num));
		frame.setSize(300,300);
        frame.setVisible(true);
        if(num==666){
            frame.setLocation(150,400);
        }
        else{
            frame.setLocation(150*num,100);
        }

        //envoie du message UDP au serveur pour se déclarer
        byte[] bufE = new String("localhost:"+String.valueOf(3100+num)).getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length,adrDest);
        socket.send(dpE);
        System.out.println("J'envoie le message UDP: à: "+adrDest);

        int cnt=0;
        while(cnt!=10){
            //en attente de réponse du serveur pour passer au rouge
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            frame.getContentPane().setBackground(Color.RED);
            frame.setVisible(true);
            System.out.println("Je suis rouge");            

            //en attente de réponse du serveur pour passer au vert
            bufR = new byte[2048];
            dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je suis vert"); 
            cnt++;
        }
        //socket.close();
        //System.out.println("Arrête du client");
    }
}
