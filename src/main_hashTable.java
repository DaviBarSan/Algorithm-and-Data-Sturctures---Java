import aed.tables.OpenAddressingHashTable;


import java.util.Arrays;

public class main_hashTable{
    public static void main(String[] args) {
        OpenAddressingHashTable testTableDoubleHasshed = new OpenAddressingHashTable();

        for (int i = 8; i/ testTableDoubleHasshed.getCapacity() <= 10; i = i + testTableDoubleHasshed.getCapacity()) {
            testTableDoubleHasshed.put(i, i);
        }

        for (int i = 8; i/37 < 1490; i = i + 37) {
            testTableDoubleHasshed.put(i, i);
        }
        System.out.println(Arrays.toString(testTableDoubleHasshed.keys));
        System.out.println(Arrays.toString(testTableDoubleHasshed.values));
/*
        OpenAddressingHashTable testTable = new OpenAddressingHashTable();

        for (int i = 8; i/ testTable.getCapacity() <= 500; i = i + testTable.getCapacity()) {
            testTable.putLinearProbing(i, i);
        }
        for (int i = 8; i/37 < 4; i = i + 37) {
            testTable.deleteLinearProbing(i);
        }
        for (int i = 8; i/37 < 2; i = i + 37) {
            testTable.putLinearProbing(i, i);
        }

        System.out.println(Arrays.toString(testTable.keys));
        System.out.println(Arrays.toString(testTable.values));
*/
        testTableDoubleHasshed.keys().forEach( key ->
                System.out.println(testTableDoubleHasshed.get(key)));
    }



}