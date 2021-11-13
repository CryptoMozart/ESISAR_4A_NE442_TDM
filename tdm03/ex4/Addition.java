import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Addition {
	
	public static void main(String[] args) throws Exception
    {
        Addition Addition = new Addition();
        Addition.execute();                
    }
	
	private void execute() throws Exception
    {
		System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		Socket socket = new Socket();
		
		// Connexion au serveur 
		InetSocketAddress adrDest = new InetSocketAddress("localhost", 7500);
		socket.connect(adrDest);        
		
		
		// Attente de la reponse 
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		String message = new String();
		while (lenBufR!=-1)
		{
		    String reponse_serveur = new String(bufR, 0 , lenBufR );
		    System.out.println("Reponse recue = "+ reponse_serveur);

			
			message = message + reponse_serveur;
						
			while( message.contains("?")) {
				System.out.println("Question: " +message);

				int rep = traitement(message)[0];
				int indice3 = traitement(message)[1];
				
				//envoie de la reponse
				byte[] bufE = new String(rep +";").getBytes();
				OutputStream os = socket.getOutputStream();
				os.write(bufE);
				System.out.println("Réponse du calcul " + rep);	    

	            message = message.substring(indice3+1);
			        
			}

			lenBufR = is.read(bufR);              
		}
		
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
    }

	private int[] traitement(String message){
		int indice1 = message.indexOf("+");
		int indice2 = message.indexOf("=");
		int indice3 = message.indexOf("?");
		
		//Récupère que les nombres 
		String nombre1 = message.substring(0,indice1); 
		String nombre2 = message.substring(indice1,indice2); 
		
		int nb1 = Integer.parseInt(nombre1);
		int nb2 = Integer.parseInt(nombre2);
		
		int rep[] ={nb1+nb2,indice3};
		return(rep);
	}
	 
}
