import aed.tables.OpenAdressingHashTable;
import java.util.Arrays;

public class main_hashTable{
    public static void main(String[] args) {
        OpenAdressingHashTable testTable = new OpenAdressingHashTable();
        for (int i = 0; i < 50; i++){
            testTable.put(i,i);
        }
        System.out.println(testTable.containsKey(35));
        for (int i = 0; i < 45; i++){
            testTable.delete(i);
        }
        System.out.println(testTable.containsKey(44));
        System.out.println(testTable.containsKey(46));
    }
}