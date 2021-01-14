import aed.tables.OpenAddressingHashTable;

import java.security.Key;
import java.util.Arrays;

public class main_hashTable{
    public static void main(String[] args) {
        OpenAddressingHashTable testTable = new OpenAddressingHashTable();
        for (int i = 8; i/ testTable.getCapacity() <= 10; i = i + testTable.getCapacity()) {
            testTable.doublehashedPut(i, i);
        }
        for (int i = 8; i/37 < 4; i = i + 37) {
            testTable.delete(i);
        }
        for (int i = 8; i/37 < 2; i = i + 37) {
            testTable.doublehashedPut(i, i);
        }


        testTable.keys().forEach(Key -> System.out.println(Key));
        testTable.keys().forEach(Value -> System.out.println(Value));

    }


}