public class Polibiusz implements Algorithm {
    private static final String al = "abcdefghiklmnopqrstuvwxyz";//25 i=j
    private static char tab[][] = new char[5][5];



    private static void fillArr() {
        for (int cnt = 0, i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j, ++cnt) {
                tab[i][j] = al.charAt(cnt);
            }
        }
    }

    public Polibiusz() { fillArr(); }

    private String cryptedChar(char cha) {

        if (cha == 'j')             //nasz alfabet nie zawiera j!
            cha = 'i';

        int i=0, j=0;
        outerloop://etykieta
        for (i = 0; i < 5; ++i)
            for (j = 0; j < 5; ++j)
                if (cha == tab[i][j])
                    break outerloop;


        String cr = Integer.toString(i+1) + Integer.toString(j+1);

        return cr;
    }


    @Override
    public String crypt(String str) {
        str = str.toLowerCase();

        for (char c : str.toCharArray())             //if (Character.isDigit(c))
            if (al.indexOf(c) < 0 && c!= 'j')
                   System.out.println("Nie można zaszyfrować znaku: "+ c +"!\nOtrzyma wartość 66.");

        String s = "";
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            s += cryptedChar(c) + " ";
        }

        return s;
    }

    @Override
    public String decrypt(String str) {
        if (str.length() != 2 ) {
            System.out.println("Błędny ilość znaków do zaszyfrowania\n"+ str + " Zostatnie zamieniony na \"_\".");
            return "_";
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("Napis do odszyfrowania musi składać się z cyfr!\n" + str + "Zostatnie zamieniony na \"_\".");
                return "_";
            }
        }

        if (Integer.parseInt(str)<11 || Integer.parseInt(str)>55) {
            System.out.println("Błędnie zaszyfrowany znak: " + str + "\nZostatnie zamieniony na \"_\".");
            return "_";
        }

        int i = Character.getNumericValue(str.charAt(0));
        int j = Character.getNumericValue(str.charAt(1));

        return Character.toString(tab[i-1][j-1]);   //bo tablica od 0
    }

}


