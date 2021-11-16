import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Compte implements Serializable
{
	private int		code		= 0;
	private String	client		= null;
	private Date	d_ouverture	= null;
	private int		solde		= 0;


	public String getClient(){
		return client;
	}

	public Date getDouverture(){
		return this.d_ouverture;
	}

	public int getSolde(){
		return this.solde;
	}

	public int getCode(){
		return this.code;
	}

	public void setClient(String c){
		this.client = c;
	}

	public void setDouverture(Date d){
		this.d_ouverture = d;
	}

	public void setSolde(int s){
		this.solde = s;
	}

	public void setCode(int c){
		this.code = c;
	}

	public Compte()
	{}

	public Compte(int code, String client)
	{
		this.code = code;
		this.client = client;
		this.d_ouverture = Calendar.getInstance().getTime();
	}

	/**
	 * Consulter le compte
	 * 
	 * @return
	 */
	public int consulter()
	{
		return this.solde;
	}

	/**
	 * Cr�diter le compte
	 * 
	 * @param somme
	 * @return
	 */
	public boolean crediter(int somme)
	{
		this.solde += somme;
		return true;
	}

	/**
	 * D�biter une somme de ce compte
	 * 
	 * @param somme
	 * @return
	 */
	public boolean debiter(int somme)
	{
		if (somme < this.solde)
		{
			this.solde -= somme;
			return true;
		}
		return false;
	}
}
