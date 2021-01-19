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

        iteradorDaMassa.forEach( key ->
                newTest.put((Integer) key,newTest.get((Integer) key)*5)

        );



        OpenAddressingHashTable testTableDoubleHashed = new OpenAddressingHashTable();
        Random r = new Random();
        Integer[] randomArray1 = new Integer[50000];

        //randomic array;
        for (int i = 0; i < 50000; i++){
            int random = r.nextInt(50000);
            randomArray1[i] = random;
        }

        for (int i = 0; i < 50000; i++) {
            int randomKey = randomArray1[i];
            int randomValue = randomArray1[i];
            testTableDoubleHashed.put(randomKey, randomValue);
        }


        long startTimeDoubleHash = System.nanoTime();
        testTableDoubleHashed.get(randomArray1[50]);
        long endTimeDoubleHash = System.nanoTime();

        long startTimeMissingKey = System.nanoTime();
        testTableDoubleHashed.get("mAdMsD");
        long endTimeMissingKey = System.nanoTime();

        long getTimeDoubleHash = (endTimeDoubleHash - startTimeDoubleHash);
        long getTimeDoubleHashMiss = (endTimeMissingKey - startTimeMissingKey);

        System.out.println("0." + getTimeDoubleHash + "ms");
        System.out.println("0." + getTimeDoubleHashMiss + "ms");
// ----------- ordem dobrada ----------------------------------------------
        OpenAddressingHashTable testTableDoubleHashedDuo = new OpenAddressingHashTable();
        Integer[] randomArray2 = new Integer[100000];

        //randomic array;
        for (int i = 0; i < 100000; i++){
            int random = r.nextInt(100000);
            randomArray2[i] = random;
        }

        for (int i = 0; i < 100000; i++) {
            int randomKey = randomArray2[i];
            int randomValue = randomArray2[i];
            testTableDoubleHashedDuo.put(randomKey, randomValue);
        }


        long startTimeDoubleHashDuo = System.nanoTime();
        testTableDoubleHashedDuo.get(randomArray1[50]);
        long endTimeDoubleHashDuo = System.nanoTime();

        long startTimeMissingKeyDuo = System.nanoTime();
        testTableDoubleHashedDuo.get("mAdMsD");
        long endTimeMissingKeyDuo = System.nanoTime();

        long getTimeDoubleHashDuo = (endTimeDoubleHashDuo - startTimeDoubleHashDuo);
        long getTimeDoubleHashMissDuo = (endTimeMissingKeyDuo - startTimeMissingKeyDuo);
        System.out.println("0." + getTimeDoubleHashDuo + "ms");
        System.out.println("0." + getTimeDoubleHashMissDuo + "ms");

    }



}
