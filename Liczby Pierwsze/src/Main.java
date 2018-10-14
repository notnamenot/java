import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);       // Scanner object

        System.out.println("Wprowadź liczbę");
        int num = sc.nextInt();
        System.out.println("Podana liczba to "+num);

        LiczbyPierwsze lp = new LiczbyPierwsze(num);
        lp.find_prime_numbers();
    }
}