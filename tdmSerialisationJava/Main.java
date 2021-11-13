import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //crée une banque
        Banque banque = new Banque("Créditer les tous !");
        banque.ajouterCompte(1001,"nathan");
        banque.ajouterCompte(1002,"antoine");
        banque.ajouterCompte(1003,"hugo");

        //crée un automate et l'associer à la banque
        Automate automate = new Automate(banque);

        //crée l'utilisateur de l'automate
        Utilisateur user = new Utilisateur(automate);

        try{
            Administrateur admin = new Administrateur(banque,automate);
        }
        catch(ClassCastException e){
            System.out.println(e);
        }
    }
}
