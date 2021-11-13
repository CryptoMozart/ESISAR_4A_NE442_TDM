public class CalculatriceObjPartage extends Thread
{
    private Somme somme;

    public CalculatriceObjPartage(Somme somme)
    {
        this.somme = somme;
    }

	public void run()
    {
        int res = 0;
        for (int i = 0; i < 1000; i++)
        {
            res= somme.somme(res, i);
        }
        System.out.println("La somme 1+2+3+4+...+998+999 est égale à :"+res);
    }


    public static void main(String[] args) throws InterruptedException
    {
        Somme Somme = new Somme();
		Somme somme2 = new Somme();
        CalculatriceObjPartage c1 = new CalculatriceObjPartage(Somme);
        CalculatriceObjPartage c2 = new CalculatriceObjPartage(somme2);

        c1.start();
        c2.start();
    }


    static  class Somme
    {

        int c;

        public int somme(int a, int b) 
        {
            c=a+b;
            System.out.println("c="+c);
            return c;
        }

    }

}