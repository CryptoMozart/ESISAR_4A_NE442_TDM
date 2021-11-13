//package tdm02;

import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Programme2 {
    
    public static void main(String[] args) throws Exception{
        Programme2 p2 = new Programme2();
		p2.execute();
        
    }

    private void execute() throws Exception
	{
		JFrame frame = new JFrame("Programme 2");
		frame.setSize(300,300);
        //création socket
        DatagramSocket socket = new DatagramSocket(null);
        InetSocketAddress adrDest = new InetSocketAddress("localhost",4001);

        //coté serveur se déclare sur la couche transport
        socket.bind(new InetSocketAddress(4002));

        System.out.println("Démarrage programme 2");

        String reponse ="2";
		
		while(reponse.contains("2")){

            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je passe au vert et j'attends la réponse");

            //attente de la réponse
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            frame.getContentPane().setBackground(Color.RED);
            frame.setVisible(true);
            Thread.sleep(1000);
            System.out.println("Je passe au rouge et j'attends 1s");


            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je passe au vert");


            //envoie message UDP
            byte[] bufE = new String("1").getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("J'envoie le message UDP: "+bufE + "à: "+adrDest);

            
        }

		frame.dispose();
        socket.close();

		
	}
}
