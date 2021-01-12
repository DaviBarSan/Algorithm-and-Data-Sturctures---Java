import aed.tables.OpenAddressingHashTable;

import java.security.Key;

public class main_hashTable{
    public static void main(String[] args) {
        OpenAddressingHashTable testTable = new OpenAddressingHashTable();
        testTable.put(5, 10);
        testTable.put(null,10);
        testTable.put(5,null);
    }
}