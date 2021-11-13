public class CalculatriceMutex extends Thread
{
    private Somme somme;

    public CalculatriceMutex(Somme somme)
    {
        this.somme = somme;
    }

    @Override
    public void run()
    {
        int res = 0;
        for (int i = 0; i < 1000; i++)
        {
            res= somme.somme(res, i);
        }
        System.out.println("La Somme 1+2+3+4+...+998+999 est égale à :"+res);
    }


    public static void main(String[] args) throws InterruptedException
    {
        Somme somme = new Somme();
        CalculatriceMutex c1 = new CalculatriceMutex(somme);
        CalculatriceMutex c2 = new CalculatriceMutex(somme);

        c1.start();
        c2.start();
    }

    static class Somme
    {
        int c;

        synchronized public int somme(int a, int b) 
        {
            c=a+b;
            System.out.println("c="+c);
            return c;
        }

    }

}