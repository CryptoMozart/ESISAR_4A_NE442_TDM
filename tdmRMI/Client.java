import java.rmi.*;

public class Client {
	
	private Client() {}

	public static void main(String[] args) {
        try {
            //Appel de la méthode lookup()
			Hello obj = (Hello) Naming.lookup("Hello");	

			//Appel distant de la méthode sayHello()
        	
			//String messageFr = obj.sayHello(Langues.Fr);
			String message = obj.sayHello(Langues.Ch);

			
			System.out.println(message);
			//Affichage en plusieurs langues
			
			
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

	}

}
