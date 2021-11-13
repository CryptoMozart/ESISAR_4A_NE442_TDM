import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class Server extends UnicastRemoteObject implements Hello {

	public Server() throws RemoteException{    
    }

    public String sayHello(Langues l) throws RemoteException{
        switch (l) {
            case Fr:
                return "Bonjour le monde!";
            case En:
                return "Hello World!";
            case Sp:
                return "¡Hola Mundo !";
            case Ch:
                return "你好，世界";
            case Jp:
                return "ハロー・ワールド";
            case Ru:
                return "Здравствуй мир";
            default:
                return "Hello World!";
        }
        
	}
    
    public static void main(String args[]) {

        try {
            //instanciation d'un objet Server
            Server s = new Server();

            //Création du registry          
            Registry registry = LocateRegistry.createRegistry(1099);
            		
            //Appel de la méthode rebind pour enregistrer l'object serveur
            Naming.rebind("Hello",s);

            System.out.println("Server ready!");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}