import java.util.*;

public class CheckPesel {
    public static void main(String[] args) {
        String pes;
        Scanner reader = new Scanner(System.in);
        System.out.println("Podaj PESEL do sprawdzenia: ");
        pes = reader.next();
        if (Pesel.check(pes))
            System.out.println("Prawidłowy PESEL");
        else
            System.out.println("PESEL nieprawidłowy");

        reader.close();
    }
}