import aed.tables.OpenAddressingHashTable;

import java.security.Key;
import java.util.Arrays;

public class main_hashTable{
    public static void main(String[] args) {
        OpenAddressingHashTable testTableDoubleHasshed = new OpenAddressingHashTable();

        for (int i = 8; i/ testTableDoubleHasshed.getCapacity() <= 10; i = i + testTableDoubleHasshed.getCapacity()) {
            testTableDoubleHasshed.putDoubleHashed(i, i);
        }
        for (int i = 8; i/37 < 4; i = i + 37) {
            testTableDoubleHasshed.deleteDoubleHashed(i);
        }
        for (int i = 8; i/37 < 2; i = i + 37) {
            testTableDoubleHasshed.putDoubleHashed(i, i);
        }
        System.out.println(Arrays.toString(testTableDoubleHasshed.keys));
        System.out.println(Arrays.toString(testTableDoubleHasshed.values));

        OpenAddressingHashTable testTable = new OpenAddressingHashTable();

        for (int i = 8; i/ testTable.getCapacity() <= 10; i = i + testTable.getCapacity()) {
            testTable.putDoubleHashed(i, i);
        }
        for (int i = 8; i/37 < 4; i = i + 37) {
            testTable.deleteDoubleHashed(i);
        }
        for (int i = 8; i/37 < 2; i = i + 37) {
            testTable.putDoubleHashed(i, i);
        }

        System.out.println(Arrays.toString(testTable.keys));
        System.out.println(Arrays.toString(testTable.values));
    }


}