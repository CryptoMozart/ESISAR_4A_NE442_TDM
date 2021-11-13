import java.rmi.*;

/**
 * 2.1.a A remote (à distance) interface extends the interface java.rmi.Remote
 * 2.1.b Each method of the interface declares java.rmi.RemoteException in its throws clause, 
 * in addition to any application-specific exceptions.
 */

public interface Hello extends Remote{
	public String sayHello(Langues l) throws RemoteException;

}