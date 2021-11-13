import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Exercice5 {
  
    public static void main(String[] args) throws Exception {
        Exercice5 e = new Exercice5();
        if(args[0].equals("1")){
            e.execute1();
        }
        else if(args[0].equals("2")){
            e.execute2();
        }
        else if(args[0].equals("3")){
            e.execute3();
        }
        else if(args[0].equals("4")){
            e.execute4();
        }
        else{
            System.out.println("Erreur argument");
        }
    }

    public void execute1()throws Exception{
        //carré haut gauche
        System.out.println("Démarrage carré haut - gauche");
        JFrame frame = new JFrame("1");
		frame.setSize(300,300);

        //déclaration serveur - client UDP
        DatagramSocket socket = new DatagramSocket(null);
        InetSocketAddress adrDest = new InetSocketAddress("localhost",5600);
        socket.bind(new InetSocketAddress(4001));

        while(true){
            frame.getContentPane().setBackground(Color.RED);
            frame.setVisible(true);
            Thread.sleep(1000);
            System.out.println("Je passe au rouge j'attends 1s");

            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je passe au vert");

            //envoie message UDP
            byte[] bufE = new String("1").getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("J'envoie le message UDP: à: "+adrDest);

            //attente de la réponse
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu"+reponse);
        }
        
    }

    public void execute2() throws Exception{
        //carré haut droit
        System.out.println("Démarrage carré haut - droit");
        JFrame frame = new JFrame("2");
		frame.setSize(300,300);

        //déclaration serveur - client UDP
        InetSocketAddress adrDest = new InetSocketAddress("localhost",4003);

        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(5600));

        while(true){
            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je passe au vert et j'attends la réponse");

            //attente de la réponse
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            frame.getContentPane().setBackground(Color.RED);
            frame.setVisible(true);
            Thread.sleep(1000);
            System.out.println("Je passe au rouge et j'attends 1s");
            System.out.println("Je passe au vert");

            //envoie message UDP
            byte[] bufE = new String("2").getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("J'envoie le message UDP: à: "+adrDest);
        }
    }

    public void execute3() throws Exception{
        //carré bas droit
        System.out.println("Démarrage carré bas - droit");
        JFrame frame = new JFrame("3");
		frame.setSize(300,300);

        //déclaration serveur - client UDP
        InetSocketAddress adrDest = new InetSocketAddress("localhost",5642);

        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4003));

        while(true){
            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je passe au vert et j'attends la réponse");

            //attente de la réponse
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            frame.getContentPane().setBackground(Color.RED);
            frame.setVisible(true);
            Thread.sleep(1000);
            System.out.println("Je passe au rouge et j'attends 1s");
            System.out.println("Je passe au vert");

            //envoie message UDP
            byte[] bufE = new String("3").getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("J'envoie le message UDP: à: "+adrDest);
        }
    }

    public void execute4() throws Exception{
        //carré bas gauche
        System.out.println("Démarrage carré bas - gauche");
        JFrame frame = new JFrame("4");
		frame.setSize(300,300);
        //déclaration serveur - client UDP

        InetSocketAddress adrDest = new InetSocketAddress("localhost",4001);

        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(5642));

        while(true){            
            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
            System.out.println("Je passe au vert et j'attends la réponse");

            //attente de la réponse
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("J'ai recu: "+reponse);

            frame.getContentPane().setBackground(Color.RED);
            frame.setVisible(true);
            Thread.sleep(1000);
            System.out.println("Je passe au rouge et j'attends 1s");
            System.out.println("Je passe au vert");

            //envoie message UDP
            byte[] bufE = new String("4").getBytes();
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            System.out.println("J'envoie le message UDP: à: "+adrDest);        
        }
    }
}
