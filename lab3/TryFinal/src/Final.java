import java.util.*;


public class Final {
    //public static void changeVar(final double var) { var += 1; } //final parameter var may not be assigned //wewnątrz funkcji do której przekazywana jest taka wartość/referencja nie można zmienić tej wartości/referencji

    public static void changeLinkedList(final LinkedList<Double> fll) {
        fll.add(3.5);
        fll.add(3.4);
        fll.clear();
        fll.add(3.3);
        fll.addFirst(1.0);
        fll.addLast((double)5);
        fll.removeFirst();
        fll.remove(1);
        //fll = new LinkedList<Double>();//final - nie można zmieniać referencji

    }

    public static void main(String[] args) {
        //Final.changeVar(2.4);
        LinkedList<Double> ll = new LinkedList<Double>();
        Final.changeLinkedList(ll);

        System.out.println("Linked List Content: " + ll);
    }
}