/*interface Algorithm {
	void crypt(String s);		//Metody w interfejsie są domyślnie publiczne i abstarkcyjne
	void decrypt(String s);		//Pola w interfejsie domyślnie są statyczne i finalneP
}*/

public class ROT11 implements Algorithm {                       //Algorithm is abstract; cannot be instantiated

    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";     //26//ABCDEFGHIJKLMNOPQRSTUVWXYZ
    public static final int rot = 11;

    //Unless the class that implements the interface is abstract, all the methods of the interface need to be defined in the class.

    @Override
    public String crypt(String str) {                           //w interfejsie domyślnie publiczne, ale tu trzeba napisać public
        str = str.toLowerCase();
        String crypted = "";                                       //ctrl+j joins
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);

            if (alphabet.indexOf(c) < 0) {
                System.out.println("Niepoprawny znak do zaszyfrowania! " + c);
                crypted += "_";
            }


            if (c >= alphabet.charAt(0) && c < alphabet.charAt(alphabet.length()-rot)) {//a do o
                crypted += alphabet.charAt( alphabet.indexOf(c)+rot );
            }
            else if (c >= alphabet.charAt( alphabet.length()-rot ) && c <= alphabet.charAt(alphabet.length()-1)) {
                crypted += alphabet.charAt( alphabet.indexOf(c) - (alphabet.length() - rot) );
            }
        }

        //System.out.println(str);
        //System.out.println(crypted);
        return crypted;
    }

    @Override
    public String decrypt(String str){
        str = str.toLowerCase();
        String decrypted = "";

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);

            if (alphabet.indexOf(c) < 0 ) {
                System.out.println("Niepoprawny znak do odszyfrowania! " + c + " = _");
                decrypted += "_";
            }

            if (c >= alphabet.charAt(0) && c < alphabet.charAt(rot) ) {     //a do k
                decrypted += alphabet.charAt( alphabet.length() -rot +alphabet.indexOf(c)  );
            }

            else if (c >= alphabet.charAt(rot) && c <= alphabet.charAt(alphabet.length()-1)) {
                decrypted += alphabet.charAt( alphabet.indexOf(c)- rot );
            }
        }
        return decrypted;
    }

}

//If the c is present in string, it returns the index(>=0).If not, it returns -1. 
