public class Pesel {
    //String pesel;
    //public Pesel(String _pesel) { pesel = _pesel; }
    public static boolean check(String str) {
        try {
            double d = Double.parseDouble(str);			//int d = Integer.parseInt(str) , sprawdzanie czy to nie inne znaki
        } catch (NumberFormatException | NullPointerException nfe) {
            //System.out.println("Pesel nie składa się z liter!");
            return false;
        }

        if (str.length() != 11) {
            //System.out.println("Nieprawidłowa liczba cyfr");
            return false;
        }

        int[] num = new int[str.length()];

        for (int i = 0; i < str.length(); i++){
            num[i] = str.charAt(i) - '0';		 // for (int i : num) { System.out.println(i);}
        }

        int liczbaKontrolna = num[0]*1 + num[1]*3 + num[2]*7 + num[3]*9 + num[4]*1
                + num[5]*3 + num[6]*7 + num[7]*9 + num[8]*1 + num[9]*3 + num[10]*1;

        if (liczbaKontrolna % 10 != 0) {
            //System.out.println("Nieprawidłowa liczba kontrolna!");
            return false;
        }

        return true;
    }
}