import aed.tables.OpenAddressingHashTable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class main_hashTable {
    public static void main(String[] args) {
        OpenAddressingHashTable<Integer, Integer> newTest = new OpenAddressingHashTable<>();
        newTest.put(5, 0);
        newTest.put(9, 50);
        newTest.put(50, 15);
        newTest.put(15, 4);

        Iterable iteradorDaMassa = newTest.keys();

        iteradorDaMassa.forEach(key -> newTest.put((Integer) key, newTest.get((Integer) key) * 5));

        iteradorDaMassa.forEach(key -> System.out.println(newTest.get((Integer) key)));
    }

}