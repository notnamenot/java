import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {   //argumenty od 0!!
        Scanner reader = new Scanner(System.in);
        System.out.println("\nCo chcesz zrobć użytkowniku? Wybierz opcję:\n");
        System.out.println("1 - szyfrować algorytmem ROT11:\n");
        System.out.println("2 - szyfrować algorytmem Polibiusz:\n");
        System.out.println("3 - deszyfrować algorytmem ROT11:\n");
        System.out.println("4 - deszyfrować algorytmem Polibiusz:\n");
        int choice = reader.nextInt();
        switch(choice) {
            case 1 :
                ROT11 crtrot = new ROT11();
                Cryptographer.cryptfile(args[0], args[1], crtrot);
                break;
            case 2 :
                Polibiusz crtpol = new Polibiusz();
                Cryptographer.cryptfile(args[0], args[1], crtpol);
                break;
            case 3 :
                ROT11 dcrtrot = new ROT11();
                Cryptographer.decryptfile(args[0], args[1], dcrtrot);
                break;
            case 4 :
                Polibiusz dcrtpol = new Polibiusz();
                Cryptographer.decryptfile(args[0], args[1], dcrtpol);
                break;
            default:
                System.out.println("Nie ma takiej opcji.");
        }
    }
}
