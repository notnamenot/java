
public class LiczbyPierwsze {
    private int liczba;

    public LiczbyPierwsze(int _liczba)
    {
        liczba=_liczba;
    }

    public void find_prime_numbers()
    {
        int lp=2;
        int p=2;
        boolean war=true;
        int d;
        while(lp<=liczba)
        {
            lp+=1;
            war = true;
            for (d = 2; d < p; d++)
            {
                if(p%d == 0)
                {
                    war=false;
                    break;
                }
            }
            if(war)
                System.out.println(p);

            p+=1;
        }
    }
}