import javax.swing.JFrame;

import java.beans.XMLEncoder;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Administrateur extends JFrame
{
	private static final long	serialVersionUID	= 1381505744347163446L;


	/*private void serialiserBanque() throws IOException,ClassNotFoundException {
		FileOutputStream file = new FileOutputStream("banqueData.temp");

		ObjectOutputStream sortie = new ObjectOutputStream(file);

		sortie.writeObject(this.banque);

		sortie.close();
		file.close();
	}*/

	private void serialiserBanque() throws IOException,ClassNotFoundException{
		XMLEncoder encoder = null;
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("banqueData.xml")));
            encoder.writeObject(this.banque);
            encoder.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(encoder != null){
                encoder.close();
            }
        }
	}

	/**
	 * Cette méthode doit permettre de dé-sérialiser la banque
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void deserialiserBanque()throws IOException,ClassNotFoundException{
		FileInputStream inFile = new FileInputStream("banqueData.temp");

		ObjectInputStream entree = new ObjectInputStream(inFile);

		Banque banque = (Banque) entree.readObject();

		this.banque = banque;
		this.automate.setBanque(banque);
		
		entree.close();
		inFile.close();

	}

	private Banque				banque				= null;
	private Automate			automate			= null;

	private javax.swing.JPanel	jContentPane		= null;
	private JButton				boutonSerialiser	= null;
	private JTextField			champSortie			= null;
	private JButton				boutonDeserialiser	= null;
	private JTextField			champEntree			= null;

	/**
	 * This method initializes boutonSerialiser
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoutonSerialiser()
	{
		if (boutonSerialiser == null)
		{
			boutonSerialiser = new JButton();
			boutonSerialiser.setBounds(14, 11, 169, 25);
			boutonSerialiser.setText("Sérialiser Banque");
			boutonSerialiser.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e){
					try {
						serialiserBanque();
						System.out.println("Sérialisation réussie");
					} catch (ClassNotFoundException | IOException e1) {
						System.out.println(e1);
					}
				}
			});
		}
		return boutonSerialiser;
	}

	/**
	 * This method initializes champSortie
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getChampSortie()
	{
		if (champSortie == null)
		{
			champSortie = new JTextField();
			champSortie.setBounds(15, 42, 282, 26);
		}
		return champSortie;
	}

	/**
	 * This method initializes boutonDeserialiser
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoutonDeserialiser()
	{
		if (boutonDeserialiser == null)
		{
			boutonDeserialiser = new JButton();
			boutonDeserialiser.setBounds(18, 92, 165, 25);
			boutonDeserialiser.setText("Restaurer Banque");
			boutonDeserialiser.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					try {
						deserialiserBanque();
						System.out.println("Dé-sérialisation réussie");
					} catch (ClassNotFoundException | IOException e1) {
						System.out.println(e1);
					}
				}
			});
		}
		return boutonDeserialiser;
	}

	/**
	 * This method initializes champEntree
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getChampEntree()
	{
		if (champEntree == null)
		{
			champEntree = new JTextField();
			champEntree.setBounds(19, 124, 277, 26);
		}
		return champEntree;
	}

	public static void main(String[] args)
	{}

	/**
	 * This is the default constructor
	 */
	public Administrateur(Banque banque, Automate autom)
	{
		super();
		this.banque = banque;
		this.automate = autom;
		initialize();
		setVisible(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(321, 204);
		this.setContentPane(getJContentPane());
		this.setTitle("Administrateur");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBoutonSerialiser(), null);
			jContentPane.add(getChampSortie(), null);
			jContentPane.add(getBoutonDeserialiser(), null);
			jContentPane.add(getChampEntree(), null);
		}
		return jContentPane;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
