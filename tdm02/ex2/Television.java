//package tdm02;

import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Television {

	public static void main(String[] args) {
		try {
		Television t = new Television();
		t.execute();
		}
		catch(Exception e) {
			System.out.println("Erreur");
		}
	}

	private void execute() throws IOException{
		
		//int cnt=0;
		String message = "oui";

		System.out.println("Démarrage du serveur");
		//affichage de la fenêtre verte
		JFrame frame = new JFrame("Television");
		frame.setSize(300,300);
		
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
		
		// Le serveur se declare aupres de la couche transport
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(7050));
        
        while(!message.contains("end")) {
        // Attente du message
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        message = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Message recu = "+message);
	        if(message.contains("red")) {
	            frame.getContentPane().setBackground(Color.RED);
	        	frame.setVisible(true);
	        	
	        }
	        else if(message.contains("green")) {
	            frame.getContentPane().setBackground(Color.GREEN);
	        	frame.setVisible(true);
	        }
		//cnt++;
        }
        	  // Fermeture de la socket
         socket.close();
		 frame.dispose();
         System.out.println("Arret du serveur .");

	}
}
