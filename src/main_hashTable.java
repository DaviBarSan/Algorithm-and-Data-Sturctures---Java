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

        iteradorDaMassa.forEach( key -> System.out.println(newTest.get((Integer) key)));

        /*
        OpenAddressingHashTable testTableDoubleHasshed = new OpenAddressingHashTable();

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
            testTableDoubleHasshed.put(randomKey, randomValue);
        }


        long startTimeDoubleHash = System.currentTimeMillis();
        testTableDoubleHasshed.get(randomArray1[50]);
        long endTimeDoubleHash = System.currentTimeMillis();

        long startTimeMissingKey = System.currentTimeMillis();
        testTableDoubleHasshed.get("mAdMsD");
        long endTimeMissingKey = System.currentTimeMillis();

        long getTimeDoubleHash = (endTimeDoubleHash - startTimeDoubleHash);

        long getTimeDoubleHashMiss = ();


    }


        }

        long averageDoubleHash =
        */
    }



}