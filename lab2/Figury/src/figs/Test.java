package figs;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        LinkedList<Prostokat> figury = new LinkedList<Prostokat>();
        Scanner reader = new Scanner(System.in);

        int choice = 0;
        while(!(choice == 4)) {
            System.out.println("\nWybierz opcję");
            System.out.println("1 - Wczytaj prostokąt\t\t\t\t\t2 -Wyświetl prostokąty\n" +
                                "3 - Suma pól wszystkich prostokątów\t\t4 - Zakończ");

            choice = reader.nextInt();
            switch(choice) {
                case 1 :
                        figury.add(Prostokat.wczytajProstokat());
                        break;
                case 2 :
                        for(int i=0; i<figury.size(); ++i)
                            System.out.println("Prostokąt "+(i+1) + ": " +figury.get(i).getA()+" x "+figury.get(i).getB());
                        break;

                case 3 :
                        double suma = 0;
                        for(int i=0; i<figury.size(); ++i)
                            suma += figury.get(i).area();

                        System.out.println("Suma pól wszystkich prostokątów: " + suma + "\n");
                        break;
                case 4 :
                        choice = 4;

                default :
                        System.out.println("Nie ma takiej możliwości!\n");
                        break;
            }
        }
    }
}
