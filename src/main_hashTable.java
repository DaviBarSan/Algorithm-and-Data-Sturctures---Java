import aed.tables.OpenAddressingHashTable;

import java.security.Key;

public class main_hashTable{
    public static void main(String[] args) {
        OpenAddressingHashTable testTable = new OpenAddressingHashTable();
        for (int i = 8; i/37 < 10; i = i + 37) {
            testTable.put(i, i);
        }
        for (int i = 8; i/37 < 4; i = i + 37) {
            testTable.delete(i);
        }
        for (int i = 8; i/37 < 2; i = i + 37) {
            testTable.put(i, i);
        }
        testTable.put(119, 119);
    }
}