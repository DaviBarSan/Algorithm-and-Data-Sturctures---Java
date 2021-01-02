import aed.collections.MinPriorityQueue;
import java.util.Arrays;
import java.util.Random;
import aed.sorting.*;

public class main {

    public static void main(String[] args) {

        //situações iniciais do array
        //-->> Array ordenado, array ordenado inversamente,
        //array parcialmente (1/8 == random), array aleatório.

        Random r = new Random();
        Integer[] testArray = new Integer[100000];

        //all elements are random with bound == array capacity
        for (int i = 0; i < testArray.length; i++) {
            testArray[0] = r.nextInt(100000);
        }

        //all elements are random with bound << array capacity (muitos elementos repetidos)
        /*for (int i = 0; i < testArray.length; i++) {
            testArray[i] = r.nextInt(500);
        }
        */

        //already sorted array
        /*for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
         */

        //reversed sorted array
        /*
        for (int i = testArray.length-1; i >= 0; i--) {
            testArray[testArray.length-i] = i;
         */

        //partialy sorted array (1/8 random)
        /*
        for (int i = 0; i < testArray.length; i++)
            testArray[i] = i;
        for (int m = 0; m < testArray.length/8; m++)
            testArray[m] = r.nextInt(testArray.length/8);
        */


        long[] timespamArray = new long[75];
        for (int i = 0; i < timespamArray.length; i++) {

            long startTime = System.currentTimeMillis();
            //sort(testArray);
            long endTime = System.currentTimeMillis();

            long timespan = endTime - startTime;
            timespamArray[i] = timespan;
        }


    }
}
/*
        Random r = new Random();

        Integer[] testArray = {24,16,12,9,7,5,4,4,7,2};

        MinPriorityQueue testHeap = new MinPriorityQueue(testArray);


        System.out.println(testHeap.isMinHeap(testHeap.getElements(), 10));
        //fazer o resize caso o array seja muito menor que o maxSize;
        //elements size = tamanho do array contando com o index 0;
        */