import java.util.Scanner;

public class OddNumbers {
    static int oddCount(int n) {
        for (int i = 1; i < n; ++i) {
                if (i % 2 != 0)
                    System.out.print(i + " ");
        }
        return n/2;

    }
    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Podaj cyfrę: ");
        int n = reader.nextInt();
        System.out.println("Liczby nieparzyste mniejsze od " + n);
        int x = oddCount(n);
        System.out.println("Jest ich " + x);
        reader.close();
    }
}
